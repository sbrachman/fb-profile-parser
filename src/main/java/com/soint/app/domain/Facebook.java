package com.soint.app.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.soint.app.json.deserializer.MilisecondsToInstantDeserializer;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Facebook {

    private String id;

    @JsonDeserialize(using = MilisecondsToInstantDeserializer.class)
    private Instant birthday;
    private String firstname;
    private String lastname;
    private String occupation;
    private String gender;
    private City city;
    private String work;
    private List<String> friends;
    private String school;
    private String location;
    private String relationship;
    private List<Post> posts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Facebook{" +
                "id='" + id + '\'' +
                ", birthday=" + birthday +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", occupation='" + occupation + '\'' +
                ", gender='" + gender + '\'' +
                ", city=" + city +
                ", work='" + work + '\'' +
                ", friends=" + friends +
                ", school='" + school + '\'' +
                ", location='" + location + '\'' +
                ", relationship='" + relationship + '\'' +
                ", posts=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facebook facebook = (Facebook) o;
        return Objects.equals(id, facebook.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
