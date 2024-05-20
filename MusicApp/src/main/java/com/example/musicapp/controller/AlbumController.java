package com.example.musicapp.controller;

import com.example.musicapp.entity.Album;
import com.example.musicapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/getAllAlbums")
    @ResponseBody
    public List<Album> viewAllAlbums(){
        return this.albumService.viewAllAlbums();
    }

    @GetMapping("/getAlbumById")
    @ResponseBody
    public Optional<Album> viewAlbumById(@RequestParam int id){
        return this.albumService.viewAlbumById(id);
    }

    @PostMapping("/insertAlbum")
    @ResponseBody
    public Album insertAlbum(@RequestBody Album album){
        return this.albumService.insertAlbum(album);
    }

    @PutMapping("/updateAlbum")
    @ResponseBody
    public Album updateAlbum(@RequestBody Album album){

        return this.albumService.insertAlbum(album);
    }

    @DeleteMapping("/deleteAlbumById")
    @ResponseBody
    public void deleteById(@RequestParam int id){
        this.albumService.deleteAlbumById(id);
    }
}
