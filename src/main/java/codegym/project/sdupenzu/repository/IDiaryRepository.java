package codegym.project.sdupenzu.repository;

import codegym.project.sdupenzu.model.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiaryRepository extends PagingAndSortingRepository<Diary, Long> {
    Iterable<Diary> findDiariesByUserId(Long user_id);
    Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id);
    Iterable<Diary> findDiariesByTagId(Long tag_id);
    Iterable<Diary> findDiariesByTitleContaining(String title);
    Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title);
    Page<Diary> findAllByOrderByDateAsc(Pageable pageable);
    Page<Diary> findAllByOrderByDateDesc(Pageable pageable);
}
