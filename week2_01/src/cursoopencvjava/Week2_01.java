/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cursoopencvjava;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.opencv.core.Core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
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
public class Week2_01 extends Application{

    @Override
    public void start(Stage primaryStage) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Nombre del fichero / File name
        String NombreImagen = "Tulips.jpg";
        Mat src;
        //Cargamos la imagen y se comprueba que lo ha hecho correctamente
        //Get the image and check       
        src = Imgcodecs.imread(NombreImagen);
        if (src.empty()) {
            System.out.println("Error al cargar la imagen: " + NombreImagen);
        }
        
        /// Separar la imagen a 3 subimagenes ( A, V y R )
        List<Mat> bgr_planes = new ArrayList<>();
        Core.split(src, bgr_planes);
        
        System.out.println(bgr_planes.get(0).toString());
        System.out.println(bgr_planes.get(0).getClass());
        
        
        //Variables para el histograma
	

	/// los rangos (A,V,R) 
	MatOfFloat range = new MatOfFloat(0,256);
	MatOfInt histSize = new MatOfInt(255);
               
        boolean uniform = true;
	boolean accumulate = false;
        
        Mat b_hist = new Mat();
        Mat g_hist = new Mat();
        Mat r_hist = new Mat();
       
        
        Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, range, accumulate);
	Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(0), new Mat(), g_hist, histSize, range, accumulate);
        Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(0), new Mat(), r_hist, histSize, range, accumulate);
        System.out.println("Azul" + b_hist.toString()+b_hist.dump());
        System.out.println("rojo" + r_hist.toString()+r_hist.dump());
        System.out.println("Verde" + g_hist.toString()+g_hist.dump());
        
                
        int hist_w = 500; int hist_h = 500;
        int bin_w = Core.cvRound((double)(hist_w / histSize));
	Mat histImage = Mat.zeros( 100, (int)histSize.get(0, 0)[0], CvType.CV_8UC1);
        Core.normalize(b_hist,b_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(g_hist,g_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(r_hist,r_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        
        for( int i = 0; i < (int)histSize.get(0, 0)[0]; i++ )
{                   
        line(
                histImage,
                new Point( i, histImage.rows() ),
                new Point( i, histImage.rows()-Math.round( b_hist.get(i,0)[0] )) ,
                new Scalar( 255, 0, 0),
                2, 8, 0 );
        line(
                histImage,
                new Point( i, histImage.rows() ),
                new Point( i, histImage.rows()-Math.round( r_hist.get(i,0)[0] )) ,
                new Scalar( 0, 255, 0),
                2, 8, 0 );
        line(
                histImage,
                new Point( i, histImage.rows() ),
                new Point( i, histImage.rows()-Math.round( g_hist.get(i,0)[0] )) ,
                new Scalar( 0, 0, 255),
                2, 8, 0 );
}
        MatOfByte buffer = new MatOfByte();
        
        
       
//         encode the frame in the buffer
//        int i = 0;
//        for (Mat c:bgr_planes){
//            
//            Imgcodecs.imencode(".png", c, buffer);
//            System.out.println("Canal cargado con imencode"+i);
//            Image e = new Image(new ByteArrayInputStream(buffer.toArray()));
//            images.add(e);
//            System.out.println("imagen canal "+i +"  " +images.get(i).toString());
//                        
//            i += 1;
//        }
        
        Imgcodecs.imencode(".png", src, buffer);
        System.out.println("Cargada con imencode");
        Image src_im = new Image(new ByteArrayInputStream(buffer.toArray()));
        Imgcodecs.imencode(".png", histImage, buffer);
        System.out.println("Cargada con imencode");
        Image histogram = new Image(new ByteArrayInputStream(buffer.toArray()));
        System.out.println(histogram.toString());
//         Create Image and ImageView objects
        
        
        
      
        // Display image on screen
        Imgcodecs.imencode(".png", b_hist, buffer);
//        Image image = new Image(new ByteArrayInputStream(buffer.toArray()));
        ImageView imageView0 = new ImageView();
        ImageView imageView1 = new ImageView();
//        ImageView imageView2 = new ImageView();
        imageView0.setImage(src_im);
        imageView1.setImage(histogram);
//        imageView2.setImage(images.get(2));
      
        // Display image on screen
//        StackPane im = new StackPane();
        StackPane his = new StackPane();
//        im.getChildren().add(imageView0);
        his.getChildren().add(imageView1);
//        root.getChildren().add(imageView1);
//        root.getChildren().add(imageView2);
//        Scene scene0 = new Scene(im);
        Scene scene1 = new Scene(his);
        primaryStage.setTitle("Image Read Test");
//        primaryStage.setScene(scene0);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
