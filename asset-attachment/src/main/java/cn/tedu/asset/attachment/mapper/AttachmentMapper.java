package cn.tedu.asset.attachment.mapper;


import cn.tedu.asset.attachment.pojo.dto.AttachmentPO;
import cn.tedu.asset.attachment.pojo.vo.AssetVO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentMapper {
    int insertUnit(AttachmentPO unit);

    String selectImgUrlById(String code);

    int deleteById(String code);

    List<List> search3(ListVO listVO);

    AssetVO findByName(String code);

    Page<AttachmentPO> listAllAttachment();
}
