import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

class SquarePane extends Pane {
    private double hue = 0;
    private double saturation = 1.0;
    private double value = 1.0;

    private final double side = 50;
    private double x = 0, y = 0;
    private double dx = 5, dy = 5;
    private Timeline animation;

    public SquarePane() {
        animation = new Timeline(new KeyFrame(Duration.millis(10), event -> addSquare()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void addSquare() {
        Rectangle square = new Rectangle(x,y,side,side);
        square.setFill(Color.hsb(hue, saturation, value));
        square.setStrokeWidth(0.25);
        square.setStroke(Color.BLACK);
        getChildren().add(square);

        if (x<0 || x>getWidth()-side) {
            dx *= -1;
        }
        if (y<0 || y>getHeight()-side) {
            dy *= -1;
        }

        x += dx;
        y += dy;
        hue = (hue+0.5)%360;
    }
}

public class ColorfulSquare extends Application {
    @Override
    public void start(Stage primaryStage) {
        SquarePane squarePane = new SquarePane();
        Scene scene = new Scene(squarePane, 400, 450);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}