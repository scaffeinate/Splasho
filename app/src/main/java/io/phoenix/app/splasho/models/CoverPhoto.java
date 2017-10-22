
package io.phoenix.app.splasho.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoverPhoto {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("width")
    @Expose
    private long width;
    @SerializedName("height")
    @Expose
    private long height;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("likes")
    @Expose
    private long likes;
    @SerializedName("liked_by_user")
    @Expose
    private boolean likedByUser;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
