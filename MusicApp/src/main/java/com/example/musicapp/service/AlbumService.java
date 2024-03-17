package com.example.musicapp.service;

import com.example.musicapp.entity.Album;
import com.example.musicapp.repository.AlbumRepository;
import com.example.musicapp.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    public List<Album> viewAllAlbums(){
        return (List<Album>) this.albumRepository.findAll();
    }

    public Optional<Album> viewAlbumById(int id){
        return this.albumRepository.findById(id);
    }

    public Album insertAlbum(Album album){
        return this.albumRepository.save(album);
    }

    public void deleteAlbumById(int id){
        try{
            this.albumRepository.deleteById(id);
            System.out.println("Album with id " + id + " was deleted.");
        }catch (Exception e){
            System.out.println("Failed to delete album with id " + id);
        }
    }

}
