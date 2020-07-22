package codegym.project.sdupenzu.service.impl;

import codegym.project.sdupenzu.model.Album;
import codegym.project.sdupenzu.repository.IAlbumRepository;
import codegym.project.sdupenzu.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    private IAlbumRepository repository;

    @Override
    public Optional<Album> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album save(Album album) {
        return repository.save(album);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Album> findAllByUserId(Long user_id) {
        return repository.findAllByUserId(user_id);
    }

    @Override
    public Iterable<Album> findAlbumsByTitleContaining(String title) {
        return repository.findAlbumsByTitleContaining(title);
    }

    @Override
    public Page<Album> findAllByOrderByDateAsc(Pageable pageable) {
        return repository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Album> findAllByOrderByDateDesc(Pageable pageable) {
        return repository.findAllByOrderByDateDesc(pageable);
    }

    @Override
    public Iterable<Album> findAlbumsByTagId(Long tag_id) {
        return repository.findAlbumsByTagId(tag_id);
    }

    @Override
    public Iterable<Album> findAlbumsByTagIdAndTitleContaining(Long tag_id, String title) {
        return repository.findAlbumsByTagIdAndTitleContaining(tag_id, title);
    }
}
