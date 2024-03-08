package entities;

public class Image extends Media implements ChangeLuminosity {
    private int luminosity = 5;
    private String luminosityString = "";

    public Image(String title) {
        super(title);
        mediaType = MediaType.IMAGE;
        setLuminosityString();
    }

    public void show() {
        System.out.println("Image " + this.title + " with luminosity " + this.luminosityString);
    }

    @Override
    public void incrementLuminosity() {
        if (this.luminosity < maxLuminosity) {
            this.luminosity++;
            setLuminosityString();
        } else {
            System.err.println("Luminosity already at maximum");
        }
    }

    @Override
    public void decrementLuminosity() {
        if (this.luminosity > minLuminosity) {
            this.luminosity--;
            setLuminosityString();
        } else {
            System.err.println("Luminosity already at minimum");
        }
    }

    @Override
    public void setLuminosityString() {
        StringBuilder luminosityString = new StringBuilder(); // suggested by IntelliJ        {

        for (int i = 0; i < this.luminosity; i++) {
            luminosityString.append("*");
        }
        for (int i = 0; i < maxLuminosity - this.luminosity; i++) {
            luminosityString.append("・");
        }
        this.luminosityString = luminosityString.toString() + " (" + this.luminosity + ")";
    }

    @Override
    public void displayLuminosity() {
        System.out.println("Luminosity: " + this.luminosityString);
    }

}
