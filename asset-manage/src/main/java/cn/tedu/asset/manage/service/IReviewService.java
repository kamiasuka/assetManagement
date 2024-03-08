package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.vo.AssetAddVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;

import java.util.List;

public interface IReviewService {
    PageData<AssetAddVO> listAllAdd(Integer pageNum);

    PageData<AssetAddVO>  listAllChange(Integer pageNum);

    void addNewOn(String code);

    void addNewOff(String code);

    void changeOff(String code);

    void changeOn(String code);

}
