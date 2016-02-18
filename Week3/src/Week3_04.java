
import static java.lang.Double.max;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.Core.flip;
import org.opencv.core.Rect;
import static org.opencv.core.CvType.CV_32SC1;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.warpAffine;
import static org.opencv.imgproc.Imgproc.warpAffine;

public class Week3_04 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "IVVI.jpg";

        Mat src = new Mat();
        Mat dst = new Mat();

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);

        if (src.empty()) {
            System.out.println("Error al cargar las imagenes");
        } else {
            System.out.println("Imagenes cargadas");
        }
        NewStage s = new NewStage();
        //scale
        Imgproc.resize(src, dst, new Size(0, 0), 0.5, 0.5, Imgproc.INTER_LINEAR);

        s.newWindow(src, "Imagen Original");
        s.newWindow(dst, "Scale");

        //Traslation
        dst = new Mat().zeros(src.size(), src.type());
        src.submat(100, src.rows(), 100, src.cols()).copyTo(dst.submat(0, src.rows() - 100, 0, src.cols() - 100));
        s.newWindow(dst, "Traslation");
        
        //Rotate
        int len = (int) max(src.cols(),src.rows());
        double angle = 60;

        Point pt = new Point(len/2.0, len/2.0);
        Mat r = Imgproc.getRotationMatrix2D(pt, angle, 1.0);
        warpAffine(src,dst,r,new Size(len,len));
        s.newWindow(dst, "Rotation");
        
        //Reflection
        flip(src,dst,1);
        s.newWindow(dst, "Reflection");
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
