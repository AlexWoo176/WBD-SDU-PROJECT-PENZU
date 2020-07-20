package codegym.project.sdupenzu.message.request;

public class SearchAlbumByTagIdAndTitle {
    private Long tagId;
    private String title;

    public SearchAlbumByTagIdAndTitle() {
    }

    public SearchAlbumByTagIdAndTitle(Long tagId, String title) {
        this.tagId = tagId;
        this.title = title;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
