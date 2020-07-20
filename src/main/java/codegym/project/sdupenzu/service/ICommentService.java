package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Comment;

import java.util.Optional;

public interface ICommentService {
    Optional<Comment> findById(Long id);

    Iterable<Comment> findAll();

    Comment save(Comment comment);

    void delete(Long id);

    Iterable<Comment> findCommentsByDiaryId(Long diary_id);

    Iterable<Comment> findCommentsByAlbumId(Long album_id);
}
