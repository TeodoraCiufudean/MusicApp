package com.example.musicapp.controller;

import com.example.musicapp.entity.Song;
import com.example.musicapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/getAllSongs")
    @ResponseBody
    public List<Song> viewAllSongs(){
        return this.songService.viewAllSongs();
    }

    @GetMapping("/getSongById")
    @ResponseBody
    public Optional<Song> viewSongById(@RequestParam int id){
        return this.songService.viewSongById(id);
    }

    @PostMapping("/addSong")
    @ResponseBody
    public Song insertSong(@RequestBody Song song){
        return this.songService.insertSong(song);
    }

    @PutMapping("/updateSong")
    @ResponseBody
    public Song updateSong(@RequestBody Song song){
        return this.songService.insertSong(song);
    }

    @DeleteMapping("deleteSongById")
    @ResponseBody
    public void deleteSongById(@RequestParam int id){
        this.songService.deleteSongById(id);
    }
}
