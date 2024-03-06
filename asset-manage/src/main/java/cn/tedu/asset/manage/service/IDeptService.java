package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.DeptAddDTO;
import cn.tedu.asset.manage.pojo.dto.DeptUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.DeptVO;

import java.util.List;

public interface IDeptService {
    List<DeptVO> listAll();

    int addNew(DeptAddDTO deptAddDTO);

    int deptUpdate(DeptUpdateDTO deptUpdateDTO);

    int deptDelete(String code);

    DeptVO getDeptByCode(String code);
}
