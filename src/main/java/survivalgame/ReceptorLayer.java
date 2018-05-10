package survivalgame;

import java.util.Map;

public class ReceptorLayer {
    private int receptorCount;
    private double degreesBetweenReceptors;
    private double[] inputMatrix;
    private double[] sensitivityMatrix;
    private double[] receptorMatrix;

    ReceptorLayer(int receptorCount) {
        this.receptorCount = receptorCount;
        this.degreesBetweenReceptors = 360 / receptorCount;
        this.sensitivityMatrix = new double[receptorCount];
        this.receptorMatrix = new double[receptorCount];
        this.inputMatrix = new double[receptorCount];
        calculateSensitivity();
        System.out.println("Receptor layer created with " + receptorCount + " receptors.");
    }

    private void calculateSensitivity() {
        // TODO: standard normal distribution
        for (int index = 0; index < receptorCount; index++) {
            sensitivityMatrix[index] = 1.0;
        }
    }

    /**
     *  receptorMatrix = sensitivityMatrix * inputMatrix(T)
     * @param inputsFromWorld (angle, distance)
     */
    public void calculateReceptorActivities(Map<Double, Double> inputsFromWorld) {
        calculateInputMatrix(inputsFromWorld);
        printMatrix("Sensitivity matrix", sensitivityMatrix);
        printMatrix("Input matrix", inputMatrix);
        for (int index = 0; index < receptorMatrix.length; index++) {
            receptorMatrix[index] = sensitivityMatrix[index] * inputMatrix[index];
        }
        printMatrix("Receptor matrix", receptorMatrix);
    }

    /**
     *
     * @param inputsFromWorld (angle, distance)
     */
    private void calculateInputMatrix(Map<Double, Double> inputsFromWorld) {
        clearInputMatrix();
        inputsFromWorld.forEach((angle, distance) -> {
            int receptorIndex = calculateReceptorIndex(angle);
            this.inputMatrix[receptorIndex] = distance;
            System.out.println("Angle: " + angle + ",receptor index: " + receptorIndex + ", distance: " + distance);
        });
    }

    private void clearInputMatrix() {
        for (int index = 0; index < inputMatrix.length; index++) {
            inputMatrix[index] = 0.0;
        }
    }

    private int calculateReceptorIndex(double angle) {
        int receptorIndex = 0;
        // TODO: calculate receptor index
        return receptorIndex;
    }

    private void printMatrix(String name, double[] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ": [");
        for (int index = 0; index < matrix.length; index++) {
            stringBuilder.append(matrix[index]);
            if (index != matrix.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder);
    }
}
