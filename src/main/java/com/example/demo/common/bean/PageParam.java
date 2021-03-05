package com.example.demo.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 页面参数
 * @date 2021/3/4 17:35
 **/
@Data
@NoArgsConstructor
public class PageParam {

    private Integer currentPage;
    private Integer pageSize;

    public PageParam(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage < 1 ? 1 : currentPage;
        this.pageSize = pageSize;
    }
}
