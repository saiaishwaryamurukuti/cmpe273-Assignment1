package votingapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Created by Naresh on 3/6/2015.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Moderator {

    public Integer id;
    public String name;
    public String email;
    public String password;
    public List<Poll> polls;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="CET")
    public Date created_at;

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    public Moderator()
    {
        this.id = 0;
        this.name = "";
        this.email = "";
        this.password = "";
        this.created_at = new Date();
        this.polls=null;
    }
    public Moderator(Integer id, String name, String email, String password, Date created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.polls=null;
    }

    public Moderator(Integer id, String name, String email, String password, Date created_at, List<Poll> polls) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.polls = polls;
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
