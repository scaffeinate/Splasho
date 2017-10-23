
package io.phoenix.app.splasho.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
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
    private Object description;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("current_user_collections")
    @Expose
    private List<Object> currentUserCollections = null;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;
    public final static Creator<Photo> CREATOR = new Creator<Photo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return (new Photo[size]);
        }

    };

    protected Photo(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.width = ((long) in.readValue((long.class.getClassLoader())));
        this.height = ((long) in.readValue((long.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.likes = ((long) in.readValue((long.class.getClassLoader())));
        this.likedByUser = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
        this.user = ((User) in.readValue((User.class.getClassLoader())));
        in.readList(this.currentUserCollections, (Object.class.getClassLoader()));
        this.urls = ((Urls) in.readValue((Urls.class.getClassLoader())));
        in.readList(this.categories, (Object.class.getClassLoader()));
    }

    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
        dest.writeValue(width);
        dest.writeValue(height);
        dest.writeValue(color);
        dest.writeValue(likes);
        dest.writeValue(likedByUser);
        dest.writeValue(description);
        dest.writeValue(user);
        dest.writeList(currentUserCollections);
        dest.writeValue(urls);
        dest.writeList(categories);
    }

    public int describeContents() {
        return 0;
    }
}
