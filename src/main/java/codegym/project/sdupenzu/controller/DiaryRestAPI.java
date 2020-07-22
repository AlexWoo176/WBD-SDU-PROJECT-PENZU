package codegym.project.sdupenzu.controller;

import codegym.project.sdupenzu.message.request.SearchDiaryByTagAndTitle;
import codegym.project.sdupenzu.message.request.SearchDiaryByTitleAndUserId;
import codegym.project.sdupenzu.message.request.SearchDiaryByTitleForm;
import codegym.project.sdupenzu.model.Diary;
import codegym.project.sdupenzu.service.IDiaryService;
import codegym.project.sdupenzu.service.impl.DiaryFirebaseServiceExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sdu")
public class DiaryRestAPI {

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private DiaryFirebaseServiceExtends diaryFirebaseServiceExtends;

    @GetMapping("/diary/pagination/ASC")
    public ResponseEntity<?> getListDiaryAndPaginationASC(@PageableDefault(value = 2 ) Pageable pageable) {
        Page<Diary> diaries =  diaryService.findAllByOrderByDateAsc(pageable);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @GetMapping("/diary/pagination/DESC")
    public ResponseEntity<?> getListDiaryAndPaginationDESC(@PageableDefault(value = 2 ) Pageable pageable) {
        Page<Diary> diaries =  diaryService.findAllByOrderByDateDesc(pageable);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @GetMapping("/diary")
    public ResponseEntity<?> getListDiary() {
        List<Diary> diaries = (List<Diary>) diaryService.findAll();
        if(diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @GetMapping("/diary/{id}")
    public ResponseEntity<?> getDiary(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.findById(id);

        if (!diary.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diary, HttpStatus.OK);
    }

    @PostMapping("/diary")
    public ResponseEntity<?> createDiary(@Valid @RequestBody Diary diary) {

        LocalDateTime now = LocalDateTime.now();
        diary.setDate(now);
        diary.setUpdate(false);
        diaryService.save(diary);

        return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

    @PutMapping("/diary/{id}")
    public ResponseEntity<?> updateDiary(@Valid @RequestBody Diary diary, @PathVariable Long id) {
        Optional<Diary> diary1 = diaryService.findById(id);

        if (!diary1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        LocalDateTime now = LocalDateTime.now();

        diary1.get().setDate(now);
        diary1.get().setTitle(diary.getTitle());
        diary1.get().setDescription(diary.getDescription());
        diary1.get().setContent(diary.getContent());
        diary1.get().setUpdate(true);
        diary1.get().setTag(diary.getTag());
        diary1.get().setUser(diary.getUser());

        diaryService.save(diary1.get());

        return new ResponseEntity<>(diary1, HttpStatus.OK);
    }

    @DeleteMapping("/diary/{id}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.findById(id);

        if (!diary.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(diary.get().getBlobString() != null) {
            diaryFirebaseServiceExtends.deleteFirebaseStorageFile(diary.get());
        }

        diaryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/diary/searchBy-Title-And-UserId")
    public ResponseEntity<?> searchDiaryByTitleAndUserId(@RequestBody SearchDiaryByTitleAndUserId searchDiaryByTitleAndUserId) {
        List<Diary> diaries;
        if(searchDiaryByTitleAndUserId.getTitle().equals("")) {
            diaries = (List<Diary>) diaryService.findAll();
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }
        diaries = (List<Diary>) diaryService.findDiariesByTitleContainingAndUserId(searchDiaryByTitleAndUserId.getTitle(), searchDiaryByTitleAndUserId.getId());
        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @GetMapping("/diary/searchBy-TagId/{id}")
    public ResponseEntity<?> searchByTagId(@PathVariable Long id) {
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(id);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @PostMapping("/diary/search-by-title")
    public ResponseEntity<?> searchDiaryByTitle(@RequestBody SearchDiaryByTitleForm titleForm) {
        if (titleForm.getTitle().equals("") || titleForm.getTitle() == null ) {
            List<Diary> diaries = (List<Diary>) diaryService.findAll();

            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(diaries,HttpStatus.OK);
            }
        }

        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTitleContaining(titleForm.getTitle());
        if(diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }
    }

    @PostMapping("/diary/search-by-tag-and-title")
    public ResponseEntity<?> searchDiaryByTagAndTitle(@RequestBody SearchDiaryByTagAndTitle searchForm) {
        if (searchForm.getTitle() == null && searchForm.getTagId() == null) {
            List<Diary> diaries = (List<Diary>) diaryService.findAll();
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }

        if (searchForm.getTitle() == null && searchForm.getTagId() != null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(searchForm.getTagId());
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }

        if (searchForm.getTitle() != null && searchForm.getTagId() == null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTitleContaining(searchForm.getTitle());
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        }

        if (searchForm.getTagId() != null && searchForm.getTitle() != null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagIdAndTitleContaining(searchForm.getTagId(),searchForm.getTitle());
            if(diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
