package cn.tedu.asset.manage.service;
import cn.tedu.asset.manage.pojo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepositoryService extends JpaRepository<Department, Long> {
    // 这里可以添加自定义查询方法，根据部门名称查找部门，还没解决
}