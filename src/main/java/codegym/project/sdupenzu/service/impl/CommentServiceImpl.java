package codegym.project.sdupenzu.service.impl;

import codegym.project.sdupenzu.model.Comment;
import codegym.project.sdupenzu.repository.ICommentRepository;
import codegym.project.sdupenzu.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository repository;

    @Override
    public Optional<Comment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findCommentsByDiaryId(Long diary_id) {
        return repository.findCommentsByDiaryId(diary_id);
    }

    @Override
    public Iterable<Comment> findCommentsByAlbumId(Long album_id) {
        return repository.findCommentsByAlbumId(album_id);
    }
}
