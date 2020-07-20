package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IDiaryService {
    Optional<Diary> findById(Long id);

    Iterable<Diary> findAll();

    Page<Diary> findAll(Pageable pageable);

    Diary save(Diary diary);

    void delete(Long id);

    Iterable<Diary> findDiariesByUserId(Long user_id);

    Iterable<Diary> findDiariesByTitleContainingAndUserId(String title,Long user_id);

    Iterable<Diary> findDiariesByTagId(Long tag_id);

    Iterable<Diary> findDiariesByTitleContaining(String title);

    Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title);

    Page<Diary> findAllByOrderByDateAsc(Pageable pageable);

    Page<Diary> findAllByOrderByDateDesc(Pageable pageable);
}
