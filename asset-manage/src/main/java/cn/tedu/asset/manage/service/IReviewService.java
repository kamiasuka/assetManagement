package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.util.List;

public interface IReviewService {
    List<AssetVO> listAllAdd();

    List<AssetVO>  listAllChange();

    void addNewOn(String code);

    void addNewOff(String code);

    void changeOff(String code);

    void changeOn(String code);

}
