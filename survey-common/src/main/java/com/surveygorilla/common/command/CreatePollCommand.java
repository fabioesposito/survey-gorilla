package com.surveygorilla.common.command;

import java.time.Instant;
import java.util.Map;

/**
 * Created by fabio.pires on 17.08.17.
 */
public class CreatePollCommand {

    private Integer pollID = Instant.now().getNano();

    private String question;

    private Map<Integer, String> options;

    public Integer getPollID() {
        return pollID;
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
        return "CreatePollCommand{" +
                "pollID=" + pollID +
                ", question='" + question + '\'' +
                ", options=" + options +
                '}';
    }
}
