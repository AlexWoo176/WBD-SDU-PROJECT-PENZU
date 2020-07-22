package codegym.project.sdupenzu.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String date;
    private Boolean isEdit;
    private Long idDiary;
    private Long idAlbum;

    @ManyToOne
    private Diary diary;

    @ManyToOne
    private User user;

    @ManyToOne
    private Album album;

    public Comment() {
    }

    public Comment(String content, String date, Boolean isEdit, Long idDiary, Diary diary, User user) {
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
        this.idDiary = idDiary;
        this.diary = diary;
        this.user = user;
    }

    public Comment(String content, String date, Boolean isEdit, Diary diary, User user) {
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
        this.diary = diary;
        this.user = user;
    }

    public Comment(Long id, String content, String date, Diary diary, User user) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.diary = diary;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public Long getIdDiary() {
        return idDiary;
    }

    public void setIdDiary(Long idDiary) {
        this.idDiary = idDiary;
    }

    public Long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
