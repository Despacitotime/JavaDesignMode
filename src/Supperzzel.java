import java.awt.Component;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class Supperzzel extends Application {
    int counts;
    GridPane gridPane1 = new GridPane();
    Button button1 = new Button("开始游戏");
    Button button2 = new Button("游戏结束");
    TextField text1 = new TextField("400");
    int[] iNumber = new int[500];
    int[] jNumber = new int[500];
    int[][] numbers3 = new int[8][5];
    int m = 0;
    int n = 0;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    ImageView[][] imageview = new ImageView[8][5];
    ImageView[][] imageview5 = new ImageView[8][5];
    Clock clock = new Clock();
    Time time = new Time();

    public Supperzzel() {
    }

    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setTop(this.getGridPane());
        pane.setCenter(this.getGridPane2());
        this.initCountDown();
        this.initClock();
        this.initMouseEvent();
        Scene scene = new Scene(pane);
        primaryStage.setTitle("对对碰游戏");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane getGridPane() {
        this.gridPane1.setHgap(10.0D);
        this.gridPane1.setPadding(new Insets(15.0D, 5.0D, 5.0D, 5.0D));
        this.gridPane1.setStyle("-fx-background-color:white");
        this.gridPane1.add(new Label("时间："), 0, 0);
        this.gridPane1.add(new Label("分数："), 2, 0);
        this.gridPane1.add(this.text1, 3, 0);
        this.text1.setAlignment(Pos.BOTTOM_RIGHT);
        this.gridPane1.add(this.clock, 13, 0);
        this.gridPane1.add(this.button1, 15, 0);
        this.gridPane1.add(this.button2, 16, 0);
        return this.gridPane1;
    }

    public GridPane getGridPane2() {
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(50.0D, 50.0D, 50.0D, 50.0D));
        Image[] image = new Image[]{new Image("图片1.png"), new Image("图片2.png"), new Image("图片3.png"), new Image("图片4.png"), new Image("图片5.png"), new Image("图片6.png"), new Image("图片7.png")};

        int[] numbers1 = new int[20];

        int r;
        for(int i = 0; i < 20; ++i) {
            r = (int)(Math.random() * 7.0D);
            numbers1[i] = r;
        }

        int[] numbers2 = new int[40];

        for(r = 0; r < 40; ++r) {
            if (r < 20) {
                numbers2[r] = numbers1[r];
            } else {
                numbers2[r] = numbers1[r - 20];
            }
        }

        int k;
        int i;
        int j;
        for(r = 0; r < 10; ++r) {
            for(k = 0; k < 40; ++k) {
                i = (int)(Math.random() * 40.0D);
                j = numbers2[k];
                numbers2[k] = numbers2[i];
                numbers2[i] = j;
            }
        }

        r = 0;

        for(k = 0; k < 8; ++k) {
            for(i = 0; i < 5; ++i) {
                this.numbers3[k][i] = numbers2[r];
                ++r;
            }
        }

        k = 0;

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 5; ++j) {
                this.imageview5[i][j] = new ImageView(image[numbers2[k]]);
                this.imageview5[i][j].setFitHeight(76.0D);
                this.imageview5[i][j].setFitWidth(76.0D);
                gridPane2.add(this.imageview5[i][j], i, j);
                ++k;
            }
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 5; ++j) {
                this.imageview[i][j] = new ImageView("haidao00.jpg");
                this.imageview[i][j].setFitHeight(76.0D);
                this.imageview[i][j].setFitWidth(76.0D);
                gridPane2.add(this.imageview[i][j], i, j);
            }
        }

        gridPane2.setOnMousePressed((e) -> {
            int y = (int)e.getY();
            int x = (int)e.getX();
            gridPane2.getChildren().removeAll(new Node[]{this.imageview[this.getXIndex(x)][this.getYIndex(y)]});
            this.countNumber();
            this.text1.setText(String.valueOf(400 - this.counts));
            this.storeIndex(this.getXIndex(x), this.getYIndex(y));
            if (this.isCommon() && (this.iNumber[this.m - 2] != this.iNumber[this.m - 1] || this.jNumber[this.m - 2] != this.jNumber[this.m - 1])) {
                gridPane2.getChildren().removeAll(new Node[]{this.imageview5[this.iNumber[this.m - 2]][this.jNumber[this.m - 2]], this.imageview5[this.iNumber[this.m - 1]][this.jNumber[this.m - 1]]});
                this.iNumber[this.m - 1] = 0;
                this.jNumber[this.m - 1] = 0;
            }

        });
        gridPane2.setOnMouseReleased((e) -> {
            int y = (int)e.getY();
            int x = (int)e.getX();
            gridPane2.add(this.imageview[this.getXIndex(x)][this.getYIndex(y)], this.getXIndex(x), this.getYIndex(y));
            this.countNumber();
            this.text1.setText(String.valueOf(400 - this.counts));
        });
        return gridPane2;
    }

    public int getXIndex(int x) {
        int i = 0;
        if (x >= 56 && x < 132) {
            i = 0;
        } else if (x >= 132 && x < 208) {
            i = 1;
        } else if (x >= 208 && x < 284) {
            i = 2;
        } else if (x >= 284 && x < 360) {
            i = 3;
        } else if (x >= 360 && x < 436) {
            i = 4;
        } else if (x >= 436 && x < 512) {
            i = 5;
        } else if (x >= 512 && x < 588) {
            i = 6;
        } else if (x >= 588 && x < 664) {
            i = 7;
        } else {
            System.out.println("你没有选中任何图标.");
        }

        return i;
    }

    public int getYIndex(int y) {
        int j = 0;
        if (y > 60 && y < 120) {
            j = 0;
        } else if (y >= 120 && y < 196) {
            j = 1;
        } else if (y >= 196 && y < 272) {
            j = 2;
        } else if (y >= 272 && y < 348) {
            j = 3;
        } else if (y >= 348 && y < 424) {
            j = 4;
        } else {
            System.out.println("你没有选中任何图标.");
        }

        return j;
    }

    public void initClock() {
        EventHandler<ActionEvent> eventHandler = (e) -> {
            this.clock.setCurrentTime();
        };
        Timeline animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), eventHandler, new KeyValue[0])});
        animation.setCycleCount(-1);
        animation.play();
    }

    public void countNumber() {
        ++this.counts;
    }

    public boolean isCommon() {
        if (this.m > 1) {
            return this.numbers3[this.iNumber[this.m - 2]][this.jNumber[this.m - 2]] == this.numbers3[this.iNumber[this.m - 1]][this.jNumber[this.m - 1]];
        } else {
            return false;
        }
    }

    public void storeIndex(int x, int y) {
        this.iNumber[this.m] = x;
        this.jNumber[this.n] = y;
        ++this.m;
        ++this.n;
    }

    public void initMouseEvent() {
        this.button1.setOnMousePressed((e) -> {
            int hour1;
            for(hour1 = 0; hour1 < 2; ++hour1) {
                System.out.println(hour1);
            }

            hour1 = this.clock.getHour();
            int minute1 = this.clock.getMinute();
            int second1 = this.clock.getSecond();
            System.out.println(hour1 + ":" + minute1 + ":" + second1);
        });
        this.button2.setOnMousePressed((e) -> {
            int hour2 = this.clock.getHour();
            int minute2 = this.clock.getMinute();
            int second2 = this.clock.getSecond();
            System.out.println(hour2 + ":" + minute2 + ":" + second2);
            this.countsTimes();
        });
    }

    public void countsTimes() {
        int times = this.time.getHour() * 3600 + this.time.getMinute() * 60 + this.time.getSecond();
        int totaltime = 300 - times;
        int hour = totaltime / 3600;
        int minute = totaltime / 60 % 60;
        System.out.println("" + hour + ":" + minute + ":" + totaltime);
        String output1 = "您的游戏所花时间为：" + String.valueOf(hour) + ":" + minute + ":" + totaltime + "\nGameOver\n得分为：" + (400 - this.counts);
        JOptionPane.showMessageDialog((Component)null, output1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initCountDown() {
        Text text = new Text("00:05:00");
        Timeline animation2 = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), (e) -> {
            this.time.increase();
            text.setText(this.time.toString());
        }, new KeyValue[0])});
        animation2.setCycleCount(-1);
        this.button1.setOnAction((e) -> {
            if (this.button1.getText().equals("开始游戏")) {
                this.button1.setText("暂停");
                animation2.play();
            } else if (this.button1.getText().equals("暂停")) {
                this.button1.setText("继续");
                animation2.pause();
            } else if (this.button1.getText().equals("继续")) {
                this.button1.setText("暂停");
                animation2.play();
            }

        });
        this.button2.setOnAction((e) -> {
            this.button1.setText("开始游戏");
            this.button2.setText("GameOver");
            animation2.pause();
        });
        text.setFont(Font.font("Times", 20.0D));
        this.gridPane1.add(text, 1, 0);
    }
}
