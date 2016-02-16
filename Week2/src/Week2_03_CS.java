import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;

import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2HSV;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2Lab;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class Week2_03_CS extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "coche-semana2.jpg";
        Mat imRGB = new Mat();
        Mat imHSV = new Mat();
        Mat imLab = new Mat();
        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        imRGB = Imgcodecs.imread(NombreImagen);
        if (imRGB.empty()) {
            System.out.println("Error al cargar la imagen: " + NombreImagen);
        } else {
            System.out.println("Imagen cargada");
        }
        
        //Separar la imagen a 3 subimagenes ( A, V y R )
        List<Mat> bgr_planes = new ArrayList<>();
	Core.split(imRGB, bgr_planes);
        
        cvtColor(imRGB, imHSV, COLOR_BGR2HSV);
        cvtColor(imRGB,imLab,COLOR_BGR2Lab);
        
	List<Mat> hsv_planes = new ArrayList<>();;
	Core.split(imHSV, hsv_planes);
        
        List<Mat> lab_planes = new ArrayList<>();;
	Core.split(imLab, lab_planes);
        
        NewStage s = new NewStage();
        s.newWindow(imRGB,"RGB");
        s.newWindow(imHSV,"HSV");
        s.newWindow(imLab,"Lab");
//        // Mostrar canales RGB        
//        for (Mat c:bgr_planes){
//            s.newWindow(c,c.toString()+);
//        }
//        // Mostrar canales HSV        
//        for (Mat c:hsv_planes){
//            s.newWindow(c,c.toString());
//        }
//        Mostrar canales HSV        
//        for (Mat c : lab_planes) {
//            s.newWindow(c, c.toString());
//        }
           s.newWindow(lab_planes.get(0),"L");
           s.newWindow(lab_planes.get(1),"a");
           s.newWindow(lab_planes.get(2),"b");
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
