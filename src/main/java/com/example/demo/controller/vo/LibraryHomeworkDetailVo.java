package com.example.demo.controller.vo;

import com.example.demo.mgr.bo.HomeworkBo;
import com.example.demo.mgr.bo.LibraryHomeworkDetailBo;
import com.example.demo.mgr.bo.problem.AbstractProblemBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 作业库中的作业详情
 * @date 2021/2/25 17:33
 **/
@Data
public class LibraryHomeworkDetailVo {

    private LibraryHomeworkVo homework;

    private List<Rules> rules;

    private Double totalScore;

    private List<ProblemVo> problems;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rules {

        private Integer problemType;

        private Integer num;

        private Double score;
    }

    public LibraryHomeworkDetailVo convertLibraryHomeworkDetailBo(LibraryHomeworkDetailBo libraryHomeworkDetailBo) {
        LibraryHomeworkDetailVo libraryHomeworkDetailVo = new LibraryHomeworkDetailVo();
        libraryHomeworkDetailVo.setHomework(new LibraryHomeworkVo().convertFromHomeworkBo(libraryHomeworkDetailBo.getHomeworkBo()));
        libraryHomeworkDetailVo.setRules(libraryHomeworkDetailBo.getRules().stream().map(x -> new Rules(x.getNum(), x.getProblemType(), x.getScore())).collect(Collectors.toList()));
        libraryHomeworkDetailVo.setTotalScore(libraryHomeworkDetailBo.getTotalScore());
        libraryHomeworkDetailVo.setProblems(libraryHomeworkDetailBo.getProblems().stream().map(x->new ProblemVo(x.getProblemId(),x.getProblemType(),x.getContent())).collect(Collectors.toList()));
        return libraryHomeworkDetailVo;
    }

}
