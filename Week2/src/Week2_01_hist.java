


import java.util.ArrayList;

import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.core.CvType;

import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.line;

/**
 *
 * @author Administrador
 */
public class Week2_01_hist extends Application{

    @Override
    public void start(Stage primaryStage) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_684x684.jpg";
        Mat src;
        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        if (src.empty()) {
            System.out.println("Error al cargar la imagen: " + NombreImagen);
        } else {
            System.out.println("Imagen cargada");
        }
        
        /// Separar la imagen a 3 subimagenes ( A, V y R )
        List<Mat> bgr_planes = new ArrayList<>();
        Core.split(src, bgr_planes);
        
        //Variables para el histograma
	/// los rangos (A,V,R) 
	MatOfFloat range = new MatOfFloat(0,256);
	MatOfInt histSize = new MatOfInt(255);
               
	boolean accumulate = false;
        
        Mat b_hist = new Mat();
        Mat g_hist = new Mat();
        Mat r_hist = new Mat();       
        
        Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, range, accumulate);
	Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(1), new Mat(), g_hist, histSize, range, accumulate);
        Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(2), new Mat(), r_hist, histSize, range, accumulate);
                
        // Dibujar el histograms para A, V y R        
        int hist_w = 512;
        int hist_h = 400;
        long bin_w;
        bin_w = Math.round((double)(hist_w /256));
        
	Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3,new Scalar(0,0,0));
        /// Normalizar el resultado a [ 0, histImage.rows ]
        Core.normalize(b_hist,b_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(g_hist,g_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(r_hist,r_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        /// Dibujar para  cada canal
        for( int i = 0; i < 255; i++ ){
        line(
                histImage,
                new Point( bin_w*(i-1), hist_h - Math.round(b_hist.get(i-1,0)[0])),
                new Point( bin_w*(i), hist_h - Math.round(b_hist.get(i,0)[0])),
                new Scalar( 255, 0, 0),
                2, 8, 0 );
        line(
                histImage,
                new Point( bin_w*(i-1), hist_h - Math.round(g_hist.get(i-1,0)[0])),
                new Point( bin_w*(i), hist_h - Math.round(g_hist.get(i,0)[0])),
                new Scalar( 0, 255, 0),
                2, 8, 0 );
        line(
                histImage,
                new Point( bin_w*(i-1), hist_h - Math.round(r_hist.get(i-1,0)[0])),
                new Point( bin_w*(i), hist_h - Math.round(r_hist.get(i,0)[0])),
                new Scalar( 0, 0, 255),
                2, 8, 0 );
                }
                
          //Mostrar imagen original e histograma a través de una nueva clase.
          /// El método newWindow(Mat m) crea una ventana con la imagen m como parámetro.
            NewStage s = new NewStage();
            s.newWindow(src,"Imagen");
            s.newWindow(histImage,"Histograma");
            
   }    
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
