package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAlbumService {
    Optional<Album> findById(Long id);

    Iterable<Album> findAll();

    Album save(Album album);

    void delete(Long id);

    Iterable<Album> findAllByUserId(Long user_id);

    Iterable<Album> findAlbumsByTitleContaining(String title);

    Page<Album> findAllByOrderByDateAsc(Pageable pageable);

    Page<Album> findAllByOrderByDateDesc(Pageable pageable);

    Iterable<Album> findAlbumsByTagId(Long tag_id);

    Iterable<Album> findAlbumsByTagIdAndTitleContaining(Long tag_id , String title);
}
