package com.mysong.api.service;

import com.mysong.api.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> findAll();

    Optional<Song> findById(Long id);

    Song save(Song song);

}
