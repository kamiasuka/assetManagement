package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.dto.ContractDTO;
import cn.tedu.asset.manage.pojo.entity.AssetLease;
import cn.tedu.asset.manage.pojo.entity.AssetSale;
import cn.tedu.asset.manage.pojo.entity.Contract;
import cn.tedu.asset.manage.pojo.entity.InCome;
import cn.tedu.asset.manage.pojo.vo.*;
import com.github.pagehelper.Page;

public interface AddModuleMapper {
    Page<AssetLease> getLeaseAsset();

    Page<AssetSaleVO> getSaleAsset();

    int submitSaleAsset(AssetSale assetSale);

    Page<AssetSaleVO> getSalePostAsset();

    AssetSale getSaleAssetInfo(String code);

    int saveResult(String code);

    int deleteAsset(String code);

    int salePostOff(String code);

    Page<AssetLease> getPostLeaseAsset();

    void submitleaseAsset(AssetLease assetLease);

    int saveLeaseResult(String code);

    void updateLeaseAsset(String code);

    int leasePostOff(String code);

    Page<AssetVOO> getAsset();

    Page<ContractVO> getContractSale();
    Page<ContractVO> getContractLease();

    void submitSaleContract(Contract contractDTO);

    void submitLeaseContract(Contract contractDTO);

    Page<InComeVO> income();

    AssetLease getAssetByCode(String code);

    void insert(InCome inCome);
}
