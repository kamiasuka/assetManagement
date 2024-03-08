package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class AddUpdateDTO {
    private String code;
    private String name;
    private String type;
    private String maxType;
    private String dept;
    private String unit;
    private int life;
    private double amount;
}
