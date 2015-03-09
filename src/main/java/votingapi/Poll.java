package votingapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by Naresh on 3/6/2015.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Poll {

    public String id;
    public String question;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="CET")
    public Date started_at;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="CET")
    public Date expired_at;
    public String choice[];
    public Integer results[];

    public Poll() {
        this.question = "";
        this.started_at = null;
        this.id = "";
        this.expired_at = null;
        this.choice = null;
    }

    public Poll(String id,String question, Date started_at, Date expired_at, String[] choice) {
        this.question = question;
        this.started_at = started_at;
        this.id = id;
        this.expired_at = expired_at;
        this.choice = choice;
    }

    public Poll(String id, String question, Date started_at, Date expired_at, String[] choice, Integer[] results) {
        this.id = id;
        this.question = question;
        this.started_at = started_at;
        this.expired_at = expired_at;
        this.choice = choice;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Date started_at) {
        this.started_at = started_at;
    }

    public Date getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(Date expired_at) {
        this.expired_at = expired_at;
    }

    public Integer[] getResults() {
        return results;
    }

    public void setResults(Integer[] results) {
        this.results = results;
    }

    public String[] getChoice() {
        return choice;
    }

    public void setChoice(String[] choice) {
        this.choice = choice;
    }
}
