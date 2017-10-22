package io.phoenix.app.splasho.container;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sudharti on 10/22/17.
 */

public class Tab implements Parcelable {
    private String title, key;

    public Tab(String title, String key) {
        this.title = title;
        this.key = key;
    }

    protected Tab(Parcel in) {
        title = in.readString();
        key = in.readString();
    }

    public static final Creator<Tab> CREATOR = new Creator<Tab>() {
        @Override
        public Tab createFromParcel(Parcel in) {
            return new Tab(in);
        }

        @Override
        public Tab[] newArray(int size) {
            return new Tab[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(key);
    }
}
