import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Texts extends Text {
    private Text text;
    public Text getTX(String s){
        text = new Text(s);
        text.setFill(new Color(0.05, 0.7, 1,0.8));
        text.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC,20));
        return text;
    }
}
