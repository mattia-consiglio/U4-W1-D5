package entities;

public class Audio extends Media implements ChangeVolume, Reproducible {

    private int duration = 0;
    private int volume = 5;
    private String volumeString = "";

    public Audio(String title, int duration) {
        super(title);
        mediaType = MediaType.AUDIO;
        this.duration = duration;
    }

    public void setVolumeString() {
        StringBuilder volumeString = new StringBuilder(); // suggested by IntelliJ
        for (int i = 0; i < this.volume; i++) {
            volumeString.append("!");
        }
        for (int i = 0; i < maxVolume - this.volume; i++) {
            volumeString.append(".");
        }
        this.volumeString = volumeString.toString();
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
            System.out.println("Playing " + this.title + " with volume " + this.volumeString);
        }
    }
}
