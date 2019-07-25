package domain_model;

public class DomainBook {
    private String id;

    private String author;

    private String title;

    public DomainBook(String id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "id=" + id +
                " author='" + author + '\'' +
                " title='" + title + '\'';
    }
}
