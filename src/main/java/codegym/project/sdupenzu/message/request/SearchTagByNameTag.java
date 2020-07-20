package codegym.project.sdupenzu.message.request;

public class SearchTagByNameTag {
    private String name;

    public SearchTagByNameTag() {
    }

    public SearchTagByNameTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
