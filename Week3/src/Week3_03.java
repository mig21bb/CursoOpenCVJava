
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_32SC1;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Week3_03 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "IMG.jpg";
        String NombreModelo = "MM.jpg";
//        String NombreModelo = "MJ.jpg";
//        String NombreModelo = "MC.jpg";

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

        
        int iwidth;
        int iheight;

        //Reservamos memoria para los diversos metodos
        iwidth = src.cols() - templ.cols() + 1;
        iheight = src.rows() - templ.rows() + 1;
        System.out.println("iwidth " + iwidth + "iheight " + iheight);
        
        Mat dst= new Mat(iheight, iwidth, CV_32SC1);
        
        int match_method = Imgproc.TM_CCOEFF_NORMED;

        
        //Correlacion	
        
            Imgproc.matchTemplate(src, templ, dst, match_method);
            Core.normalize(dst, dst, 0, 255, Core.NORM_MINMAX);

        double minVal, maxVal;
        Point minLoc,maxLoc;
        Point matchLoc;
        
        minVal = Core.minMaxLoc(dst).minVal;
        maxVal = Core.minMaxLoc(dst).maxVal;
        minLoc = Core.minMaxLoc(dst).minLoc;
        maxLoc = Core.minMaxLoc(dst).maxLoc;
        
        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED)
	{
		matchLoc = minLoc;
	}
	else
	{
		matchLoc = maxLoc;
	}

        Imgproc.rectangle(src, matchLoc, new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()), new Scalar(255,0,0),4, 8, 0);
        Imgproc.rectangle(dst, new Point(matchLoc.x - (templ.cols() / 2), matchLoc.y - (templ.rows() / 2)), new Point(matchLoc.x + (templ.cols() / 2), matchLoc.y + (templ.rows() / 2)), new Scalar(0,0,0), 4, 8, 0);
        
        NewStage s = new NewStage();
        s.newWindow(src, "Imagen Original");
        s.newWindow(templ, "Modelo");
        s.newWindow(dst, "result");
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
