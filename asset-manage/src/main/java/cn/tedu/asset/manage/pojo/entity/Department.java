package cn.tedu.asset.manage.pojo.entity;
import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // 部门名称

    @Column(name = "unit", nullable = false)
    private String unit; // 所属单位

    @Column(name = "code", nullable = false, unique = true)
    private String code; // 部门编码

    @Column(name = "phone", nullable = true)
    private String phone; // 部门电话

    @Column(name = "fax", nullable = true)
    private String fax; // 部门传真

    public void setId(Long id) {
    }

    // 省略getter和setter方法
}
