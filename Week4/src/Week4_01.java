


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.*;
import static org.opencv.core.CvType.CV_32SC1;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.GaussianBlur;


public class Week4_01 extends Application {    
    
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_512x512_gray_rg.jpg";
//        String NombreImagen = "ivvi_512x512_gray_ri.jpg";
        String NombreImagenIvvi = "ivvi_512x512_gray.jpg";
        
	Mat src = new Mat();
        Mat imBlr = new Mat();
        Mat imGus = new Mat();
        Mat imMed = new Mat();
        Mat imBil = new Mat();
        Mat ivvi = new Mat();
        
       

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        ivvi = Imgcodecs.imread(NombreImagenIvvi);

        if (src.empty() || ivvi.empty()) {
            System.out.println("Error al cargar las imagenes");
        } else {
            System.out.println("Imagenes cargadas");
        }

        
        // Homogeneous blur
	Imgproc.blur(src, imBlr, new Size(5, 5), new Point(-1, -1), 4);

	// Gaussian blur
	GaussianBlur(src, imGus, new Size(5, 5), 0, 0, 4);

	// Median blur
	Imgproc.medianBlur(src, imMed, 5);

	//bilateral filter
	Imgproc.bilateralFilter(src, imBil, 15, 80, 80);
        
        NewStage s = new NewStage();
        s.newWindow(ivvi, NombreImagenIvvi);
        s.newWindow(src, NombreImagen);
        s.newWindow(imBlr, "Homogeneous blur");
        s.newWindow(imGus, "Gaussian");
        s.newWindow(imMed, "Median");
        s.newWindow(imBil, "Bilateral");
        
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
