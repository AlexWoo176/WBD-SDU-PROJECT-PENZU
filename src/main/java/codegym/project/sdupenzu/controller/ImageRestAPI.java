package codegym.project.sdupenzu.controller;

import codegym.project.sdupenzu.model.Image;
import codegym.project.sdupenzu.service.IImageService;
import codegym.project.sdupenzu.service.impl.ImageFirebaseServiceExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sdu")
public class ImageRestAPI {
    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

    @GetMapping("/image/search-image-by-albumId/{id}")
    public ResponseEntity<?> getListImageByAlbumId(@PathVariable Long id) {
        List<Image> images = (List<Image>) imageService.findImagesByAlbumId(id);

        if(images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        Optional<Image> image = imageService.findById(id);

        if (!image.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(image.get().getBlobString() != null) {
            imageFirebaseServiceExtends.deleteFirebaseStorageFile(image.get());
        }
        imageService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
