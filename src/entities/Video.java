package entities;

import java.util.Scanner;

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

    @Override
    public PlayerActions managePlayer(Media media, Scanner sc) {
        int option;

        Video video = (Video) media;
        do {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println("1. Play");
            System.out.println("2. Increment volume");
            System.out.println("3. Decrement volume");
            System.out.println("4. Increment luminosity");
            System.out.println("5. Decrement luminosity");
            System.out.println("6. Select another media");
            System.out.println("7. Exit application");


            //prevent the user from entering a non-integer value
            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                option = 0;
            }

            switch (option) {
                case 1:
                    video.play();
                    break;
                case 2:
                    video.incrementVolume();
                    video.displayVolume();
                    break;
                case 3:
                    video.decrementVolume();
                    video.displayVolume();
                    break;
                case 4:
                    video.incrementLuminosity();
                    video.displayLuminosity();
                    break;
                case 5:
                    video.decrementLuminosity();
                    video.displayLuminosity();
                    break;
                case 6:
                    return PlayerActions.CONTINUE;
                case 7:
                    return PlayerActions.EXIT;
                default:
                    System.err.println("Invalid option. Try again.");
                    break;
            }
        } while (true);  //while true because can be exited by returning PlayerActions.EXIT or PlayerActions.CONTINUE
    }
}
