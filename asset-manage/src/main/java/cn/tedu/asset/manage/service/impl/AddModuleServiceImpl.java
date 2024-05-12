package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.persist.mapper.AddModuleMapper;
import cn.tedu.asset.manage.pojo.dto.AssetLeaseSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.AssetSaleSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.ContractDTO;
import cn.tedu.asset.manage.pojo.entity.AssetLease;
import cn.tedu.asset.manage.pojo.entity.AssetSale;
import cn.tedu.asset.manage.pojo.entity.Contract;
import cn.tedu.asset.manage.pojo.entity.InCome;
import cn.tedu.asset.manage.pojo.vo.*;
import cn.tedu.asset.manage.service.AddModuleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class AddModuleServiceImpl implements AddModuleService {
    @Autowired(required = false)
    private AddModuleMapper addModuleMapper;
    @Value("16")
    private Integer defaultQueryPageSize;
    /**
     * 资产出借
     * @param pageNum
     * @return
     */
    @Override
    public PageData<AssetLease> getLeaseAsset(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AssetLease> page = addModuleMapper.getLeaseAsset();
        PageInfo<AssetLease> pageInfo = new PageInfo<>(page);
        PageData<AssetLease> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
    @Override
    public PageData<AssetLease> getPostLeaseAsset(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AssetLease> page = addModuleMapper.getPostLeaseAsset();
        PageInfo<AssetLease> pageInfo = new PageInfo<>(page);
        PageData<AssetLease> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public void submitLeaseAsset(AssetLeaseSubmitSDTO assetLeaseSubmitSDTO) {
        AssetLease assetLease = new AssetLease();
        BeanUtils.copyProperties(assetLeaseSubmitSDTO,assetLease);
        assetLease.setApprovalStatus("未审核");
        assetLease.setLeaseDate(new Date());
        addModuleMapper.submitleaseAsset(assetLease);
    }

    @Override
    public void leaseOn(String code) {
        int num = addModuleMapper.saveLeaseResult(code);
        if (num != 1)
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        else {
            AssetLease assetLease = addModuleMapper.getAssetByCode(code);
            InCome inCome = new InCome();
            inCome.setIncomeType("资产租借");
            inCome.setAssetCode(assetLease.getAssetCode());
            inCome.setAssetName(assetLease.getAssetName());
            inCome.setIncomeAmount(assetLease.getRevenueMoney());
            addModuleMapper.insert(inCome);
            addModuleMapper.updateLeaseAsset(code);
        }
    }

    @Override
    public void leaseOff(String code) {
        int num = addModuleMapper.leasePostOff(code);
        if (num != 1)
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");

    }


    /**
     * 资产变卖
     * @param pageNum
     * @return
     */
    @Override
    public PageData<AssetVOO> getAsset(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AssetVOO> page = addModuleMapper.getAsset();
        PageInfo<AssetVOO> pageInfo = new PageInfo<>(page);
        PageData<AssetVOO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }



    @Override
    public PageData<AssetSaleVO> getSaleAsset(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AssetSaleVO> page = addModuleMapper.getSaleAsset();
        PageInfo<AssetSaleVO> pageInfo = new PageInfo<>(page);
        PageData<AssetSaleVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
    //<!--提交变卖申请-->
    @Override
    public void submitSaleAsset(AssetSaleSubmitSDTO assetSaleSubmitSDTO) {
        AssetSale assetSale = new AssetSale();
        BeanUtils.copyProperties(assetSaleSubmitSDTO,assetSale);
        assetSale.setSaleTime(new Date());
        assetSale.setApprovalStatus("未审核");
        addModuleMapper.submitSaleAsset(assetSale);
    }
//获取变卖审核列表
    @Override
    public PageData<AssetSaleVO> getSalePostAsset(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AssetSaleVO> page = addModuleMapper.getSalePostAsset();
        PageInfo<AssetSaleVO> pageInfo = new PageInfo<>(page);
        PageData<AssetSaleVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public void saleOn(String code) {
        AssetSale assetSale = addModuleMapper.getSaleAssetInfo(code);
        int num = addModuleMapper.saveResult(code);
        if (num != 1)
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");
        else {
            InCome inCome = new InCome();
            inCome.setIncomeType("资产租借");
            inCome.setAssetCode(assetSale.getAssetCode());
            inCome.setAssetName(assetSale.getAssetName());
            inCome.setIncomeAmount(assetSale.getRevenueMoney());
            addModuleMapper.insert(inCome);
            addModuleMapper.deleteAsset(code);
        }
    }

    @Override
    public void saleOff(String code) {
        int num = addModuleMapper.salePostOff(code);
        if (num != 1)
            throw new ServiceException(StatusCode.OPERATION_FAILED,"操作失败！");

    }

    /**
     * 合同管理
     * @param pageNum
     * @return
     */
    @Override
    public PageData<ContractVO> getContractSale(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<ContractVO> page = addModuleMapper.getContractSale();
        PageInfo<ContractVO> pageInfo = new PageInfo<>(page);
        PageData<ContractVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public PageData<ContractVO> getContractLease(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<ContractVO> page = addModuleMapper.getContractLease();
        PageInfo<ContractVO> pageInfo = new PageInfo<>(page);
        PageData<ContractVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public void submitContract(ContractDTO contractDTO) {
        System.out.println("contractDTO:"+contractDTO);
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO,contract);
        if (contractDTO.getContractType().equals("变卖合同")){
            contract.setSigningDate(new Date());
            addModuleMapper.submitSaleContract(contract);
        }else {
            addModuleMapper.submitLeaseContract(contract);
        }
    }

    @Override
    public PageData<InComeVO> income(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<InComeVO> page = addModuleMapper.income();
        System.out.println("page:"+page);
        PageInfo<InComeVO> pageInfo = new PageInfo<>(page);
        PageData<InComeVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }
}
