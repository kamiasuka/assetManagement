package cn.tedu.asset.attachment.mapper;


import cn.tedu.asset.attachment.pojo.entity.AList;
import cn.tedu.asset.attachment.pojo.vo.ListAdminVO;
import cn.tedu.asset.attachment.pojo.vo.ListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListMapper {
    int insertUnit(AList unit);

    String selectImgUrlById(Long id);

    void deleteById(Long id);

    List<ListAdminVO> selectForAdmin();

    List<List> search3(ListVO listVO);
}
