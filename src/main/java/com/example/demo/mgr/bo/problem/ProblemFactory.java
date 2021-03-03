package com.example.demo.mgr.bo.problem;

/**
 * @description 题目工厂
 * @date 2021/2/24 15:02
 **/
public class ProblemFactory {

    /**
     * 单选 singleChoice
     */
    private static final int singleChoice = 1;

    /**
     * 多选 multipleChoice
     */
    private static final int multipleChoice = 2;

    /**
     * 单选 fillBlanks
     */
    private static final int fillBlanks = 3;

    /**
     * 单选 subjective
     */
    private static final int subjective = 4;

    public static AbstractProblemBo getProblemBo(Integer problemType) {
        AbstractProblemBo problemBo = null;
        switch (problemType) {
            case singleChoice:
                problemBo = new SingleChoiceProblemBo();
                break;
            case multipleChoice:
                problemBo = new MultipleChoiceProblemBo();
                break;
            case fillBlanks:
                problemBo = new FillBlanksProblemBo();
                break;
            case subjective:
                problemBo = new SubjectiveProblemBo();
                break;
            default:
                break;
        }
        return problemBo;
    }

}
