
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_8UC3;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;



public class Week4_02 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_low_contrast.jpg";
        
	Mat src = new Mat();   

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);

        if (src.empty()) {
            System.out.println("Error al cargar la imagen");
        } else {
            System.out.println("Imagen cargada");
        }
        
        //Variables para el histograma
	int histSize = 256;
	/// el rango del nivel del gris 0-255
	float range[] = { 0, 256 };
	final float histRange = { range };

	/// imagen del histograma
	int hist_w = 512; int hist_h = 400;
	int bin_w = cvRound((double)hist_w / histSize);
	Mat histImage(hist_h, hist_w, CV_8UC3, Scalar(0, 0, 0));
	Mat equalizedHistImage(hist_h, hist_w, CV_8UC3, Scalar(0, 0, 0));
    
        NewStage s = new NewStage();
        
        s.newWindow(src, NombreImagen);
//        s.newWindow(imBlr, "Homogeneous blur");
//        s.newWindow(imGus, "Gaussian");
//        s.newWindow(imMed, "Median");
//        s.newWindow(imBil, "Bilateral");
        
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
