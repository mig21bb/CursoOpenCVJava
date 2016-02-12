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
        } else {
            System.out.println("Imagen cargada");
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
	Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(1), new Mat(), g_hist, histSize, range, accumulate);
        Imgproc.calcHist((List<Mat>) bgr_planes, new MatOfInt(2), new Mat(), r_hist, histSize, range, accumulate);
        System.out.println("Azul" + b_hist.toString()+b_hist.dump());
        System.out.println("rojo" + r_hist.toString()+r_hist.dump());
        System.out.println("Verde" + g_hist.toString()+g_hist.dump());
        
                
        int hist_w = 512; int hist_h = 400;
        long bin_w;
        bin_w = Math.round((double)(hist_w /256));
	Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3,new Scalar(0,0,0));
        Core.normalize(b_hist,b_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(g_hist,g_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        Core.normalize(r_hist,r_hist, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() ); 
        
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
                
            
            NewStage s = new NewStage();
            s.newWindow(src);
            s.newWindow(histImage);
            
//        MatOfByte buffer = new MatOfByte();
        
        
       
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
            
        
//         Create Image and ImageView objects
            
            
            
            
            
            

//        Imgcodecs.imencode(".png", src, buffer);
//        System.out.println("Cargada con imencode");
//        Image src_im = new Image(new ByteArrayInputStream(buffer.toArray()));
//        Imgcodecs.imencode(".png", histImage, buffer);
//        System.out.println("Cargada con imencode");
//        Image histogram = new Image(new ByteArrayInputStream(buffer.toArray()));
//        System.out.println(histogram.toString());
//         Create Image and ImageView objects
//        
//        
//        
//      
//         Display image on screen
//        Imgcodecs.imencode(".png", g_hist, buffer);
//        Image image = new Image(new ByteArrayInputStream(buffer.toArray()));
//        ImageView imageView0 = new ImageView();
//        ImageView imageView1 = new ImageView();
//        ImageView imageView2 = new ImageView();
//        imageView0.setImage(src_im);
//        imageView1.setImage(histogram);
//        imageView2.setImage(images.get(2));
//      
//         Display image on screen
//        StackPane im = new StackPane();
//        StackPane his = new StackPane();
//        his.getChildren().add(imageView0);
//        his.getChildren().add(imageView1);
//        root.getChildren().add(imageView1);
//        root.getChildren().add(imageView2);
//        Scene scene0 = new Scene(im);
//        Scene scene1 = new Scene(his);
//        primaryStage.setTitle("Image Read Test");
//        primaryStage.setScene(scene0);
//        primaryStage.setScene(scene1);
//        primaryStage.show();

    }
//    public void newWindow(Mat m){
//        Stage s = new Stage();
//        MatOfByte buffer = new MatOfByte();
//        Imgcodecs.imencode(".png", m, buffer);
//        System.out.println(m.toString() + "Cargada con imencode en metodo");
//        Image img = new Image(new ByteArrayInputStream(buffer.toArray()));
//        StackPane pane = new StackPane();
////        Text t = new Text(m.dump());
//        ImageView imageView = new ImageView();
//        imageView.setImage(img);
//        pane.getChildren().addAll(imageView);
//        Scene scene = new Scene(pane);
//        s.setScene(scene);
//        s.setTitle(m.toString());
//        s.show();
//        
//    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
}
