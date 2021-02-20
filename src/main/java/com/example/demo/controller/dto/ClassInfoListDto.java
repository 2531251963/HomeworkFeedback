package com.example.demo.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description 班级信息列表dto
 * @date 2021/2/20 14:12
 **/
@Data
public class ClassInfoListDto {

    @NotNull(message = "班级id不能为空")
    @Size(message = "班级id最少为1", min = 1)
    private List<Integer> classIds;

    private Boolean hasClassUserInfo;


    public boolean isHasClassUserInfo() {
        return hasClassUserInfo != null && hasClassUserInfo;
    }
}
