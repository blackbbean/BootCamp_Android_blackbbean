package com.example.android.boostcamp;

/**
 * Created by Minjeong Kim on 2018-12-05.
 */

public class Movie {
    private String image;
    private String title;
    private int userRating;
    private String pubDate;
    private String director;
    private String actor;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}

