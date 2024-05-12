package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetAddVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IReviewService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Value("16")
    private Integer defaultQueryPageSize;

    @Autowired(required = false)
    private AssetMapper assetMapper;

    @Override
    public PageData<AssetAddVO> listAllAdd(Integer pageNum) {
        PageHelper.startPage(pageNum,defaultQueryPageSize);
        List<AssetAddVO> list = assetMapper.listAllAdd();
        PageInfo<AssetAddVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<AssetAddVO> listAllAddLog(Integer pageNum) {
        PageHelper.startPage(pageNum,defaultQueryPageSize);
        List<AssetAddVO> list = assetMapper.listAllAddLog();
        PageInfo<AssetAddVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }



    @Override
    public PageData<AssetAddVO> listAllChange(Integer pageNum) {
        PageHelper.startPage(pageNum,defaultQueryPageSize);
        List<AssetAddVO> list = assetMapper.listAllChange();
        PageInfo<AssetAddVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<AssetAddVO> listAllChangeLog(Integer pageNum) {
        PageHelper.startPage(pageNum,defaultQueryPageSize);
        List<AssetAddVO> list = assetMapper.listAllChangeLog();
        PageInfo<AssetAddVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public void addNewOn(String code) {
        AssetAddVO addNew = assetMapper.getAddNew(code);
        AssetPO assetPO = new AssetPO();
        BeanUtils.copyProperties(addNew,assetPO);
        assetPO.setReviewStatus("已通过");
        assetPO.setApprovalDate(new Date());
        assetMapper.updateAddInfo(assetPO);
        assetMapper.deleteAddNew(code);//幂等
        int num = assetMapper.saveAddNew(assetPO);
        if (num != 1){
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        }
    }

    @Override
    public void addNewOff(String code) {
//        AssetAddVO addNew = assetMapper.getAddNew(code);
//        AssetPO assetPO = new AssetPO();
//        BeanUtils.copyProperties(addNew,assetPO);
//        assetPO.setReviewStatus("已通过");
//        assetPO.setApprovalDate(new Date());
        int num = assetMapper.updateAddOff(code);
        if (num != 1){
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        }
    }

    @Override
    public void changeOn(String code) {
        AssetUpdateDTO assetUpdateDTO = assetMapper.getSubmitAsset(code);

        assetUpdateDTO.setReviewStatus("已通过");
        assetUpdateDTO.setApprovalDate(new Date());

        int num = assetMapper.saveSubmitAsset(assetUpdateDTO);
        if (assetUpdateDTO.getNote().equals("删除该资产")){
            assetMapper.delete(code);
        }

        assetMapper.updateChangeInfo(code);
        if (num != 1 ){
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        }
    }



    @Override
    public void changeOff(String code) {
        int num = assetMapper.updateChangeOff(code);
        int num2 = assetMapper.updateChangeOffInfo(code);
        if (num != 1 && num2!=1){
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        }
    }


}
