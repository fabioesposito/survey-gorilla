package com.surveygorilla.common.command;

import java.util.List;

/**
 * Created by fabio.pires on 18.08.17.
 */
public class SubmitAnswerCommand {

    private Integer pollID;

    private List<Integer> answers;

    public Integer getPollID() {
        return pollID;
    }

    public void setPollID(Integer pollID) {
        this.pollID = pollID;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
