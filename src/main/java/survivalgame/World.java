package survivalgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static survivalgame.SurvivalGameConstants.WORLD_HEIGHT;
import static survivalgame.SurvivalGameConstants.WORLD_WIDTH;

public class World extends Application {
    private Pane root;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private Pane createContent() {
        root = new Pane();
        root.setPrefSize(WORLD_WIDTH, WORLD_HEIGHT);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        return root;
    }

    private void update() {

    }
}
