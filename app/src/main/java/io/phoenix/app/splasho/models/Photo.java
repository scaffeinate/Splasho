
package io.phoenix.app.splasho.models;

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
    @SerializedName("links")
    @Expose
    private Links links;
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
        this.links = ((Links) in.readValue((Links.class.getClassLoader())));
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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
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
        dest.writeValue(links);
    }

    public int describeContents() {
        return 0;
    }

    public static class Links implements Parcelable {

        @SerializedName("self")
        @Expose
        private String self;
        @SerializedName("html")
        @Expose
        private String html;
        @SerializedName("download")
        @Expose
        private String download;
        @SerializedName("download_location")
        @Expose
        private String downloadLocation;
        public final static Creator<Links> CREATOR = new Creator<Links>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Links createFromParcel(Parcel in) {
                return new Links(in);
            }

            public Links[] newArray(int size) {
                return (new Links[size]);
            }

        };

        protected Links(Parcel in) {
            this.self = ((String) in.readValue((String.class.getClassLoader())));
            this.html = ((String) in.readValue((String.class.getClassLoader())));
            this.download = ((String) in.readValue((String.class.getClassLoader())));
            this.downloadLocation = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Links() {
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownloadLocation() {
            return downloadLocation;
        }

        public void setDownloadLocation(String downloadLocation) {
            this.downloadLocation = downloadLocation;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(self);
            dest.writeValue(html);
            dest.writeValue(download);
            dest.writeValue(downloadLocation);
        }

        public int describeContents() {
            return 0;
        }
    }

    public static class Urls implements Parcelable {

        @SerializedName("raw")
        @Expose
        private String raw;
        @SerializedName("full")
        @Expose
        private String full;
        @SerializedName("regular")
        @Expose
        private String regular;
        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        public final static Creator<Urls> CREATOR = new Creator<Urls>() {
            @SuppressWarnings({
                    "unchecked"
            })
            public Urls createFromParcel(Parcel in) {
                return new Urls(in);
            }

            public Urls[] newArray(int size) {
                return (new Urls[size]);
            }

        };

        protected Urls(Parcel in) {
            this.raw = ((String) in.readValue((String.class.getClassLoader())));
            this.full = ((String) in.readValue((String.class.getClassLoader())));
            this.regular = ((String) in.readValue((String.class.getClassLoader())));
            this.small = ((String) in.readValue((String.class.getClassLoader())));
            this.thumb = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Urls() {
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(raw);
            dest.writeValue(full);
            dest.writeValue(regular);
            dest.writeValue(small);
            dest.writeValue(thumb);
        }

        public int describeContents() {
            return 0;
        }

    }
}
