import java.io.ByteArrayInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
 
public class Week1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        String NombreImagen = "ivvi.jpg";
        Mat m = Imgcodecs.imread(NombreImagen);
        if (m.empty()) {
            System.out.println("Error al cargar la imagen: " + NombreImagen);
        }
        // encode the frame in a buffer
        MatOfByte buffer = new MatOfByte();        
        Imgcodecs.imencode(".png", m, buffer);
        Image image = new Image(new ByteArrayInputStream(buffer.toArray()));
        // Create Image and ImageView objects
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        // Display image on screen 
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Image Read Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
