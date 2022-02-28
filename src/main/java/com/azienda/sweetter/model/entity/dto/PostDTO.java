package com.azienda.sweetter.model.entity.dto;


import com.azienda.sweetter.model.entity.Post;

import java.sql.Timestamp;

public class PostDTO extends LoginDTO {
    private String title, text, stringLike;
    private Timestamp publicationDate, lastModifiedDate;

    public PostDTO() {}

    public PostDTO(String username, String password, String title, String text, String stringLike,
                   Timestamp publicationDate, Timestamp lastModifiedDate) {
        super(username, password);
        this.title = title;
        this.text = text;
        this.stringLike = stringLike;
        this.publicationDate = publicationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public PostDTO(LoginDTO loginDTO, Post post) {
        super(loginDTO);
        this.title = post.getTitle();
        this.text = post.getText();
        this.publicationDate = post.getPublicationDate();
        this.lastModifiedDate = post.getLastModifiedDate();
    }

    public String getStringLike() { return stringLike; }

    public void setStringLike(String stringLike) { this.stringLike = stringLike; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getPublicationDate() { return publicationDate; }

    public void setPublicationDate(Timestamp publicationDate) { this.publicationDate = publicationDate; }

    public Timestamp getLastModifiedDate() { return lastModifiedDate; }

    public void setLastModifiedDate(Timestamp lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
}
