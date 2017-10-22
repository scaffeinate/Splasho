
package io.phoenix.app.splasho.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;
    @SerializedName("portfolio_url")
    @Expose
    private Object portfolioUrl;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("total_likes")
    @Expose
    private long totalLikes;
    @SerializedName("total_photos")
    @Expose
    private long totalPhotos;
    @SerializedName("total_collections")
    @Expose
    private long totalCollections;
    @SerializedName("profile_image")
    @Expose
    private ProfileImage profileImage;

    public final static Creator<User> CREATOR = new Creator<User>() {

        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    };

    protected User(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.twitterUsername = ((String) in.readValue((String.class.getClassLoader())));
        this.portfolioUrl = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bio = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.totalLikes = ((long) in.readValue((long.class.getClassLoader())));
        this.totalPhotos = ((long) in.readValue((long.class.getClassLoader())));
        this.totalCollections = ((long) in.readValue((long.class.getClassLoader())));
        this.profileImage = ((ProfileImage) in.readValue((ProfileImage.class.getClassLoader())));
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public Object getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(Object portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
    }

    public long getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(long totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public long getTotalCollections() {
        return totalCollections;
    }

    public void setTotalCollections(long totalCollections) {
        this.totalCollections = totalCollections;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(updatedAt);
        dest.writeValue(username);
        dest.writeValue(name);
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeValue(twitterUsername);
        dest.writeValue(portfolioUrl);
        dest.writeValue(bio);
        dest.writeValue(location);
        dest.writeValue(totalLikes);
        dest.writeValue(totalPhotos);
        dest.writeValue(totalCollections);
        dest.writeValue(profileImage);
    }

    public int describeContents() {
        return 0;
    }

    public static class ProfileImage implements Parcelable {

        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("medium")
        @Expose
        private String medium;
        @SerializedName("large")
        @Expose
        private String large;
        public final static Creator<ProfileImage> CREATOR = new Creator<ProfileImage>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public ProfileImage createFromParcel(Parcel in) {
                return new ProfileImage(in);
            }

            public ProfileImage[] newArray(int size) {
                return (new ProfileImage[size]);
            }

        };

        protected ProfileImage(Parcel in) {
            this.small = ((String) in.readValue((String.class.getClassLoader())));
            this.medium = ((String) in.readValue((String.class.getClassLoader())));
            this.large = ((String) in.readValue((String.class.getClassLoader())));
        }

        public ProfileImage() {
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(small);
            dest.writeValue(medium);
            dest.writeValue(large);
        }

        public int describeContents() {
            return 0;
        }

    }
}
