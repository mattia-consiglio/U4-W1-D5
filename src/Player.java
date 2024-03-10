import entities.*;


import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int mediaNumber = 3;//REMEMBER to change back to 5
        Media[] player = new Media[mediaNumber];
        if (mediaNumber > 1) {
            System.out.println("Add " + mediaNumber + " medias to the player");
        } else {
            System.out.println("Add " + mediaNumber + " media to the player");
        }


        int mediaType;
        String mediaTypeString;
        for (int i = 0; i < mediaNumber; i++) {
            System.out.println("Enter the media type: ");
            System.out.println("1. Audio");
            System.out.println("2. Video");
            System.out.println("3. Image");


            mediaTypeString = sc.nextLine();
            mediaType = Integer.parseInt(mediaTypeString);

            String title;
            int duration = 0;
            String stringDuration;

            switch (mediaType) {
                case 1:
                    System.out.println("Enter the audio title: ");
                    title = sc.nextLine();
                    while (duration <= 0) {
                        System.out.print("Enter the audio duration: ");
                        //Check if the duration is a number
                        stringDuration = sc.next().trim(); //get user input trimming initial and final whitespaces
                        sc.nextLine(); //Consume the rest of the line


                        try {
                            duration = Integer.parseInt(stringDuration); //get user input trimming initial and final whitespaces
                            //prevent adding a media with 0 or a negative duration
                            if (duration <= 0) {
                                System.err.println("Invalid duration. Try again.");
                                System.out.println();

                                continue;
                            }
                            player[i] = new Audio(title, duration);
                            System.out.println("Audio added");
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid duration. Try again.");
                            System.out.println();

                        }
                    }

                    break;
                case 2:
                    System.out.println("Enter the video title: ");
                    title = sc.nextLine();
                    System.out.print("Enter the video duration: ");

                    stringDuration = sc.next().trim(); //get user input trimming initial and final whitespaces
                    sc.nextLine(); //Consume the rest of the line
                    try {
                        duration = Integer.parseInt(stringDuration);//get user input trimming initial and final whitespaces
                        //prevent adding a media with 0 or a negative duration
                        if (duration <= 0) {
                            System.err.println("Invalid duration. Try again.");
                            i--;
                            continue;
                        }
                        player[i] = new Video(title, duration);
                        System.out.println("Video added");
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid duration. Try again.");
                        i--;
                    }


                    break;
                case 3:
                    System.out.println("Enter the image title: ");
                    title = sc.nextLine();
                    player[i] = new Image(title);
                    System.out.println("Media added");
                    break;
                default:
                    System.err.println("Invalid media type. Try again.");
                    i--;
                    break;
            }
            System.out.println();

        }


        int mediaIndex = -1;
        PlayerActions action = PlayerActions.CONTINUE;
        while (action == PlayerActions.CONTINUE) {
            System.out.println("Media list:");
            for (int i = 0; i < player.length; i++) {
                System.out.println((i + 1) + ". " + player[i].getMediaType() + " " + player[i].getTitle());
            }
            System.out.println();
            mediaIndex = -1;
            while (mediaIndex < 0 || mediaIndex > player.length - 1) {
                try {
                    System.out.print("Choose a media to interact with: ");
                    mediaIndex = Integer.parseInt(sc.next().trim()) - 1;
                    if (mediaIndex < 0 || mediaIndex > player.length - 1) {
                        System.err.println("Invalid media index. Try again.");
                        System.out.println();
                        mediaIndex = -1;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid media index. Try again.");
                    mediaIndex = -1;
                }
            }
            sc.nextLine(); //Consume the rest of the line

            System.out.println("You chose " + player[mediaIndex].getMediaType() + " " + player[mediaIndex].getTitle());


            action = player[mediaIndex].managePlayer(player[mediaIndex], sc);
            System.out.println();

        }
        System.out.println("Player closed");
        sc.close();
    }


}