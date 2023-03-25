package edu.uet.signlanguage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "sentences")
public class Sentence {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    private Timestamp viewTime;
    private boolean favor = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    public Sentence(int id, String content, Timestamp viewTime, Boolean favor) {
        this.id = id;
        this.content = content;
        this.viewTime = viewTime;
        this.favor = favor;
    }
    public Sentence(Sentence sentence) {
        this.content = sentence.getContent();
        this.viewTime = sentence.getViewTime();
        this.favor = sentence.getFavor();
    }

    public Sentence() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFavor(boolean favor) {
        this.favor = favor;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setViewTime(Timestamp viewTime) {
        this.viewTime = viewTime;
    }

    public void setFavor(Boolean favor) {
        this.favor = favor;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getViewTime() {
        return viewTime;
    }

    public Boolean getFavor() {
        return favor;
    }
}
