
import javafx.application.Application;
import javafx.stage.Stage;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_8UC1;

import org.opencv.core.Mat;



public class Week2_04_logicOp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                
        Mat img1 = Mat.zeros(400, 400, CV_8UC1);
        Mat img2 = Mat.zeros(400, 400, CV_8UC1);
        for (int i=0; i <= img1.rows();i++){
            for (int j=0; j<= img1.cols()/2;j++){
               img1.put(i, j, 255);
            }
        }
        for (int i=150; i <= 300;i++){
            for (int j=100; j<= 300;j++){
               img2.put(i, j, 255);
            }
        }
        

        Mat res = new Mat();
        
        NewStage s = new NewStage();
        s.newWindow(img1,"Imagen 1");
        s.newWindow(img2,"Imagen 2");
        
        Core.bitwise_and(img1,img2,res);
        s.newWindow(res,"And");
        Core.bitwise_or(img1, img2, res);
        s.newWindow(res,"or");
        Core.bitwise_xor(img1, img2, res);
        s.newWindow(res,"xor");
        Core.bitwise_not(img1, res);
        s.newWindow(res,"not");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
