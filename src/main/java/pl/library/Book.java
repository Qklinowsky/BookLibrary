package pl.library;

import javax.xml.crypto.Data;
import java.util.UUID;

public class Book {
    private UUID ID;
    private String author;
    private String title;
    private int releseYear;
    private String descritpion;
    private boolean isAvaible;

    public Book(UUID uuid, String author, String title, int releseYear, String descritpion) {
        this.ID = uuid;
        this.author = author;
        this.title = title;
        this.releseYear = releseYear;
        this.descritpion = descritpion;
        this.isAvaible = true;
    }

    public Book(String author, String title, int releseYear, String descritpion) {
        this(UUID.randomUUID(), author, title, releseYear, descritpion);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleseYear(Data releseDate) {
        this.releseYear = releseYear;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
    }


    public UUID getID() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getReleseYear() {
        return releseYear;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    @Override
    public String toString() {
        return "pl.library.Book{" +
                "ID='" + ID + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", releseYear=" + releseYear +
                ", descritpion='" + descritpion + '\'' +
                '}';
    }


}
