package com.example.musicapp.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "songs")
public class Song {

    @Id
    @GeneratedValue
    @Column (name = "song_id")
    private int songId;

    @Column (name = "song_title")
    private String songTitle;

    @Column (name = "lyrics")
    private String lyrics;

    @Column (name = "artist")
    private String artist;

    @Column (name = "year")
    private int year;

    @ManyToOne()
    @JoinColumn (name = "album")
    private Album album;

    public Song(){}

    public Song(int songId, String songTitle, String lyrics, String artist,int year,Album album){
        this.songId = songId;
        this.songTitle = songTitle;
        this.lyrics = lyrics;
        this.artist= artist;
        this.year = year;
        this.album = album;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
