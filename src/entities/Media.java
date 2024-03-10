package entities;

import java.util.Scanner;

public abstract class Media {
    protected String title;
    protected MediaType mediaType;

    public Media(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Media{" +
                "title='" + title + '\'' +
                ", mediaType=" + mediaType +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    abstract public PlayerActions managePlayer(Media media, Scanner sc);
}
