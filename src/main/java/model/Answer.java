package model;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Answer {

    private String title;
    private String author;
    private String url;
    private Date date;
    private boolean answered;

    public Answer() {
    }

    public Answer(String title, String author, String url, Date date, boolean answered) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.date = date;
        this.answered = answered;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", answered=" + answered +
                '}';
    }
}