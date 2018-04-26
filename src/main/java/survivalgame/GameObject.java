package survivalgame;

import javafx.geometry.Point2D;
import javafx.scene.Node;

import static survivalgame.SurvivalGameConstants.TURN_SIZE;
import static survivalgame.SurvivalGameConstants.WORLD_HEIGHT;
import static survivalgame.SurvivalGameConstants.WORLD_WIDTH;

public class GameObject {
    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private boolean isAlive = true;

    GameObject(Node view) {
        this.view = view;
    }

    public Node getView() {
        return view;
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
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void rotateRight() {
        view.setRotate(view.getRotate() + TURN_SIZE);
        Point2D newVelocity = new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate())));
        setVelocity(newVelocity.multiply(getVelocity().magnitude()));
    }

    public double getRotate() {
        return view.getRotate() - 90;
    }

    public void rotateLeft() {
        view.setRotate(view.getRotate() - TURN_SIZE);
        Point2D newVelocity = new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate())));
        setVelocity(newVelocity.multiply(getVelocity().magnitude()));
    }

    public double getDistanceFromGameObject(GameObject object) {
        Point2D rocketPosition = new Point2D(this.getView().getTranslateX(), this.getView().getTranslateY());
        Point2D targetPosition = new Point2D(object.getView().getTranslateX(), object.getView().getTranslateY());
        return targetPosition.distance(rocketPosition);
    }

    public boolean isInWorld() {
        double objectX = this.getView().getTranslateX();
        double objectY = this.getView().getTranslateY();
        if (objectX > 0 && objectX < WORLD_WIDTH &&
            objectY > 0 && objectY < WORLD_HEIGHT) {
            return true;
        }
        return false;
    }
}
