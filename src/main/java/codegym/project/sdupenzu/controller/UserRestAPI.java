package codegym.project.sdupenzu.controller;

import codegym.project.sdupenzu.message.request.SearchUserByName;
import codegym.project.sdupenzu.model.Diary;
import codegym.project.sdupenzu.model.User;
import codegym.project.sdupenzu.service.IDiaryService;
import codegym.project.sdupenzu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserRestAPI {
    @Autowired
    private IUserService userService;

    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/user")
    public ResponseEntity<?> getListAllUser(){
        List<User> users = (List<User>) userService.findAll();

        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id ){
        Optional<User> user = userService.findById(id);

        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{id}/diary")
    public ResponseEntity<?> getAllDiaryByUser(@PathVariable Long id) {
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByUserId(id);

        if(diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @PostMapping("/user/search-by-name")
    public ResponseEntity<?> getListUserByName(@RequestBody SearchUserByName userForm) {
        if(userForm.getName().equals("") || userForm.getName() == null) {
            List<User> users = (List<User>) userService.findAll();

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users,HttpStatus.OK);
            }
        }

        List<User> users = (List<User>) userService.findUsersByNameContaining(userForm.getName());
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}
