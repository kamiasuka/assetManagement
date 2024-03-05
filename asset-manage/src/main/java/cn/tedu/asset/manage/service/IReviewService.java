package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.vo.AssetVO;

public interface IReviewService {
    AssetVO listAllAdd();

    AssetVO  listAllChange();

    void addNewOn(String code);

    void addNewOff(String code);

    void changeOff(String code);

    void changeOn(String code);

}
