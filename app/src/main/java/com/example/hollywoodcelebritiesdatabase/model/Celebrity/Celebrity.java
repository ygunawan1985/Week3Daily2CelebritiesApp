package com.example.hollywoodcelebritiesdatabase.model.Celebrity;

import android.os.Parcel;
import android.os.Parcelable;

public class Celebrity implements Parcelable {

    private String firstName;
    private String lastName;
    private String mostPopularMovie;
    private String recentScandal;
    private boolean isAlive;
    private String picture;
    private boolean isFavorite;

    public Celebrity() {
    }

    public Celebrity(String firstName, String lastName, String mostPopularMovie, String recentScandal, boolean isAlive, String picture, boolean isFavorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mostPopularMovie = mostPopularMovie;
        this.recentScandal = recentScandal;
        this.isAlive = isAlive;
        this.picture = picture;
        this.isFavorite = isFavorite;
    }

    protected Celebrity(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        mostPopularMovie = in.readString();
        recentScandal = in.readString();
        isAlive = in.readByte() != 0;
        picture = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

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

    public String getMostPopularMovie() {
        return mostPopularMovie;
    }

    public void setMostPopularMovie(String mostPopularMovie) {
        this.mostPopularMovie = mostPopularMovie;
    }

    public String getRecentScandal() {
        return recentScandal;
    }

    public void setRecentScandal(String recentScandal) {
        this.recentScandal = recentScandal;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(mostPopularMovie);
        parcel.writeString(recentScandal);
        parcel.writeByte((byte) (isAlive ? 1 : 0));
        parcel.writeString(picture);
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mostPopularMovie='" + mostPopularMovie + '\'' +
                ", recentScandal='" + recentScandal + '\'' +
                ", isAlive=" + isAlive +
                ", picture='" + picture + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
