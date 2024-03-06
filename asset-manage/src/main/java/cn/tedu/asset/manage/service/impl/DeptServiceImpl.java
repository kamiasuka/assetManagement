package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.manage.dao.persist.mapper.DeptMapper;
import cn.tedu.asset.manage.pojo.dto.DeptAddDTO;
import cn.tedu.asset.manage.pojo.dto.DeptUpdateDTO;
import cn.tedu.asset.manage.pojo.entity.Dept;
import cn.tedu.asset.manage.pojo.vo.DeptVO;
import cn.tedu.asset.manage.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DeptVO getDeptByCode(String code) {
        return deptMapper.getDeptByCode(code);
    }

    @Override
    public List<DeptVO> listAll() {
        return deptMapper.listAll();
    }

    @Override
    public int addNew(DeptAddDTO deptAddDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptAddDTO,dept);
        return deptMapper.insert(dept);
    }

    @Override
    public int deptUpdate(DeptUpdateDTO deptUpdateDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptUpdateDTO,dept);
        return deptMapper.update(dept);
    }

    @Override
    public int deptDelete(String code) {
        return deptMapper.delete(code);
    }
}
