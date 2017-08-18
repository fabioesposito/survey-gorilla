package com.surveygorilla.common.event;

import java.util.Map;

/**
 * Created by fabio.pires on 17.08.17.
 */
public class PollCreatedEvent {

    private Integer pollID;

    private String question;

    private Map<Integer, String> options;

    public PollCreatedEvent() {
    }

    public PollCreatedEvent(Integer pollID, String question, Map<Integer, String> options) {
        this.pollID = pollID;
        this.question = question;
        this.options = options;
    }

    public Integer getPollID() {
        return pollID;
    }

    public void setPollID(Integer pollID) {
        this.pollID = pollID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer, String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "PollCreatedEvent{" +
                "pollID=" + pollID +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
