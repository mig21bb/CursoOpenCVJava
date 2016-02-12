
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.Core.LUT;
import static org.opencv.core.CvType.CV_8U;
import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;

public class Week2_02_LUTs extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "ivvi_684x684_gray.jpg";
        Mat src = new Mat();
        Mat result = new Mat();
        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        if (src.empty()) {
            System.out.println("Error al cargar la imagen: " + NombreImagen);
        } else {
            System.out.println("Imagen cargada");
        }
       
        Mat lut = new Mat(1, 256, CV_8U);
        
        System.out.println(lut.dump());
        
        for (int i = 0; i < 256; i++){
//		lut.put(0,i,255 - i); //Función Inversa
//              lut.put(0,i,Math.round(Math.sqrt(i*255)));// Función raíz cuadrada
                lut.put(0,i,Math.round(Math.pow(i,3)/(255*255)));// Función cubica
	}
        
        System.out.println(lut.dump());
	LUT(src, lut, result);
        NewStage s = new NewStage();
        s.newWindow(src);
        s.newWindow(result);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
