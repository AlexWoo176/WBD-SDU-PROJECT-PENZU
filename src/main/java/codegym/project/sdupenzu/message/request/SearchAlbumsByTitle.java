package codegym.project.sdupenzu.message.request;

public class SearchAlbumsByTitle {
    private String title;

    public SearchAlbumsByTitle() {
    }

    public SearchAlbumsByTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
