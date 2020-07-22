package codegym.project.sdupenzu.controller;

import codegym.project.sdupenzu.model.Album;
import codegym.project.sdupenzu.model.Comment;
import codegym.project.sdupenzu.model.Diary;
import codegym.project.sdupenzu.service.IAlbumService;
import codegym.project.sdupenzu.service.ICommentService;
import codegym.project.sdupenzu.service.IDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sdu")
public class CommentRestAPI {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private IAlbumService albumService;

    @GetMapping("/comment")
    public ResponseEntity<?> getListAllComment() {
        List<Comment> comments = (List<Comment>) commentService.findAll();

        if(comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);

        if(comment.isPresent()) {
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        if(comment.getIdDiary() != null) {
            Optional<Diary> diary = diaryService.findById(comment.getIdDiary());
            comment.setDiary(diary.get());
        }

        if(comment.getIdAlbum() != null) {
            Optional<Album> album = albumService.findById(comment.getIdAlbum());
            comment.setAlbum(album.get());
        }


        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);
        comment.setEdit(false);
        comment.setDate(date);

        commentService.save(comment);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<?> editComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);

        if (!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = now.format(format);

        comment1.get().setEdit(true);
        comment1.get().setDate(date);
        comment1.get().setContent(comment.getContent());

        commentService.save(comment1.get());

        return new ResponseEntity<>(comment1,HttpStatus.OK);

    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comment/diary/{id}")
    public ResponseEntity<?> getAllCommentByDiaryId(@PathVariable Long id) {
        List<Comment> comments = (List<Comment>) commentService.findCommentsByDiaryId(id);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

    @GetMapping("/comment/album/{id}")
    public ResponseEntity<?> getAllCommentByAlbumId(@PathVariable Long id) {
        List<Comment> comments = (List<Comment>) commentService.findCommentsByAlbumId(id);

        if(comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
