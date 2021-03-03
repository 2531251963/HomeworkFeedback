package com.example.demo.mgr.bo;

import com.example.demo.common.util.BigDecimalUtil;
import com.example.demo.mgr.bo.problem.AbstractProblemBo;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 作业详情bo
 * @date 2021/2/25 18:32
 **/
@Data
public class LibraryHomeworkDetailBo {

    private HomeworkBo homeworkBo;

    private List<Rules> rules;

    private Double totalScore = 0.0;

    private List<AbstractProblemBo> problems;

    @Data
    public static class Rules {

        private Integer problemType;

        private Integer num;

        private Double score = 0.0;

    }

    public void assembleData(HomeworkBo homeworkBo, List<AbstractProblemBo> abstractProblemBos) {
        this.homeworkBo = homeworkBo;
        this.problems = abstractProblemBos;
        this.rules = new LinkedList<>();

        Map<Integer, List<AbstractProblemBo>> map = abstractProblemBos.stream().collect(Collectors.groupingBy(AbstractProblemBo::getProblemType));
        map.keySet().forEach(x -> {
            Rules rules = new Rules();
            List<AbstractProblemBo> abstractProblemBosGroup = map.get(x);
            rules.setNum(abstractProblemBosGroup.size());
            rules.setProblemType(x);

            abstractProblemBosGroup.forEach(abstractProblemBo -> {
                rules.setScore(BigDecimalUtil.sum(rules.getScore(), abstractProblemBo.getScore()));
            });
            this.totalScore=BigDecimalUtil.sum(this.totalScore,rules.getScore());
            this.rules.add(rules);
        });
    }
}
