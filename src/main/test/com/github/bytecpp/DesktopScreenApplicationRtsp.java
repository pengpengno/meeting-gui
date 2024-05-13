//package com.github.bytecpp;
//
//import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
//import com.github.meeting.common.connect.connection.client.ClientToolkit;
//import com.github.meeting.common.connect.connection.client.ReactiveClientAction;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.PooledByteBufAllocator;
//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.javacv.*;
//import org.bytedeco.javacv.Frame;
//
//import java.awt.*;
//import java.io.ByteArrayOutputStream;
//import java.net.InetSocketAddress;
//
//@Slf4j
//public class DesktopScreenApplicationRtsp extends Application {
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        ClientLifeStyle connect = ClientToolkit.clientLifeStyle()
//                .connect(new InetSocketAddress("localhost", 8080));
//
//        CamareScreenCapture camareScreenCapture = new CamareScreenCapture();
//
//        ReactiveClientAction reactiveClientAction = ClientToolkit.reactiveClientAction();
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        new Thread(()-> {
//            while (true){
////                recorder.get
//                if (bos.size() > 0){
//                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
//                    buffer.writeBytes(bos.toByteArray());
////
////                    DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0,
////                            RtspMethods.OPTIONS, "/live", buffer);
//
////                    reactiveClientAction.sendObject(defaultFullHttpRequest).subscribe();
//                    reactiveClientAction.sendObject(buffer).subscribe();
//
//                    bos.reset();
//                }
//
//            }
//        }).start();
//
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
//        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {// 退出时停止
////                isStop = true;
//                System.exit(0);
//            }
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
////        var audio = FFmpegFrameGrabber.createDefault("audio=virtual-auodi-capturer");
////        audio.setFormat("dshow");
//
//        desktop.setFormat("gdigrab");
////        desktop.setFormat("rtsp");
//        desktop.setFrameRate(refreshRate);
//
//        desktop.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//
//        desktop.start();
//
//
//        var recorder = new FFmpegFrameRecorder(bos, desktop.getImageWidth(), desktop.getImageHeight(),
//                desktop.getAudioChannels());
////            var recorder = FrameRecorder.createDefault(bos,
////                    desktop.getImageWidth(), desktop.getImageHeight());
//        recorder.setFormat("avi");
//
//        recorder.setGopSize(refreshRate * 2);
//
//        recorder.setFrameRate(refreshRate);
//
//        recorder.setVideoBitrate(2000000);
//
//        recorder.start();
//
//
//
//        try {
//
//            var javaFXFrameConverter = new JavaFXFrameConverter();
//
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
//
//                    desktop.release();
//
//
//                    recorder.stop();
//                    recorder.release();
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
