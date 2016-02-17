
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_32SC1;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Week3_02 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "corr_norm.tif";
        String NombreModelo = "modelo.tif";

        Mat src = new Mat();
        Mat templ = new Mat();

        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        templ = Imgcodecs.imread(NombreModelo);

        if (src.empty() || templ.empty()) {
            System.out.println("Error al cargar las imagenes");
        } else {
            System.out.println("Imagenes cargadas");
        }

        ArrayList<Mat> ftmp = new ArrayList<>(6);
        int iwidth;
        int iheight;

        //Reservamos memoria para los diversos metodos
        iwidth = src.cols() - templ.cols() + 1;
        iheight = src.rows() - templ.rows() + 1;
        System.out.println("iwidth " + iwidth + "iheight " + iheight);

        for (int i = 0; i < 6; i++) {
            ftmp.add(i, new Mat(iheight, iwidth, CV_32SC1));

        }
        //Correlacion	
        for (int i = 0; i < 6; i++) {
            Imgproc.matchTemplate(src, templ, ftmp.get(i), i);

            Core.normalize(ftmp.get(i), ftmp.get(i), 0, 255, Core.NORM_MINMAX);

        }

        NewStage s = new NewStage();
        s.newWindow(src, "Imagen Original");
        s.newWindow(templ, "Modelo");
        s.newWindow(ftmp.get(0), "SQDIFF");
        s.newWindow(ftmp.get(1), "SQDIFF_NORMED");
        s.newWindow(ftmp.get(2), "CCORR");
        s.newWindow(ftmp.get(3), "CCORR_NORMED");
        s.newWindow(ftmp.get(4), "CCOEFF");
        s.newWindow(ftmp.get(5), "CCOEFF_NORMED");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
