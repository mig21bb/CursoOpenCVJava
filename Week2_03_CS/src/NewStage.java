

import java.io.ByteArrayInputStream;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
        Imgcodecs.imencode(".png", m, buffer);
        Image img = new Image(new ByteArrayInputStream(buffer.toArray()));
        StackPane pane = new StackPane();
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        pane.getChildren().add(imageView);
        Scene scene = new Scene(pane);
        s.setScene(scene);
        s.setTitle(m.toString());
        s.show();
        return s;
    }

    
    
    
    
    
    
    
}
