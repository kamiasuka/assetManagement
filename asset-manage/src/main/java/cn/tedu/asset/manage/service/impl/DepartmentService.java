package cn.tedu.asset.manage.service.impl;
import cn.tedu.asset.manage.pojo.entity.Department;
import cn.tedu.asset.manage.service.DepartmentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepositoryService departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepositoryService departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        departmentRepository.save(department);
        return department;
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


}
