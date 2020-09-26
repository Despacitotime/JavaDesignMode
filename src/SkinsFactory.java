import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface SkinsFactory {
    public Button creatButton1();
    public Button creatButton2();
    public TextField creatTextField();
    public Label creatLabel1();
    public Label creatLabel2();
    public Clock creatClock();
}

