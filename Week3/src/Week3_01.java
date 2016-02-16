


import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Week3_01 extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "IMG.jpg";
        

        Mat src = new Mat();
        Mat dst = new Mat();

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        

        if (src.empty() ) {
            System.out.println("Error al cargar la imagen");
        } else {
            System.out.println("Imagenes cargadas");
        }
        
        Mat kernel = new Mat().ones(5, 5, CvType.CV_32F);
        kernel.setTo(new Scalar(.04));
        
        System.out.println(kernel.dump());
        
        Imgproc.filter2D(src, dst, -1, kernel, new Point(-1,-1), 0, 1);

        NewStage s = new NewStage();
        s.newWindow(src, "Original");
        s.newWindow(dst, "Filter");

        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
