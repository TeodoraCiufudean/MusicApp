package com.example.musicapp.service;

import com.example.musicapp.entity.Song;
import com.example.musicapp.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<Song> viewAllSongs(){
        return (List<Song>) this.songRepository.findAll();
    }

    public Optional<Song> viewSongById(int id){
        return this.songRepository.findById(id);
    }

    public Song insertSong(Song song){
        return this.songRepository.save(song);
    }

    public void deleteSongById(int id){
        try{
            this.songRepository.deleteById(id);
            System.out.println("Song with id " + id + " was deleted.");
        }catch (Exception e){
            System.out.println("Failed to delete song with id " + id);
        }
    }
}
