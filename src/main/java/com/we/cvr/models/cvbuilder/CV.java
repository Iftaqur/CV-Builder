package com.we.cvr.models.cvbuilder;

import com.we.cvr.models.auth.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CV implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cvID;

    @ManyToOne
    private User user;

    @ManyToOne
    private Template template;

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    private String title;

    public CV(int cvID, User user, String title) {
        this.cvID = cvID;
        this.user = user;
        this.title = title;
    }

    public CV() {

    }

    public int getCvID() {
        return cvID;
    }

    public void setCvID(int cvID) {
        this.cvID = cvID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
