package me.cxis.es.lucene.index;

public class Article {

    private Long id;

    private String title;

    private String content;

    private int replay;

    public Article() {
    }

    public Article(Long id, String title, String content, int replay) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.replay = replay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplay() {
        return replay;
    }

    public void setReplay(int replay) {
        this.replay = replay;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", replay=" + replay +
            '}';
    }
}
