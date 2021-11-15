package com.mayank.authentication.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notice {

    @Id
    private String topic;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
