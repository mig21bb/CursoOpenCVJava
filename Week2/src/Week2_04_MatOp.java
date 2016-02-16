
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class Week2_04_MatOp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen1 = "LSI.jpg";
        String NombreImagen2 = "UC3M.jpg";

        Mat img1 = new Mat();
        Mat img2 = new Mat();

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        img1 = Imgcodecs.imread(NombreImagen1);
        img2 = Imgcodecs.imread(NombreImagen2);

        if (img1.empty() || img2.empty()) {
            System.out.println("Error al cargar las imagenese");
        } else {
            System.out.println("Imagenes cargadas");
        }

        Mat dst = new Mat();

        NewStage s = new NewStage();
        s.newWindow(img1, "Imagen 1");
        s.newWindow(img2, "Imagen 2");

        Core.add(img1, img2, dst, new Mat(), -1);
        s.newWindow(dst, "Addition");
        Core.subtract(img1, img2, dst);
        s.newWindow(dst, "Substraction");
        Core.multiply(img1, img2, dst, 1.0, -1);
        s.newWindow(dst, "Multiplication");
        Core.divide(img1, img2, dst, 1.0, -1);
        s.newWindow(dst, "Division");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
