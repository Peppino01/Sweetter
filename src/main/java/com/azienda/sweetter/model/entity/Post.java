package com.azienda.sweetter.model.entity;

import com.azienda.sweetter.model.entity.dto.PostDTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Timestamp publicationDate;

    @Column(nullable = false)
    private Timestamp lastModifiedDate;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User postOwner;

    @ManyToMany
    @JoinTable(
            name = "Likes",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> likesList;

    @ManyToMany
    @JoinTable(
            name = "Unlikes",
            joinColumns = {@JoinColumn(name = "postId")},
            inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> unlikesList;

    public Post() {

    }

    public Post(String title, String text, Timestamp publicationDate, Timestamp lastModifiedDate, User postOwner) {
        this.title = title;
        this.text = text;
        this.publicationDate = publicationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.postOwner = postOwner;
    }

    public Post(PostDTO postDTO, Timestamp publicationDate, Timestamp lastModifiedDate, User postOwner) {
        this.title = postDTO.getTitle();
        this.text = postDTO.getText();
        this.publicationDate = publicationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isActive = true;
        this.postOwner = postOwner;
    }

    public static List<PostDTO>  toListPostDTO(List<Post> postList){
         return postList.stream().map(p ->
             new PostDTO(p.getPostOwner().getUsername(), null, p.getTitle(), p.getText(), null,
             p.getPublicationDate(), p.getLastModifiedDate())
         ).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsActive() { return isActive; }

    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Timestamp getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
    }

    public List<User> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<User> likesList) {
        this.likesList = likesList;
    }

    public List<User> getUnlikesList() {
        return unlikesList;
    }

    public void setUnlikesList(List<User> unlikesList) {
        this.unlikesList = unlikesList;
    }
}
