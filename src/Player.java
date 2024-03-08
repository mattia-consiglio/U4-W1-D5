import entities.*;


import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int mediaNumber = 5;//REMEMBER to change back to 5
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


            if (player[mediaIndex] instanceof Audio) {
                action = manageAudio((Audio) player[mediaIndex], sc);
            }
            if (player[mediaIndex] instanceof Video) {
                action = manageVideo((Video) player[mediaIndex], sc);
            }
            if (player[mediaIndex] instanceof Image) {
                action = manageImage((Image) player[mediaIndex], sc);
            }
            System.out.println();

        }
        System.out.println("Player closed");
        sc.close();
    }

    public static PlayerActions manageAudio(Audio audio, Scanner sc) {
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

    public static PlayerActions manageVideo(Video video, Scanner sc) {
        int option;

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

    public static PlayerActions manageImage(Image image, Scanner sc) {
        int option;
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

}