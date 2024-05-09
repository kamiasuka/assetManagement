package cn.tedu.asset.attachment.service.impl;

import cn.tedu.asset.attachment.mapper.AttachmentMapper;
import cn.tedu.asset.attachment.pojo.dto.AssetDTO;
import cn.tedu.asset.attachment.pojo.dto.AttachmentPO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import cn.tedu.asset.attachment.pojo.vo.PageData;
import cn.tedu.asset.attachment.service.AttachmentService;
import cn.tedu.asset.attachment.util.AttachmentCode;
import cn.tedu.asset.attachment.util.PageInfoToPageDataConverter;
import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Value("${file-path}")
    private String dirPath;
    @Value("16")
    private Integer defaultQueryPageSize;

    @Override
    public String checkAsset(String code) {
        if (attachmentMapper.findByName(code) !=null) {
            return code;
        } else {
            return null;
        }
    }


    @Override
    public void insert(AssetDTO assetDTO) {
        AttachmentPO attachmentPO = new AttachmentPO();
        BeanUtils.copyProperties(assetDTO, attachmentPO);
        String code = AttachmentCode.buildCode();
        attachmentPO.setAttachmentCode(code);
        attachmentPO.setUpdatedTime(new Date());
        attachmentMapper.insertUnit(attachmentPO);
    }


    @Override
    public void deleteById(String code) {
        //通过id查询到图片路径
        String imgUrl = attachmentMapper.selectImgUrlById(code);
        System.out.println(imgUrl);
        //把路径对应的文件删除
        new File(dirPath + imgUrl).delete();
        int num = attachmentMapper.deleteById(code);
        if (num != 0){
            throw new ServiceException(StatusCode.OPERATION_FAILED);
        }
    }

    @Override
    public PageData<AttachmentPO> listAll(Integer pageNum) {
        PageHelper.startPage(pageNum, defaultQueryPageSize);
        Page<AttachmentPO> page = attachmentMapper.listAllAttachment();
        PageInfo<AttachmentPO> pageInfo = new PageInfo<>(page);
        PageData<AttachmentPO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }


    @Override
    public List<List> search2(ListVO listVO) {
        return attachmentMapper.search3(listVO);
    }


}



