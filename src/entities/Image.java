package entities;

import java.util.Scanner;

public class Image extends Media implements ChangeLuminosity {
    private int luminosity = 5;
    private String luminosityString = "";

    public Image(String title) {
        super(title);
        mediaType = MediaType.IMAGE;
        setLuminosityString();
    }

    @Override
    public PlayerActions managePlayer(Media media, Scanner sc) {
        int option;
        Image image = (Image) media;
        do {
            System.out.println();
            System.out.println("What do you want to do?");
            System.out.println("1. Display image");
            System.out.println("2. Increment luminosity");
            System.out.println("3. Decrement luminosity");
            System.out.println("4. Select another media");
            System.out.println("5. Exit application");
            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                option = 0;
            }

            switch (option) {
                case 1:
                    image.show();
                    break;
                case 2:
                    image.incrementLuminosity();
                    image.displayLuminosity();
                    break;
                case 3:
                    image.decrementLuminosity();
                    image.displayLuminosity();
                    break;
                case 4:
                    return PlayerActions.CONTINUE;
                case 5:
                    return PlayerActions.EXIT;
                default:
                    System.err.println("Invalid option. Try again.");
                    break;
            }
        } while (true);
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
