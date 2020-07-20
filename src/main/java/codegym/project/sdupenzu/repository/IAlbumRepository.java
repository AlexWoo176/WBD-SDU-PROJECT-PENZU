package codegym.project.sdupenzu.repository;

import codegym.project.sdupenzu.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlbumRepository extends PagingAndSortingRepository<Album, Long> {
    Iterable<Album> findAllByUserId(Long user_id);

    Iterable<Album> findAlbumsByTitleContaining(String title);

    Page<Album> findAllByOrderByDateAsc(Pageable pageable);
    Page<Album> findAllByOrderByDateDesc(Pageable pageable);

    Iterable<Album> findAlbumsByTagId(Long tag_id);
    Iterable<Album> findAlbumsByTagIdAndTitleContaining(Long tag_id, String title);
}
