package com.example.demo.controller.vo;

import com.example.demo.mgr.bo.HomeworkBo;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description HomeworkListVo
 * @date 2021/2/22 14:21
 **/
@Data
public class LibraryHomeworkVo {

    private Long homeworkId;

    private String homeworkName;

    private String homeworkNotice;

    private String lastModifiedTime;


    public LibraryHomeworkVo convertFromHomeworkBo(HomeworkBo homeworkBo){
        LibraryHomeworkVo libraryHomeworkVo=new LibraryHomeworkVo();
        libraryHomeworkVo.setHomeworkId(homeworkBo.getHomeworkId());
        libraryHomeworkVo.setHomeworkName(homeworkBo.getHomeworkName());
        libraryHomeworkVo.setHomeworkNotice(homeworkBo.getHomeworkNotice());
        libraryHomeworkVo.setLastModifiedTime(homeworkBo.getLastModifiedTime());
        return libraryHomeworkVo;
    }


    public List<LibraryHomeworkVo> convertFromHomeworkBoList(List<HomeworkBo> homeworkBos){

        return homeworkBos.stream().map(this::convertFromHomeworkBo).collect(Collectors.toList());
    }
}
