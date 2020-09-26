import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BlueSkinFactory implements SkinsFactory {
    public Button button;
    public Button creatButton1(){
        ButtonFactory buttonFactory = new beginBTFactory();
        button = buttonFactory.createButton();
        button.setStyle("-fx-background-color: rgb(111, 201, 243)");
        return button;
    };
    public Button creatButton2(){
        ButtonFactory buttonFactory = new endBTFactory(button);
        button = buttonFactory.createButton();
        button.setStyle("-fx-background-color:  rgb(111, 201, 243)");
        return button;
    };
    public TextField creatTextField(){
        TextField text1 = new TextField("400");
        text1.setStyle("-fx-background-color:  rgb(185, 233, 255)");
        text1.setAlignment(Pos.BOTTOM_RIGHT);
        return text1;
    };
    public Label creatLabel1(){
        Label label = new Label("时间：");
        label.setStyle("-fx-background-color:  rgb(111, 201, 243)");
        return label;
    };
    public Label creatLabel2(){
        Label label = new Label("分数：");
        label.setStyle("-fx-background-color:  rgb(111, 201, 243)");
        return label;
    };
    public Clock creatClock(){
        Clock clock = new Clock();
        clock.setStyle("-fx-background-color:  rgb(56, 143, 184)");
        clock = clock.initClock(clock);
        return clock;
    };
}
