package cz.cvut.fel.ts1.utils;

public class ArticleInfo {
    private String title;
    private String doi;
    private String publicationDate;

    public ArticleInfo(String title, String doi, String publicationDate) {
        this.title = title;
        this.doi = doi;
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "\"" + title + "\", " + doi + ", " + publicationDate;
    }
}
