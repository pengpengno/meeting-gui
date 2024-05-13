//package com.github.bytecpp;
//
//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.javacv.*;
//import org.bytedeco.javacv.Frame;
//import org.junit.jupiter.api.Test;
//
//import java.awt.*;
//
//@Slf4j
//public class DesktopScreenApplication extends Application {
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setTitle("desktop screen");
//
//        ImageView imageVideo = new ImageView();// 用于软件录制显示
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//        var screenDevices = ge.getScreenDevices();
//
//        for (var screenDevice : screenDevices) {
//            log.info("{}",screenDevice);
//        }
//        VBox box = new VBox();
//
//        box.getChildren().addAll( imageVideo);
//
//        stage.setScene(new Scene(box));
//
//        stage.show();
//        stage.setOnCloseRequest(event -> {// 退出时停止
////                isStop = true;
//            System.exit(0);
//        });
//        Integer screen = 0;
//
//
//        var screenDevice = screenDevices[screen];
//
//        var refreshRate = screenDevice.getDisplayMode().getRefreshRate();
//
//        log.info("refreshRate : {}",refreshRate);
//
//        refreshRate = 60 ;
//
////        OpenCVFrameGrabber desktop = new OpenCVFrameGrabber(0);
//        FFmpegFrameGrabber desktop = new FFmpegFrameGrabber("desktop");
//
////        var audio = FFmpegFrameGrabber.createDefault("audio=virtual-auodi-capturer");
////        audio.setFormat("dshow");
//
//        desktop.setFormat("gdigrab");
////        desktop.setFormat("rtsp");
//        desktop.setFrameRate(refreshRate);
//
//        desktop.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//
////        desktop.setVideoFrameNumber(0);
//
//        desktop.start();
//
////        desktop.setVideoBitrate(2000000);
//
//
//        try {
//
//            var recorder = FrameRecorder.createDefault("output2.avi",
//                    desktop.getImageWidth(), desktop.getImageHeight());
//
//            recorder.setGopSize(refreshRate * 2);
//
//            recorder.setFrameRate(refreshRate);
//
//            recorder.setVideoBitrate(2000000);
//
//            var javaFXFrameConverter = new JavaFXFrameConverter();
//
//            recorder.start();
//
//            new Thread(()->{
//                try {
//                    Frame frame = null;
//
//                    while ( (frame = desktop.grab()) != null){
//
////                        canvasFrame.setCanvasSize(frame.imageWidth, frame.imageHeight);
//
//                        imageVideo.setImage(javaFXFrameConverter.convert(frame));
//
////                        canvasFrame.showImage(frame);
//
//                        recorder.record(frame);
//
//                    }
//
//                    desktop.stop();
//
//                    desktop.release();
//                }
//                catch (Exception ex){
//                    log.error("{}",ex);
//                }
//
//            }).start();
//
//
//        }catch (Exception exception){
//
//            log.error("{}",exception);
//
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
