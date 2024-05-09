package cn.tedu.asset.attachment.service;

import cn.tedu.asset.attachment.pojo.dto.AssetDTO;
import cn.tedu.asset.attachment.pojo.dto.AttachmentPO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import cn.tedu.asset.attachment.pojo.vo.PageData;

import java.util.List;

public interface AttachmentService {
    void insert(AssetDTO unitDTO);

    void deleteById(String code);

    List<List> search2(ListVO listVO);

    String checkAsset(String code);

    PageData<AttachmentPO> listAll(Integer pageNum);
}
