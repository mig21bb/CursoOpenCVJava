
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.Core.BORDER_DEFAULT;
import static org.opencv.core.Core.addWeighted;
import static org.opencv.core.Core.convertScaleAbs;
import static org.opencv.core.CvType.CV_16S;
import static org.opencv.core.CvType.CV_8S;
import static org.opencv.core.CvType.CV_8U;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.GaussianBlur;
import static org.opencv.imgproc.Imgproc.Sobel;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.threshold;

public class Week4_04 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_684x684_gray.jpg";

        Mat src = new Mat();
        Mat src_gray = new Mat();
        Mat grad = new Mat();
        
        int scale = 1;
        int delta = 0;
        int ddepth = CV_16S;

        int c;

        /// Generar grad_x and grad_y
        Mat grad_x= new Mat(src.rows(), src.cols(), ddepth);
        Mat grad_y= new Mat(src.rows(), src.cols(), ddepth);
        Mat abs_grad_x= new Mat(src.rows(), src.cols(), ddepth);
        Mat abs_grad_y= new Mat(src.rows(), src.cols(), ddepth);
        
        
        
        

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        
        if (src.empty()) {
            System.out.println("Error al cargar la imagen");
        } else {
            System.out.println("Imagen cargada");
        }

        GaussianBlur(src, src, new Size(3, 3), 0, 0, BORDER_DEFAULT);

        /// Gradiente X
	Sobel(src, grad_x, ddepth, 1, 0, 3, scale, delta, BORDER_DEFAULT);
	convertScaleAbs(grad_x, abs_grad_x);
        
        /// Gradiente Y
	Sobel(src, grad_y, ddepth, 0, 1, 3, scale, delta, BORDER_DEFAULT);
	convertScaleAbs(grad_y, abs_grad_y);
        
        /// Total Gradiente (approximado)
	addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, grad);
        
        threshold(grad, grad, 80, 255, THRESH_BINARY);

        //Mostrar las ventanas
        NewStage s = new NewStage();

        s.newWindow(src, NombreImagen);
        s.newWindow(grad, "sobel");
//        s.newWindow(imagenFilt01, "Imagen Con realce de bordes /-1,5,-1/");
//        s.newWindow(imagenFilt02, "Imagen Con realce de bordes /-2,13,-2/");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
