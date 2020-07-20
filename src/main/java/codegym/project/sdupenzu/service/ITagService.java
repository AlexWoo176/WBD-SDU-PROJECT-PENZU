package codegym.project.sdupenzu.service;

import codegym.project.sdupenzu.model.Tag;

import java.util.Optional;

public interface ITagService {
    Optional<Tag> findById(Long id);

    Iterable<Tag> findAll();

    Tag save(Tag tag);

    void delete(Long id);

    Iterable<Tag> findTagsByNameContaining(String tag_name);
}
