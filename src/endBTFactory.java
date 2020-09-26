import javafx.scene.control.Button;

public class endBTFactory implements ButtonFactory{
    private Button button1;
    private Button button2;
    public endBTFactory(Button button1){
        this.button1 = button1;
    }
    public Button createButton(){
        button2 = new Button("游戏结束");
        Buttons buttons = new endButton();
        button2 = buttons.SetEvent(button1,button2);
        return button2;
    }
}
