package survivalgame;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

import static survivalgame.SurvivalGameConstants.TURN_SIZE;

abstract class GameObject {
    private final String name;
    private ArrayList<Node> views = new ArrayList<>();
    private Point2D velocity = new Point2D(0, 0);
    private boolean isAlive = true;

    GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addView(Node view) {
        this.views.add(view);
    }

    public ArrayList<Node> getViews() {
        return views;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void update() {
        views.forEach(view -> view.setTranslateX(view.getTranslateX() + velocity.getX()));
        views.forEach(view -> view.setTranslateY(view.getTranslateY() + velocity.getY()));
    }

    public double getRotate() {
        return views.get(0).getRotate() + 90;
    }

    public void rotateRight() {
        rotate(TURN_SIZE);
    }

    public void rotateLeft() {
        rotate(-TURN_SIZE);
    }

    private void rotate(double rotation) {
        views.forEach(view -> {
            if (view.getClass().equals(Line.class)) {
                view.getTransforms().add(new Rotate(rotation));
            } else {
                view.setRotate(view.getRotate() + rotation);
            }
        });
        Point2D newVelocity = new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate())));
        setVelocity(newVelocity.multiply(getVelocity().magnitude()));
    }

    abstract public double getDistanceFromGameObject(GameObject object);
}
