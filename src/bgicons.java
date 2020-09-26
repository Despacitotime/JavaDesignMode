import javafx.scene.image.ImageView;

public class bgicons implements Cloneable{
    private ImageView images;
    public ImageView creatIcon(){
        images = new ImageView("haidao00.jpg");
        images.setFitHeight(76.0D);
        images.setFitWidth(76.0D);
        return images;
    }
    public bgicons clone(){
        Object obj = null;
        try{
            obj = super.clone();
            return (bgicons)obj;
        }
        catch (CloneNotSupportedException e){
            System.out.println("不支持复制");
            return null;
        }
    }

}
