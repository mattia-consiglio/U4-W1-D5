import entities.Audio;
import entities.Image;
import entities.Media;
import entities.Video;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Media[] player = new Media[5];
        System.out.println("Add 5 medias to the player");


        for (int i = 0; i < 5; i++) {
            System.out.println("Enter the media type: ");
            System.out.println("1. Audio");
            System.out.println("2. Video");
            System.out.println("3. Image");
            int mediaType = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the media title: ");
            String title = sc.nextLine();
            int duration = 0;
            switch (mediaType) {
                case 1:
                    System.out.println("Enter the video duration: ");
                    duration = Integer.parseInt(sc.next());
                    player[i] = new Audio(title, duration);
                    break;
                case 2:
                    System.out.println("Enter the video duration: ");
                    duration = Integer.parseInt(sc.next());
                    player[i] = new Video(title, duration);
                    break;
                case 3:
                    player[i] = new Image(title);
                    break;
                default:
                    System.out.println("Invalid media type");
                    break;
            }
            System.out.println("Media added");

        }

        sc.close();
        System.out.println(Arrays.toString(player));
    }

}