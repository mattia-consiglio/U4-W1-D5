package entities;

public class Video extends Media implements ChangeVolume, Reproducible, ChangeLuminosity {
    private int duration = 0;
    private int volume = 5;
    private String volumeString = "";
    private int luminosity = 5;
    private String luminosityString = "";

    public Video(String title, int duration) {
        super(title);
        mediaType = MediaType.VIDEO;
        this.duration = duration;
        setVolumeString();
        setLuminosityString();
    }

    @Override
    public void setVolumeString() {
        StringBuilder volumeString = new StringBuilder(); // suggested by IntelliJ
        for (int i = 0; i < this.volume; i++) {
            volumeString.append("!");
        }
        for (int i = 0; i < maxVolume - this.volume; i++) {
            volumeString.append(".");
        }
        this.volumeString = volumeString.toString() + " (" + this.volume + ")";
    }


    @Override
    public void incrementVolume() {
        if (this.volume < maxVolume) {
            this.volume++;
            setVolumeString();
        } else {
            System.err.println("Volume already at maximum");
        }
    }

    @Override
    public void decrementVolume() {
        if (this.volume > minVolume) {
            this.volume--;
            setVolumeString();
        } else {
            System.err.println("Volume already at minimum");
        }
    }

    @Override
    public void play() {
        for (int i = 0; i < this.duration; i++) {
            System.out.println("[" + (i + 1) + "] Playing " + this.title + " with volume " + this.volumeString + " and luminosity " + this.luminosityString);
        }
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
    public void displayVolume() {
        System.out.println("Volume: " + this.volumeString);
    }

    @Override
    public void displayLuminosity() {
        System.out.println("Luminosity: " + this.luminosityString);
    }
}
