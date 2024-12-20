package mk.ecode.artists.service;

import mk.ecode.artists.model.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAll();

    Album findById(Long albumId);
}
