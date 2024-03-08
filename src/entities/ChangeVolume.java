package entities;

public interface ChangeVolume {
    int maxVolume = 10; // This is a constant.
    int minVolume = 0; // This is a constant. I experimented, this is not AI generated.

    void incrementVolume();

    void decrementVolume();

    void setVolumeString();

    void displayVolume();
}
