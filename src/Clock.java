import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Clock extends Pane {
    private int hour;
    private int minute;
    private int second;
    private double w = 100.0D;
    private double h = 100.0D;
    private Clock clock;

    public Clock() {
        this.setCurrentTime();
    }

    public Clock(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.paintClock();
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.paintClock();
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        this.paintClock();
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
        this.paintClock();
    }

    public double getW() {
        return this.w;
    }

    public void setW(double w) {
        this.w = w;
        this.paintClock();
    }

    public double getH() {
        return this.h;
    }

    public void setH(double h) {
        this.h = h;
        this.paintClock();
    }

    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
        this.paintClock();
    }

    protected void paintClock() {
        double clockRadius = Math.min(this.w, this.h) * 0.8D * 0.5D;
        double centerX = this.w / 2.0D;
        double centerY = this.h / 2.0D;
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX - 5.0D, centerY - clockRadius + 12.0D, "12");
        Text t2 = new Text(centerX - clockRadius + 3.0D, centerY + 5.0D, "9");
        Text t3 = new Text(centerX + clockRadius - 10.0D, centerY + 3.0D, "3");
        Text t4 = new Text(centerX - 3.0D, centerY + clockRadius - 3.0D, "6");
        double sLength = clockRadius * 0.8D;
        double secondX = centerX + sLength * Math.sin((double)this.second * 0.10471975511965977D);
        double secondY = centerY - sLength * Math.cos((double)this.second * 0.10471975511965977D);
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);
        double mLength = clockRadius * 0.65D;
        double xMinute = centerX + mLength * Math.sin((double)this.second * 0.10471975511965977D);
        double minuteY = centerY - sLength * Math.cos((double)this.second * 0.10471975511965977D);
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);
        double hLength = clockRadius * 0.5D;
        double hourX = centerX + hLength * Math.sin(((double)(this.hour % 12) + (double)this.minute / 60.0D) * 0.5235987755982988D);
        double hourY = centerY - hLength * Math.cos(((double)(this.hour % 12) + (double)this.minute / 60.0D) * 0.5235987755982988D);
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);
        this.getChildren().clear();
        this.getChildren().addAll(new Node[]{circle, t1, t2, t3, t4, sLine, mLine, hLine});
    }

    public Clock initClock(Clock clock) {
        this.clock = clock;
        EventHandler<ActionEvent> eventHandler = (e) -> {
            this.clock.setCurrentTime();
        };
        Timeline animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), eventHandler, new KeyValue[0])});
        animation.setCycleCount(-1);
        animation.play();
        return clock;
    }
}
