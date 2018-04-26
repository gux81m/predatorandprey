package survivalgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static survivalgame.SurvivalGameConstants.WORLD_HEIGHT;
import static survivalgame.SurvivalGameConstants.WORLD_WIDTH;

public class World extends Application {
    private Pane root;
    private Temp temp;

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
}
