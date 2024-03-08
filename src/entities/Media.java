package entities;

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
}
