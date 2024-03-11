package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.DeptAddDTO;
import cn.tedu.asset.manage.pojo.dto.DeptQFDTO;
import cn.tedu.asset.manage.pojo.dto.DeptUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.DeptVO;

import java.util.List;

public interface IDeptService {
    /**
     * 获取所有部门信息
     * @return
     */
    List<DeptVO> listAll();

    /**
     * 新增部门
     * @param deptAddDTO
     * @return
     */
    int addNew(DeptAddDTO deptAddDTO);

    /**
     * 修改部门
     * @param deptUpdateDTO
     * @return
     */
    int deptUpdate(DeptUpdateDTO deptUpdateDTO);

    /**
     * 删除部门
     * @param code
     * @return
     */
    int deptDelete(String code);

    /**
     * 通过部门编码获取部门
     * @param code
     * @return
     */
    DeptVO getDeptByCode(String code);

    /**
     * 根据查询条件过滤出部门信息
     * @param deptQFDTO
     * @return
     */
    List<DeptVO> queryFilter(DeptQFDTO deptQFDTO);
}
