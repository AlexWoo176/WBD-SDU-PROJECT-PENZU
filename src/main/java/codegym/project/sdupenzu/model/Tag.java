package codegym.project.sdupenzu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Diary.class, mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Diary> diaries;

    @JsonIgnore
    @OneToMany(targetEntity = Album.class, mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Album> albums;

    public Tag() {
    }

    public Tag(String name, List<Diary> diaries) {
        this.name = name;
        this.diaries = diaries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(List<Diary> diaries) {
        this.diaries = diaries;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
