package cn.tedu.asset.attachment.service.impl;

import cn.tedu.asset.attachment.mapper.ListMapper;
import cn.tedu.asset.attachment.pojo.dto.AssetDTO;
import cn.tedu.asset.attachment.pojo.dto.ListDTO;
import cn.tedu.asset.attachment.pojo.entity.AList;
import cn.tedu.asset.attachment.pojo.vo.AssetVO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import cn.tedu.asset.attachment.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    ListMapper mapper;

    @Override
    public void insert(ListDTO listDTO) {
        AList alist =new AList();
        BeanUtils.copyProperties(listDTO,alist);
        alist.setUpdatedTime(new Date());
        alist.setStatus("1");
        mapper.insertUnit(alist);
//        if (listDTO.getId()==null){//新增
//            mapper.insertUnit(alist);
//        }else{//修改
//            mapper.update(alist);
//        }
    }

    @Value("${file-path}")
    private String dirPath;


    @Override
    public void deleteById(Long id) {
        //通过id查询到图片路径
        String imgUrl = mapper.selectImgUrlById(id);
        System.out.println(imgUrl);
        //把路径对应的文件删除
        new File(dirPath+imgUrl).delete();

        mapper.deleteById(id);

    }

    @Override
    public List<ListAdminVO> listForAdmin() {
        return mapper.selectForAdmin();
    }

    @Override
    public List<List> search2(ListVO listVO) {
        return mapper.search3(listVO);
    }

    @Override
    public Long checkAsset(AssetDTO assetDTO) {
        AssetVO assetVO = mapper.findByName(assetDTO);
        if (assetVO != null) {
            return assetVO.getId();
        } else {
            return null;
        }
    }


}



