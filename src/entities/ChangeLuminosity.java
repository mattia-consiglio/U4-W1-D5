package entities;

public interface ChangeLuminosity {
    int maxLuminosity = 10;
    int minLuminosity = 0;

    void incrementLuminosity();

    void decrementLuminosity();

    void setLuminosityString();
}
