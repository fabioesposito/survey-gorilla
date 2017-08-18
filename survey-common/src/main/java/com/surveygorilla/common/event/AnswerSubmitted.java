package com.surveygorilla.common.event;

import java.util.Map;

/**
 * Created by fabio.pires on 18.08.17.
 */
public class AnswerSubmitted {
    private Integer pollID;

    private Map<Integer, Integer> answers;

    public AnswerSubmitted() {
    }

    public AnswerSubmitted(Integer pollID, Map<Integer, Integer> answers) {
        this.pollID = pollID;
        this.answers = answers;
    }

    public Integer getPollID() {
        return pollID;
    }

    public void setPollID(Integer pollID) {
        this.pollID = pollID;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Integer> answers) {
        this.answers = answers;
    }
}
