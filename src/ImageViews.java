import javafx.scene.image.ImageView;

public class ImageViews extends ImageView{
    private ImageView img;
    public ImageView getImgObj(String s){
        img = new ImageView(s);
        img.setFitWidth(50);
        img.setFitHeight(30);
        img.setStyle("-fx-background-color: #000000");
        return img;
    }
}
