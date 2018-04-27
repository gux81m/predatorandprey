package survivalgame;

import java.util.ArrayList;

public class Receptor {
    private int receptorCount;
    private double degreesBetweenReceptors;
    private ArrayList<Double> sensitivities;

    Receptor(int receptorCount) {
        this.receptorCount = receptorCount;
        this.degreesBetweenReceptors = 360 / receptorCount;
        this.sensitivities = new ArrayList<>(receptorCount);
        fillSensitivity();
    }

    private void fillSensitivity() {
        // standard normal distribution
    }

    public double getInputsFromWorld() {
        return 0;
    }

}
