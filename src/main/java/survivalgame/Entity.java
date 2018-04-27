package survivalgame;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

abstract class Entity extends GameObject {
    private Node body;
    private Node viewArea;
    private double viewDistance;

    public Entity(String name, double bodySize, double viewDistance, Color bodyColor) {
        super(name);
        this.body = new Circle(bodySize, bodyColor);
        this.body.setOpacity(0.3);
        this.viewDistance = viewDistance;
        this.viewArea = new Circle(viewDistance, Color.GREEN);
        this.viewArea.setOpacity(0.2);
        super.addView(body);
        super.addView(viewArea);
        super.addView(new Line(body.getTranslateX(), body.getTranslateY(), body.getTranslateX(), body.getTranslateX() + viewDistance));
    }

    public Node getBody() {
        return body;
    }

    public boolean isCollidingWithViewArea(GameObject other) {
        double distanceBetweenObjects = getDistanceFromGameObjectCenter(other);
        double ourRadius = ((Circle) viewArea).getRadius();
        double otherRadius = ((Circle) other.getBody()).getRadius();
        if (distanceBetweenObjects < ourRadius + otherRadius) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidingWithBody(GameObject other) {
        double distanceBetweenObjects = getDistanceFromGameObjectCenter(other);
        double ourRadius = ((Circle) body).getRadius();
        double otherRadius = ((Circle) other.getBody()).getRadius();
        if (distanceBetweenObjects < ourRadius + otherRadius) {
            return true;
        } else {
            return false;
        }
    }

    public Point2D getCollidingBodyDirection(GameObject object) {
        return getVectorToOtherBody(object).normalize();
    }

    public double getCollidingBodyCenterDistance(GameObject object) {
        return getVectorToOtherBody(object).magnitude();
    }

//    public double getDistanceFromGameObjectCenter(GameObject object) {
//        Point2D rocketPosition = new Point2D(this.getViews().getTranslateX(), this.getViews().getTranslateY());
//        Point2D targetPosition = new Point2D(object.getViews().getTranslateX(), object.getViews().getTranslateY());
//        return targetPosition.distance(rocketPosition);
//    }

//    public boolean isInWorld() {
//        double objectX = this.getViews().getTranslateX();
//        double objectY = this.getViews().getTranslateY();
//        if (objectX > 0 && objectX < WORLD_WIDTH &&
//            objectY > 0 && objectY < WORLD_HEIGHT) {
//            return true;
//        }
//        return false;
//    }

    public abstract double calculateFitness();
}
