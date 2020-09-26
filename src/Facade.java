import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Facade extends Application{
    private int x,y;
    private SkinsFactory skin;
    private int[][] numbers;
    private int[] n = new int[20];
    private Button button1,button2;
    private TextField texts;
    private Clock clock;
    public int counts = 0;
    Time time = new Time();
    private ClickEvent clickEvent  = new ClickEvent();;
    private Text text;
    @Override
    public void start(Stage primaryStage){
        //用于存储图片背景标签
        StackPane pane = new StackPane();
        //用于存储按钮
        GridPane pane1 = new GridPane();
        pane1.setVgap(40);
        pane1.setHgap(40);
        pane1.setAlignment(Pos.CENTER);
        //用于存储标题
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        Label lb = new Label();
        Image image;
        image = new Image(getClass().getResourceAsStream("9.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        lb.setGraphic(imageView);

        Button btOK1 = new Button();
        btOK1.setGraphic(new ImageViews().getImgObj("jd.png"));
        Button btOK2 = new Button();
        btOK2.setGraphic(new ImageViews().getImgObj("kn.png"));

        Text nd = new Texts().getTX("难度：");
        Text wg = new Texts().getTX("外观：");
        Button btOK3 = new Button("开始游戏");
        Button btOK4 = new Button("Red");
        Button btOK5 = new Button("Bule");
        pane1.add(nd,0,1);
        pane1.add(wg,2,1);
        pane1.add(btOK1,0,2);
        pane1.add(btOK2,0,3);
        pane1.add(btOK3,1,4);
        pane1.add(btOK4,2,2);
        pane1.add(btOK5,2,3);

        Text text = new Text("对对碰游戏");
        text.setFill(new Color(0.05, 0.7, 1,0.8));
        text.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC,36));
        box.getChildren().addAll(text,pane1);

        pane.getChildren().addAll(lb,box);

        Scene scene1 = new Scene(pane);

        //设置按钮事件
        btOK1.setOnAction((e) -> {
            this.x=0;
        });
        btOK2.setOnAction((e) -> {
            this.x=1;
        });

        btOK4.setOnAction((e) ->{
            this.y=0;
        });
        btOK5.setOnAction((e) -> {
            this.y=1;
        });

        primaryStage.setTitle("对对碰游戏");
        primaryStage.setScene(scene1);
        Context context = new Context();
        btOK3.setOnMousePressed(e -> {
            if(x==0){
                context.setStrategy(new FirstBreakMethod());
                numbers = context.breakArray(n,20);
            }else if(x==1) {
                context.setStrategy(new SecondBreakMethod());
                numbers = context.breakArray(n,20);
            }else{
                x=2;
            }
            if(y==0){
                skin = new RedSkinFactory();
            }else if(y==1){
                skin = new BlueSkinFactory();
            }else{
                y=2;
            }
            BorderPane panel = new BorderPane();
            panel.setTop(this.getGridPane());
            Scene scene2 = new Scene(panel);
            panel.setCenter(this.getGridPane2());
            initMouseEvent();
            initCountDown();
            primaryStage.setScene(scene2);
        });
        primaryStage.show();
    }

    public GridPane getGridPane() {
        GridPane gridPane1 = new GridPane();
        gridPane1.setAlignment(Pos.CENTER_RIGHT);
        text = new Text("00:05:00");
        text.setFont(Font.font("Times", 20.0D));
        gridPane1.setHgap(10.0D);
        gridPane1.setPadding(new Insets(15.0D, 5.0D, 5.0D, 5.0D));
        gridPane1.setStyle("-fx-background-color:white");
        gridPane1.add(skin.creatLabel1(), 0, 0);
        gridPane1.add(text,1,0);
        gridPane1.add(skin.creatLabel2(), 2, 0);
        texts = skin.creatTextField();
        gridPane1.add(texts, 3, 0);
        clock = skin.creatClock();
        gridPane1.add(clock, 13, 0);
        this.button1 = skin.creatButton1();
        this.button2 = skin.creatButton2();
        gridPane1.add(button1, 15, 0);
        gridPane1.add(button2, 16, 0);
        return gridPane1;
    }

    public GridPane getGridPane2(){
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(50.0D, 50.0D, 50.0D, 50.0D));
//        Image[] image = new Image[]{new Image("图片1.png"), new Image("图片2.png"), new Image("图片3.png"), new Image("图片4.png"), new Image("图片5.png"), new Image("图片6.png"), new Image("图片7.png")};
        gridPane2.setAlignment(Pos.CENTER);
        int i,j;
        ImageView[][] imageview = new ImageView[8][5];
        IconsFactory factory = IconsFactory.getInstance();
        String s = "";

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 5; ++j) {
                clickEvent.numbers3[i][j] = numbers[i][j];
            }
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 5; ++j) {
                s = ""+numbers[i][j];
                icons icon = factory.getIcons(s);
                imageview[i][j] = icon.creatIcon();
                gridPane2.add(imageview[i][j], i, j);
            }
        }

//        icons con1 = factory.getIcons("1");
//        icons con2 = factory.getIcons("2");
//        icons con3 = factory.getIcons("1");
//        icons con4 = factory.getIcons("2");
//        System.out.println("判断con1和con3是否相同："+(con1 == con3));
//        System.out.println("判断con2和con4是否相同："+(con2 == con4));

        ImageView[][] imageView5 = new ImageView[8][5];
        bgicons ic;
        ic = new bgicons();
        ic.creatIcon();
        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 5; ++j) {
                bgicons im = ic.clone();
                imageView5[i][j] = im.creatIcon();
                gridPane2.add(imageView5[i][j], i, j);
            }
        }

//        ImageView img = ic.clone().creatIcon();
//        for(i=0;i<8;i++){
//            for(j=0;j<5;j++){
//                if(img==imageView5[i][j]){
//                    System.out.println("未成功复制！");
//                    break;
//                }
//                if(i==7 && j==4){
//                    System.out.println("所有对象均成功复制！");
//                }
//            }
//        }

        gridPane2.setOnMousePressed((e) -> {
            int y = (int)e.getY();
            int x = (int)e.getX();
            gridPane2.getChildren().removeAll(new Node[]{imageView5[clickEvent.getXIndex(x)][clickEvent.getYIndex(y)]});
            counts = clickEvent.countNumber(counts);
            this.texts.setText(String.valueOf(400 - this.counts));
            clickEvent.storeIndex(clickEvent.getXIndex(x), clickEvent.getYIndex(y));
            if (clickEvent.isCommon() && (clickEvent.iNumber[clickEvent.m - 2] != clickEvent.iNumber[clickEvent.m - 1] || clickEvent.jNumber[clickEvent.m - 2] != clickEvent.jNumber[clickEvent.m - 1])) {
                gridPane2.getChildren().removeAll(new Node[]{imageview[clickEvent.iNumber[clickEvent.m - 2]][clickEvent.jNumber[clickEvent.m - 2]],imageview[clickEvent.iNumber[clickEvent.m - 1]][clickEvent.jNumber[clickEvent.m - 1]]});
                clickEvent.iNumber[clickEvent.m - 1] = 0;
                clickEvent.jNumber[clickEvent.m - 1] = 0;
            }
        });
        gridPane2.setOnMouseReleased((e) -> {
            int y = (int)e.getY();
            int x = (int)e.getX();
            gridPane2.add(imageView5[clickEvent.getXIndex(x)][clickEvent.getYIndex(y)], clickEvent.getXIndex(x), clickEvent.getYIndex(y));
            counts = clickEvent.countNumber(counts);
            this.texts.setText(String.valueOf(400 - this.counts));
        });
//        //用于得到面板坐标值
//        Text texts = new Text();
//        gridPane2.getChildren().add(texts);
//        gridPane2.setOnMousePressed(e -> {
//            texts.setX(e.getX());
//            texts.setY(e.getY());
//            texts.setText("(" + e.getX() + ", " + e.getY() + ")");
//        });
        return gridPane2;
    }

    public void initMouseEvent() {
        button2.setOnMousePressed((e) -> {
            createCal();
        });
    }

    public void createCal() {
        Calculator calculator = new Calculator(time);
        calculator.countsTimes(counts);
    }

    public void initCountDown() {
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
