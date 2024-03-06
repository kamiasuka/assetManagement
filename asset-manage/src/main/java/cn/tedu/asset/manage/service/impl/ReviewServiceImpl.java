package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.service.IReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements IReviewService {
    @Autowired(required = false)
    private AssetMapper assetMapper;

    @Override
    public AssetVO listAllAdd() {
        return assetMapper.listAllAdd();
    }

    @Override
    public AssetVO listAllChange() {
        return assetMapper.listAllChange();
    }

    @Override
    public void addNewOn(String code) {

    }

    @Override
    public void addNewOff(String code) {

    }

    @Override
    public void changeOn(String code) {
        AssetUpdateDTO assetUpdateDTO = assetMapper.getSubmitAsset(code);
        assetUpdateDTO.setReviewStatus("已通过");
        assetMapper.saveSubmitAsset(assetUpdateDTO);
    }

    @Override
    public void changeOff(String code) {
        AssetUpdateDTO assetUpdateDTO = assetMapper.getSubmitAsset(code);

        AssetUpdatePO assetUpdatePO = new AssetUpdatePO();
        BeanUtils.copyProperties(assetUpdateDTO, assetUpdatePO);
        assetUpdatePO.setReviewStatus("未通过");
        assetMapper.ReviewAssetUpdate(assetUpdatePO);
    }


}
