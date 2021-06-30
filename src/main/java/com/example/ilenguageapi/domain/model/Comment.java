package com.example.ilenguageapi.domain.model;

import com.mysql.cj.conf.PropertyDefinitions;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = true)
    private User user;

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Comment setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Comment setUser(User user) {
        this.user = user;
        return this;
    }
}
