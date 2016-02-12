
package cursoopencvjava;
import java.io.ByteArrayInputStream;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class NewStage extends Stage{
    
    Mat m = new Mat();

    public NewStage() {
        
    }    
    
    public Stage newWindow(Mat m){
        Stage s = new Stage();
        MatOfByte buffer = new MatOfByte();
        System.out.println("New window - m. channels " + m.channels());
        Imgcodecs.imencode(".png", m, buffer);
        System.out.println(m.toString() + "Cargada con imencode");
        Image img = new Image(new ByteArrayInputStream(buffer.toArray()));
        StackPane pane = new StackPane();
        Text t = new Text(m.toString()+"Altura:"+ m.height()+" Anchura:"+m.width());
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        pane.getChildren().addAll(imageView,t);
        Scene scene = new Scene(pane);
        s.setScene(scene);
        s.setTitle(m.toString());
        s.show();
        return s;
    }

    
    
    
    
    
    
    
}
