package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.Util.AssetCode;
import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.dto.AddUpdateDTO;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetChangeDTO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IAssetService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AssetServiceImpl implements IAssetService {
    @Value("8")
    private Integer defaultQueryPageSize;
    @Autowired
    private IAssetCacheRepository iAssetCacheRepository;
    @Autowired(required = false)
    private AssetMapper assetMapper;
    @Autowired
    private IAssetService iAssetService;

    @Override
    public PageData<AssetVO> getAsset(Integer pageNum) {
        return null;
    }

    @Override
    public PageData<AssetVO> getAssetByType(String type, Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        List<AssetVO> voList = assetMapper.listAssetByCategory(type);
        PageInfo<AssetVO> pageInfo = new PageInfo<>(voList);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public void rebuildCache() {
        log.debug("开始处理【重建资产数据】的业务");
        iAssetCacheRepository.deleteAll();

        List<String> categoryList = assetMapper.listAllCategory();
        List<AssetVO> assetVOList = null;

        for (String type : categoryList) {
            iAssetCacheRepository.saveCategory(type);
            assetVOList = assetMapper.listAssetByCategory(type);
            iAssetCacheRepository.saveAsset(type,assetVOList);

        }

        iAssetCacheRepository.saveAll();
    }

    @Override
    public List<AssetStatisticDTO> getStatistics() {
        log.debug("开始处理【获取统计信息】的业务");
        List<AssetStatisticDTO> list = new ArrayList<>();

        /**
         * 获取所有一级分类名称
         */
        List<String> types = assetMapper.listByLevel(1);
        log.debug("获取所有一级分类的名称types：{}", types);


        for (int i = 0; i < types.size(); i++) {
            log.debug("开始封装第{}个分类的统计信息AssetStatisticDTO", i + 1);
            AssetStatisticDTO asp = new AssetStatisticDTO();
            asp.setType(types.get(i));
            /**
             * 获取该一级分类的资产总数
             */
            Integer num = assetMapper.numberSumQuery(types.get(i));
            asp.setNum(num);
            /**
             * 获取该一级分类的资产总值
             */
            Double worth = assetMapper.worthSumQuery(types.get(i));
            if (worth == null) worth = 0.0;
            log.debug("worth = {}", worth);
            asp.setWorth(worth);
            log.debug("封装完成：{}", asp);
            list.add(asp);
        }
        log.debug("封装完成：{}", list);

        return list;
    }

    @Override
    public AssetVO searchAsset(String keyword) {
        log.debug("开始处理【资产搜索】的请求,关键词：{}", keyword);

        return null;
    }

    @Override
    public int addNew(AssetAddDTO assetAddDTO) {
        log.debug("开始处理【资产录入】的业务");
        AssetCode assetCode = new AssetCode();
        AssetPO assetPO = new AssetPO();
        BeanUtils.copyProperties(assetAddDTO, assetPO);
        assetPO.setCode(assetCode.buildCode());
        assetPO.setUseStatus("在用");
        assetPO.setReviewStatus("审核中");
        assetPO.setSubmitDate(new Date());
        return assetMapper.insertNew(assetPO);
    }


    @Override
    public void assetChange(AssetChangeDTO assetChangeDTO) {
        log.debug("开始处理【资产变更】的业务，参数：{}", assetChangeDTO);
        int updateNum = assetMapper.assetUpdate(assetChangeDTO.getCode());

        AssetUpdatePO assetUpdatePO = new AssetUpdatePO();
        BeanUtils.copyProperties(assetChangeDTO, assetUpdatePO);
        assetUpdatePO.setReviewStatus("审核中");
        assetUpdatePO.setSubmitDate(new Date());

        assetMapper.idempotent(assetUpdatePO.getCode());//幂等
        int ReviewNum = assetMapper.ReviewAssetUpdate(assetUpdatePO);

        if (updateNum != 1 && ReviewNum != 1) {
            throw new ServiceException(StatusCode.OPERATION_FAILED, "资产变更申请提交失败！");
        }
    }

    @Override
    public void assetDelete(String code) {
        log.debug("开始处理【资产删除】的业务，参数：{}", code);
        AssetUpdatePO assetUpdatePO = new AssetUpdatePO();
        assetUpdatePO.setCode(code);
        assetUpdatePO.setReviewStatus("审核中");

        assetMapper.assetUpdate(code);

        int num = assetMapper.assetUpdate(code);
        if (num != 1) {
            throw new ServiceException(StatusCode.OPERATION_FAILED, "资产删除申请提交失败！");
        }
    }

    @Override
    public void addUpdate(AddUpdateDTO addUpdateDTO) {
        log.debug("开始处理【资产录入时修改】的业务");
        AssetPO assetPO = new AssetPO();
        BeanUtils.copyProperties(addUpdateDTO, assetPO);
        assetPO.setUseStatus("在用");
        assetPO.setReviewStatus("审核中");
        log.debug("assetPO类信息封装完毕:{}"+assetPO);
        assetMapper.updateAddInfo(assetPO);
    }

    @Override
    public void addDelete(String code) {
        log.debug("开始处理【资产录入时删除】的业务，参数：{}", code);
        assetMapper.addDelete(code);
    }
}
