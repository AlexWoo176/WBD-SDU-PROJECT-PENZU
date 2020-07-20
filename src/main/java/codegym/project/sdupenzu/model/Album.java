package codegym.project.sdupenzu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String title;

    @Lob
    private String description;

    @Lob
    private String avatar;

    @Lob
    private String blobString;

    private LocalDateTime date;

    @ManyToOne
    private Tag tag;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(targetEntity = Image.class , mappedBy = "album" , cascade = CascadeType.ALL)
    private List<Image> images;

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class , mappedBy = "album" , cascade = CascadeType.ALL)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Tag getTag() {
        return tag;
    }

    public User getUser() {
        return user;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = date.format(format);
        return now;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBlobString() {
        return blobString;
    }

    public void setBlobString(String blobString) {
        this.blobString = blobString;
    }
}
