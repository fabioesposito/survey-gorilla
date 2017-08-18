package com.surveygorilla.common.event;

import java.util.Map;

/**
 * Created by fabio.pires on 18.08.17.
 */
public class AnswerSubmitted {

    private Integer pollID;

    private Map<Integer, Integer> answers;

    private Map<Integer, Double> stats;

    public AnswerSubmitted() {
    }

    public AnswerSubmitted(Integer pollID, Map<Integer, Integer> answers, Map<Integer, Double> stats) {
        this.pollID = pollID;
        this.answers = answers;
        this.stats = stats;
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

    public Map<Integer, Double> getStats() {
        return stats;
    }

    public void setStats(Map<Integer, Double> stats) {
        this.stats = stats;
    }
}
