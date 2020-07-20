package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Image;

import java.util.Optional;

public interface IImageService {
    Optional<Image> findById(Long id);

    Iterable<Image> findAll();

    Image save(Image image);

    void delete(Long id);

    Iterable<Image> findImagesByAlbumId(Long id);
}
