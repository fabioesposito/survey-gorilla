package com.surveygorilla.common;

import com.surveygorilla.common.exception.PollCreationException;

import java.util.Map;

/**
 * Created by fabio.pires on 16.08.17.
 */
public class Poll {

    private Integer pollID;

    private String question;

    private Map<Integer, String> options;

    private Map<Integer, Integer> answers;

    public Poll(Integer pollID, String question, Map<Integer, String> options, Map<Integer, Integer> answers) throws PollCreationException {

        if (pollID == null || question == null || options == null || answers == null) {
            throw new PollCreationException("Unable to create a new poll");
        }

        this.pollID = pollID;
        this.question = question;
        this.options = options;
        this.answers = answers;
    }

    public Integer getPollID() {
        return pollID;
    }

    public String getQuestion() {
        return question;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }
}
