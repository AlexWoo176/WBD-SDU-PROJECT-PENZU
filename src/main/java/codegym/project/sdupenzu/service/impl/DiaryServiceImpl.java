package codegym.project.sdupenzu.service.impl;

import codegym.project.sdupenzu.model.Diary;
import codegym.project.sdupenzu.repository.IDiaryRepository;
import codegym.project.sdupenzu.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryServiceImpl implements IDiaryService {
    @Autowired
    private IDiaryRepository repository;

    @Override
    public Optional<Diary> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Diary> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Diary> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Diary save(Diary diary) {
        return repository.save(diary);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Diary> findDiariesByUserId(Long user_id) {
        return repository.findDiariesByUserId(user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id) {
        return repository.findDiariesByTitleContainingAndUserId(title, user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTagId(Long tag_id) {
        return repository.findDiariesByTagId(tag_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContaining(String title) {
        return repository.findDiariesByTitleContaining(title);
    }

    @Override
    public Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title) {
        return repository.findDiariesByTagIdAndTitleContaining(tag_id, title);
    }

    @Override
    public Page<Diary> findAllByOrderByDateAsc(Pageable pageable) {
        return repository.findAllByOrderByDateAsc(pageable);
    }

    @Override
    public Page<Diary> findAllByOrderByDateDesc(Pageable pageable) {
        return repository.findAllByOrderByDateDesc(pageable);
    }
}
