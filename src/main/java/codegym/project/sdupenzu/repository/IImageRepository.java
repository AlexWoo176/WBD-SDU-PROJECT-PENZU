package codegym.project.sdupenzu.repository;

import codegym.project.sdupenzu.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Iterable<Image> findImagesByAlbumId(Long id);
}
