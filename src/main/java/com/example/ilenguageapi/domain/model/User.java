package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Lob
    @Size(max = 245)
    private String description;

    @NotNull
    private double media;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    public boolean hasTheCommentOf(Comment comment) {
        return this.getComments().contains(comment);
    }

    public User addComment(Comment comment) {
        if (!this.hasTheCommentOf(comment)) {
            this.getComments().add(comment);
        }
        return this;
    }

    public User removeComment(Comment comment) {
        if (this.hasTheCommentOf(comment)) {
            this.getComments().remove(comment);
        }
        return this;
    }
    public double getRatingMedia() {
        List<Comment> comments = getComments();
        double newMedia = 0;
        for (int i = 0; i < comments.size(); i++) {
            newMedia += comments.get(i).getRating();
        }
        this.media = newMedia / comments.size();
        return this.media;
    }
    // Session relationship

    public List<Session> getSessions() {
        return sessions;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "users")
    private List<Session> sessions;

    // ! Session relationship

    public User() {
    }

    public User(@NotNull String name, @NotNull String lastName, @NotNull String email, @NotNull String password, @NotNull String description, @NotNull double media) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.description = description;
        this.media = media;
    }

    public User setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    public List<Badget> getBadgets() {
        return badgets;
    }

    public User setBadgets(List<Badget> badgets) {
        this.badgets = badgets;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_badgets",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "badget_id")})
    private List<Badget> badgets;

    public boolean hasTheBadgetOf(Badget badget) {
        return this.getBadgets().contains(badget);
    }

    public User addBadget(Badget badget) {
        if (!this.hasTheBadgetOf(badget)) {
            this.getBadgets().add(badget);
        }
        return this;
    }

    public User removeBadget(Badget badget) {
        if (this.hasTheBadgetOf(badget)) {
            this.getBadgets().remove(badget);
        }
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "role_id", nullable = true)
    private Role role;

    public boolean isUserWithRole(String roleName) {
        return getRole().name.equals(roleName);
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserSubscription> subscriptions;

    public boolean isSubscribedWith(Subscription subscription) {
        return this.getSubscriptions().contains(subscription);
    }

    /* public User SubscribeWith(UserSubscription subscription){
        if(!isSubscribedWith(subscription)){
           this.getSubscriptions().add(subscription);
        }
        return this;
     }*/
    public UserSubscription getSubcriptionActive() {
        int size = this.getSubscriptions().size();
        return this.getSubscriptions().get(size - 1);
    }

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_topics",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")})
    List<TopicOfInterest> topicOfInterests;

    public boolean hasTheTopicOf(TopicOfInterest topicOfInterest) {
        return this.getTopicOfInterests().contains(topicOfInterest);
    }

    public User addTopicOfInterest(TopicOfInterest topicOfInterest) {
        if (!this.hasTheTopicOf(topicOfInterest)) {
            this.getTopicOfInterests().add(topicOfInterest);
        }
        return this;
    }

    public User removeTopicOfInterest(TopicOfInterest topicOfInterest) {
        if (this.hasTheTopicOf(topicOfInterest)) {
            this.getTopicOfInterests().remove(topicOfInterest);
        }
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_languages",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "language_id")})
    List<LanguageOfInterest> languageOfInterests;

    public boolean hasTheLenguageOf(LanguageOfInterest languageOfInterest) {
        return this.getLanguageOfInterests().contains(languageOfInterest);
    }

    public User addLanguageOfInterest(LanguageOfInterest languageOfInterest) {
        if (!this.hasTheLenguageOf(languageOfInterest)) {
            this.getLanguageOfInterests().add(languageOfInterest);
        }
        return this;
    }

    public User removeLanguageOfInterest(LanguageOfInterest languageOfInterest) {
        if (this.hasTheLenguageOf(languageOfInterest)) {
            this.getLanguageOfInterests().remove(languageOfInterest);
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<UserSubscription> getSubscriptions() {
        return subscriptions;
    }

    public User setSubscriptions(List<UserSubscription> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public Role getRole() {
        return this.role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<TopicOfInterest> getTopicOfInterests() {
        return topicOfInterests;
    }

    public User setTopicOfInterests(List<TopicOfInterest> topicOfInterests) {
        this.topicOfInterests = topicOfInterests;
        return this;
    }

    public List<LanguageOfInterest> getLanguageOfInterests() {
        return languageOfInterests;
    }

    public User setLanguageOfInterests(List<LanguageOfInterest> languageOfInterests) {
        this.languageOfInterests = languageOfInterests;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public User setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public double getMedia() {
        return media;
    }

    public User setMedia(double media) {
        this.media = media;
        return this;
    }
}
