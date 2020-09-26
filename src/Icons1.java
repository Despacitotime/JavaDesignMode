import javafx.scene.image.ImageView;

public class Icons1 extends icons{
    public ImageView creatIcon(){
        ImageView image = new ImageView("图片1.png");
        image = setImg(image);
        return image;
    }
}
