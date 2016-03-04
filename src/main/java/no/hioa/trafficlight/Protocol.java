package no.hioa.trafficlight;

/**
 * Created by Simen on 04.03.2016.
 */
public interface Protocol {
    int BLINKING_YELLOW = 0;
    int RED = 1;
    int YELLOW = 2;
    int GREEN = 3;

    int getState();

    void updateInstructions();

    void setState(String state);

    void setInstructionState(String state, int milliseconds);

}