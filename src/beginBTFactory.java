import javafx.scene.control.Button;

public class beginBTFactory implements ButtonFactory{
    private Button button1;
    private Button button2;
    public Button createButton(){
        button1 = new Button("开始游戏");
        button2 = new Button();
        Buttons buttons = new beginButton();
        button1 = buttons.SetEvent(button1,button2);
        return button1;
    }
}
