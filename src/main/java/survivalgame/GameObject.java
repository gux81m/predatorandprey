package survivalgame;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.util.ArrayList;

import static survivalgame.SurvivalGameConstants.TURN_SIZE;

abstract class GameObject {
    private final String name;
    private ArrayList<Node> views = new ArrayList<>();
    private Node body;
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
        if (views.size() == 1) {
            if (Circle.class.equals(view.getClass())) {
                body = view;
            } else {
                throw new RuntimeException("Body is not a Circle!");
            }
        }
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

    public double getRotate() {
        return body.getRotate() + 90;
    }

    public Node getBody() {
        return body;
    }

    public double getDistanceFromGameObjectCenter(GameObject object) {
        return getVectorToOtherBody(object).magnitude();
    }

    public Point2D getVectorToOtherBody(GameObject other) {
        double x = other.getBody().getTranslateX() - this.body.getTranslateX();
        double y = other.getBody().getTranslateY() - this.body.getTranslateY();
        return new Point2D(x, y);
    }

    public Point2D getPosition() {
        double x = this.body.getTranslateX();
        double y = this.body.getTranslateY();
        return new Point2D(x, y);
    }

    public double getAngleBetweenVelocityAndCollidingObject(GameObject object) {
        Point2D vectorToOtherBody = getVectorToOtherBody(object);
        double dotProduct = this.velocity.dotProduct(this.getVectorToOtherBody(object));
        double determinant = this.velocity.getX() * vectorToOtherBody.getY() - this.velocity.getY() * vectorToOtherBody.getX();
        double angleInRadiant = Math.atan2(determinant, dotProduct);
        double angleInDegrees = Math.toDegrees(angleInRadiant);
        return angleInDegrees > 0 ? angleInDegrees : 360 + angleInDegrees;
    }

    public boolean isInWorld(){
        return true;
    };
}
