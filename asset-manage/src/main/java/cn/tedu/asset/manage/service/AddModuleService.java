package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.AssetLeaseSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.AssetSaleSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.ContractDTO;
import cn.tedu.asset.manage.pojo.entity.AssetLease;
import cn.tedu.asset.manage.pojo.entity.AssetSale;
import cn.tedu.asset.manage.pojo.vo.*;

public interface AddModuleService {

    PageData<AssetSaleVO> getSaleAsset(Integer pageNum);

    void submitSaleAsset(AssetSaleSubmitSDTO assetSaleSubmitSDTO);

    PageData<AssetSaleVO> getSalePostAsset(Integer pageNum);

    void saleOn(String code);

    void saleOff(String code);

    PageData<AssetLease> getLeaseAsset(Integer pageNum);

    PageData<AssetLease> getPostLeaseAsset(Integer pageNum);

    void submitLeaseAsset(AssetLeaseSubmitSDTO assetLeaseSubmitSDTO);

    void leaseOn(String code);

    void leaseOff(String code);

    PageData<AssetVOO> getAsset(Integer pageNum);

    PageData<ContractVO> getContractSale(Integer pageNum);
    PageData<ContractVO> getContractLease(Integer pageNum);

    void submitContract(ContractDTO contractDTO);

    PageData<InComeVO> income(Integer pageNum);
}
