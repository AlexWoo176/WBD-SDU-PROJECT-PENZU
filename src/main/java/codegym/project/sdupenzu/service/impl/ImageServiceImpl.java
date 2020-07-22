package codegym.project.sdupenzu.service.impl;

import codegym.project.sdupenzu.model.Image;
import codegym.project.sdupenzu.repository.IImageRepository;
import codegym.project.sdupenzu.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private IImageRepository repository;

    @Override
    public Optional<Image> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Image> findImagesByAlbumId(Long id) {
        return repository.findImagesByAlbumId(id);
    }
}
