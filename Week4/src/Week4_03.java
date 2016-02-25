
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_8S;
import static org.opencv.core.CvType.CV_8U;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Week4_03 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_684x684_gray.jpg";
        
	Mat imagen = new Mat(); 
         

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        
        imagen = Imgcodecs.imread(NombreImagen);
        
        //Para realizar la equialización hay que convertir la imagen a escala de grises.
        
        if (imagen.empty()) {
            System.out.println("Error al cargar la imagen");
        } else {
            System.out.println("Imagen cargada");
        }
        
        Mat imagenFilt00= new Mat(imagen.rows(), imagen.cols(), CV_8U);
        Mat imagenFilt01= new Mat(imagen.rows(), imagen.cols(), CV_8U);
        Mat imagenFilt02= new Mat(imagen.rows(), imagen.cols(), CV_8U);
        
        Mat kernel00 = new Mat(3,3,CV_8S);
        Mat kernel01 = new Mat(3,3,CV_8S);
        Mat kernel02 = new Mat(3,3,CV_8S);
        
        kernel00.put(0,0, -1); kernel00.put(1,0, -1); kernel00.put(2,0, -1);
	kernel00.put(0,1, -1); kernel00.put(1,1, 9); kernel00.put(2,1, -1);
	kernel00.put(0,2, -1); kernel00.put(1,2, -1); kernel00.put(2,2, -1);
        
        kernel01.put(0,0, 0); kernel01.put(1,0, -1); kernel01.put(2,0, 0);
	kernel01.put(0,1, -1); kernel01.put(1,1, 5); kernel01.put(2,1, -1);
	kernel01.put(0,2, 0); kernel01.put(1,2, -1); kernel01.put(2,2, 0);
        
        kernel02.put(0,0, -1); kernel02.put(1,0, -2); kernel02.put(2,0, -1);
	kernel02.put(0,1, -2); kernel02.put(1,1, 13); kernel02.put(2,1, -2);
	kernel02.put(0,2, -1); kernel02.put(1,2, -2); kernel02.put(2,2, -1);
        
        Imgproc.filter2D(imagen, imagenFilt00, CV_8U, kernel00);
        Imgproc.filter2D(imagen, imagenFilt01, CV_8U, kernel01);
        Imgproc.filter2D(imagen, imagenFilt02, CV_8U, kernel02);
        
        //Mostrar las ventanas
        NewStage s = new NewStage();
        
        s.newWindow(imagen, NombreImagen);
        s.newWindow(imagenFilt00, "Imagen Con realce de bordes /-1,9,-1/");
        s.newWindow(imagenFilt01, "Imagen Con realce de bordes /-1,5,-1/");
        s.newWindow(imagenFilt02, "Imagen Con realce de bordes /-2,13,-2/");

        
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
