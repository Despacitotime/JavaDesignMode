import javafx.scene.image.ImageView;

public abstract class icons {
    public ImageView image;
    public abstract ImageView creatIcon();
    public ImageView setImg(ImageView image){
        image.setFitHeight(76.0D);
        image.setFitWidth(76.0D);
        return image;
    }
}
