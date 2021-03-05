package com.example.demo.common.bean;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 统一返回分页信息
 **/
@Data
@Accessors(chain = true)
public class PageResultInfo<T> {

    //当前查询页面
    private Integer currentPage;
    //每页条数
    private Integer pageSize;
    //总条数
    private Long total;
    //总页数
    private Integer pages;
    //本页数据
    private List<T> list;
    //是否最后一页
    private Boolean isLastPage;

    public PageResultInfo(PageInfo pageInfo,List<T> list){
        this.currentPage = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();
        this.isLastPage = pageInfo.isIsLastPage();
        this.list=list;
    }

    public PageResultInfo(PageResultInfo pageResultInfo,List<T> list){
        this.currentPage = pageResultInfo.getCurrentPage();
        this.pageSize = pageResultInfo.getPageSize();
        this.total = pageResultInfo.getTotal();
        this.pages = pageResultInfo.getPages();
        this.isLastPage = pageResultInfo.getIsLastPage();
        this.list=list;
    }

}
