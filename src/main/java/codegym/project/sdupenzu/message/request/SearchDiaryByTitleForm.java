package codegym.project.sdupenzu.message.request;

public class SearchDiaryByTitleForm {
    private String title;

    public SearchDiaryByTitleForm() {
    }

    public SearchDiaryByTitleForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
