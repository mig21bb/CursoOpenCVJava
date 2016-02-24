
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import static org.opencv.core.CvType.CV_8UC3;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.line;



public class Week4_02 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_low_contrast.jpg";
        
	List<Mat> original_img = new ArrayList<>(); 
         

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        
        Mat src = new Mat();
        src = Imgcodecs.imread(NombreImagen);
        System.out.println("Src.type"+src.type());
        //Para realizar la equialización hay que convertir la imagen a escala de grises.
        Imgproc.cvtColor(src, src, COLOR_BGR2GRAY);
        if (src.empty()) {
            System.out.println("Error al cargar la imagen");
        } else {
            System.out.println("Imagen cargada");
        }
        original_img.add(Imgcodecs.imread(NombreImagen));
        List<Mat> equaliz_img = new ArrayList<>();
        Mat equalized_img = new Mat(original_img.get(0).rows(),original_img.get(0).cols(),original_img.get(0).type());
        
        
        //Variables para el histograma
	MatOfInt histSize = new MatOfInt(255);
	/// el rango del nivel del gris 0-255
	MatOfFloat range = new MatOfFloat(0,256);
	
        
	/// imagen del histograma
	int hist_w = 512;
        int hist_h = 400;
        long bin_w = Math.round((double)(hist_w /256));	
        Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3,new Scalar(0,0,0));
	Mat equalizedHistImage = new Mat(hist_h, hist_w, CV_8UC3, new Scalar(0, 0, 0));
        
        //calcular el histograma
	Mat original_hist = new Mat();
        Mat normalized_hist = new Mat();
        Mat equalized_hist = new Mat();
        Mat equalized_normalized_hist = new Mat();
        
        
	Imgproc.calcHist((List<Mat>)original_img, new MatOfInt(1),new Mat(), original_hist, histSize, range, false);
        
        // Mostrar los valores del histograma de la imagen original
        System.out.println("histograma original"+original_hist.dump());
        
        /// Normalizar el resultado a [ 0, histImage.rows ]
        Core.normalize(original_hist,normalized_hist, 0, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() );
        
        // Mostrar los valores del histograma normalizado
        System.out.println("histograma normalizado"+normalized_hist.dump());
        
        // Equalize histogram from a grayscale image	
	Imgproc.equalizeHist( src, equalized_img);
        equaliz_img.add(equalized_img);

	Imgproc.calcHist((List<Mat>)equaliz_img, new MatOfInt(0),new Mat(), equalized_hist, histSize, range, false);
        
        // Mostrar los valores del histograma de la imagen original
        System.out.println("histograma equalizado"+equalized_hist.dump());
        
        /// Normalizar el resultado a [ 0, histImage.rows ]
        Core.normalize(equalized_hist,equalized_normalized_hist, 0, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() );
        
        // Mostrar los valores del histograma normalizado
        System.out.println("histograma equalizado normalizado"+equalized_normalized_hist.dump());
        
        /// dibujar los histogramas
        for( int i = 0; i < 255; i++ ){
        line(histImage,
                new Point( bin_w*(i), hist_w),
                new Point( bin_w*(i),hist_h - Math.round(normalized_hist.get(i,0)[0])),
                new Scalar( 255, 0, 0),
                Math.round(bin_w), 8, 0 );
        line(equalizedHistImage,
                new Point( bin_w*(i), hist_w),
                new Point( bin_w*(i), hist_h - Math.round(equalized_normalized_hist.get(i,0)[0])),
                new Scalar( 0, 255, 0),
                Math.round(bin_w), 8, 0 );
        }

        NewStage s = new NewStage();
        
        s.newWindow(original_img.get(0), NombreImagen);
        s.newWindow(equalized_img, "Imagen equalizada");
        s.newWindow(histImage, "Original histogram");
        s.newWindow(equalizedHistImage, "Equalized histogram");

        
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
