//package com.github.meeting.gui.app.controller;
//
//
//import com.github.meeting.common.connect.module.GuiceModuleInjector;
////import com.github.meeting.common.cv.ScreenGrabber;
//import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
////import org.bytedeco.javacv.Frame;
////import org.bytedeco.javacv.FrameGrabber;
////import org.bytedeco.javacv.Java2DFrameUtils;
////import org.bytedeco.javacv.OpenCVFrameConverter;
////import org.opencv.android.Utils;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
///**
// * Description:
// * <p>
// * </p>
// *
// * @author pengpeng
// * @version 1.0
// * @since 2024/4/17
// */
//
//@Slf4j
//public class VideoController implements Initializable {
//
//    @FXML
//    private ImageView cameraView;
//
//    @FXML
//    private ImageView screenShareView;
//
//    @FXML
//    private MediaView videoView;
//
//    @FXML
//    private Button camera;
//
//    @FXML
//    private Button screen;
//
//
////    private final ScreenGrabber screenGrabber = GuiceModuleInjector.getInstance(ScreenGrabber.class);
//
////
////    private FrameGrabber desktopGrabber;
////    private FrameGrabber cameraGrabber;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        initialize();
//    }
//
//    public void initialize() {
////        Scene scene = cameraView.getScene();
////        scene.getc
//        // Load camera data (replace with your camera logic)
////        Image cameraImage = new Image("path/to/your/camera/image.jpg");
////        cameraView.setImage(cameraImage);
//
//        // Load screen share data (replace with your screen sharing logic)
////        Image screenShareImage = new Image("path/to/your/screen/share/image.png");
////        screenShareView.setImage(screenShareImage);
//        var instance = GuiceModuleInjector.getInstance(ScreenGrabber.class);
//        // Load video data (replace with your video logic)
////        String videoFilePath = "path/to/your/local/video/file.mp4";
////        Media media = new Media(new File(videoFilePath).toURI().toString());
////        MediaPlayer mediaPlayer = new MediaPlayer(media);
////        videoView.setMediaPlayer(mediaPlayer);
////        mediaPlayer.setAutoPlay(true);
////        startScreenCapture();
//    }
//
//
//    public static Image bufferedImageToImage(BufferedImage bufferedImage) {
//        try {
//            // 将 BufferedImage 转换为 byte 数组
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", os);
//            byte[] imageData = os.toByteArray();
//
//            // 将 byte 数组转换为 JavaFX Image
//            ByteArrayInputStream is = new ByteArrayInputStream(imageData);
//            return new Image(is);
//        } catch (IOException e) {
//            log.error("error",e);
//            return null;
//        }
//    }
//
//    private FrameGrabber startScreenCapture() {
//        var grabber = screenGrabber.desktop();
//        new Thread(() -> {
//            try {
////                if (!grabber.isTriggerMode()){
////                    log.error("local desktop screen could not open!");
////                    return;
////                }
//
//                while (!Thread.interrupted()) {
//                    Frame frame = grabber.grab();
//                    if (frame != null) {
//                        var bufferedImage = Java2DFrameUtils.toBufferedImage(frame);
////                        Java2DFrameUtils.toIplImage(frame).
//                        screenShareView.setImage(bufferedImageToImage(bufferedImage));
//                    }
//                }
//            } catch (Exception e) {
//                log.error("errror",e);
//            }
//        }).start();
//        return grabber;
//    }
//
//
//    @SneakyThrows
//    public FrameGrabber camera() {
//        FrameGrabber camera1 = screenGrabber.camera();
////        if (!camera1.isTriggerMode()){
////            return null;
////        }
//
//        new Thread(()-> {
//            try{
////                while(true){
//                while (!Thread.interrupted()) {
//                    Frame frame = camera1.grab();
//                    if (frame!= null){
//                        var bufferedImage = Java2DFrameUtils.toBufferedImage(frame);
//
//                        cameraView.setImage(bufferedImageToImage(bufferedImage));
//
//                    }
//                }
//            }
//              catch (FrameGrabber.Exception exception){
//                        log.error("camera exception ",exception);
//              }
//        }).start();
//        return camera1;
//    }
//
//    @FXML
//    @SneakyThrows
//    private void shareCamera() {
//        // Add logic to share camera
//        log.info("click camera");
//
//        if (cameraGrabber!=null){
//            cameraGrabber.close();
//            cameraGrabber = null;
//        }
//        cameraGrabber = camera();
//
//        System.out.println("Sharing camera...");
//    }
//
//    @FXML
//    @SneakyThrows
//    private void shareScreen() {
//        // Add logic to share screen
//        log.info("click screen");
//        if (desktopGrabber!=null){
//            desktopGrabber.close();
//            desktopGrabber = null;
//        }
//        desktopGrabber = startScreenCapture();
//    }
//}
