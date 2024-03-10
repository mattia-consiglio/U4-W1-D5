package entities;

import java.util.Scanner;

public class Audio extends Media implements ChangeVolume, Reproducible {

    private int duration = 0;
    private int volume = 5;
    private String volumeString = "";

    public Audio(String title, int duration) {
        super(title);
        mediaType = MediaType.AUDIO;
        this.duration = duration;
        setVolumeString();
    }

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
    public void displayVolume() {
        System.out.println("Volume: " + this.volumeString);
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
            System.out.println("[" + (i + 1) + "] Playing " + this.title + " with volume " + this.volumeString);
        }
    }

    @Override
    public PlayerActions managePlayer(Media media, Scanner sc) {
        Audio audio = (Audio) media;
        int option;
        do {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println("1. Play");
            System.out.println("2. Increment volume");
            System.out.println("3. Decrement volume");
            System.out.println("4. Select another media");
            System.out.println("5. Exit application");
            option = Integer.parseInt(sc.nextLine().trim());
            switch (option) {
                case 1:
                    audio.play();
                    break;
                case 2:
                    audio.incrementVolume();
                    audio.displayVolume();
                    break;
                case 3:
                    audio.decrementVolume();
                    audio.displayVolume();
                    break;
                case 4:
                    return PlayerActions.CONTINUE;
                case 5:
                    return PlayerActions.EXIT;
                default:
                    System.err.println("Invalid option. Try again.");
                    break;
            }
        } while (true); //while true because can be exited by returning PlayerActions.EXIT or PlayerActions.CONTINUE
    }
}
