package survivalgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static survivalgame.SurvivalGameConstants.WORLD_HEIGHT;
import static survivalgame.SurvivalGameConstants.WORLD_WIDTH;

public class World extends Application {
    private Pane root;
    private Temp temp;
    private Obstacle obstacle;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                temp.rotateLeft();
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                temp.rotateRight();
            }
        });
        primaryStage.show();
    }

    private Pane createContent() {
        root = new Pane();
        root.setPrefSize(WORLD_WIDTH, WORLD_HEIGHT);

        temp = new Temp();
        temp.setVelocity(new Point2D(0, 1));
        addGameObject(temp,  500, 500);

        obstacle = new Obstacle();
        addGameObject(obstacle, 500, 700);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        return root;
    }

    private void addGameObject(GameObject object, double x, double y) {
        object.getViews().forEach(view -> view.setTranslateX(x));
        object.getViews().forEach(view -> view.setTranslateY(y));
        root.getChildren().addAll(object.getViews());
    }

    private void update() {
        temp.update();
        if (temp.isCollidingWithViewArea(obstacle) && temp.isAlive()) {
            temp.setVelocity(new Point2D(0, 0));
            temp.setAlive(false);

            System.out.println("temp x: " + temp.getBody().getTranslateX());
            System.out.println("temp y: " + temp.getBody().getTranslateY());

            System.out.println("osbt x: " + obstacle.getBody().getTranslateX());
            System.out.println("osbt y: " + obstacle.getBody().getTranslateY());
        }
    }

    private class Temp extends Entity {

        public Temp() {
            super("Temp", 20, 40, Color.BLUE);
        }

        @Override public double calculateFitness() {
            return 0;
        }

        @Override public boolean isInWorld() {
            return false;
        }

        @Override public double getDistanceFromGameObject(GameObject object) {
            return 0;
        }
    }

    private class Obstacle extends GameObject {
        private Node body;

        Obstacle() {
            super("obstacle");
            body = new Circle(20, Color.BLACK);
            super.addView(body);
        }

        @Override
        public double getDistanceFromGameObject(GameObject object) {
            return 0;
        }
    }
}
