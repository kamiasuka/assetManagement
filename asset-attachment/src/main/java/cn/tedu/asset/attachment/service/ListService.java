package cn.tedu.asset.attachment.service;

import cn.tedu.asset.attachment.pojo.dto.ListDTO;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;

import java.util.List;

public interface ListService {
        void insert(ListDTO unitDTO);

        void deleteById(Long id);


        List<ListAdminVO> listForAdmin();

    List<List> search2(ListVO listVO);
}
