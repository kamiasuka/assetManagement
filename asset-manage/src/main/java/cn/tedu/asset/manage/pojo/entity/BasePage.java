package cn.tedu.asset.manage.pojo.entity;

import java.util.List;

public class BasePage {

    //当前页码
    private Integer pageNum=1;
    //总页数
    private Integer pageTotal;
    //每页的条数
    private Integer pageSize=10;
    //总条数
    private Integer totalSize;
    //起始索引
    private Integer startIndex;
    //是否有更多
    private boolean isMore;
    private List list;

    public BasePage(){
        super();
    }

    public BasePage(Integer  totalSize,Integer pageNum,Integer pageSize){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.totalSize=totalSize;
        this.pageTotal=(this.totalSize+this.pageSize-1)/this.pageSize;
        this.startIndex=(this.pageNum-1)*pageSize;
        this.isMore=this.pageNum<this.pageTotal;
    }
}
