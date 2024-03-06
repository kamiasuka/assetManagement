package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.entity.Dept;
import cn.tedu.asset.manage.pojo.vo.DeptVO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DeptMapper {
    List<DeptVO> listAll();

    int insert(Dept dept);

    int update(Dept dept);

    int delete(String code);

    DeptVO getDeptByCode(String code);
}
