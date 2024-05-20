package com.example.musicapp.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table (name = "albums")
public class Album {

    @Id
    @GeneratedValue
    @Column (name = "album_id")
    private int albumID;

    @Column (name = "album_title")
    private String albumTitle;

    @Column (name = "artist")
    private String artist;

    @Column (name = "year")
    private int year;

    @Column (name = "genre")
    private String genre;

    @Column (name = "record")
    private String record;

    public Album() {}

    public Album(int albumID, String albumTitle, String artist, int year, String genre, String record) {
        this.albumID = albumID;
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.record = record;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
