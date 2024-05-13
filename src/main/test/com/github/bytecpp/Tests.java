//package com.github.bytecpp;
//
//import com.github.meeting.common.connect.module.GuiceModuleInjector;
//import com.github.meeting.common.cv.ScreenGrabber;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.javacv.*;
//import org.bytedeco.javacv.Frame;
//import org.junit.jupiter.api.Test;
//import reactor.core.publisher.Flux;
//import reactor.netty.http.client.HttpClient;
//
//import java.awt.*;
//
//@Slf4j
//public class Tests {
//
//
//    @Test
//    public void start () {
//
//        DesktopScreenApplication.main(new String[]{});
//
//    }
//    @Test
//    public void cameraShare () throws Exception {
//
//        CameraScreen.main(new String[]{});
//
//    }
//
//
//
//    @Test
//    public void startDesktopRtsp () {
//
//        DesktopScreenApplicationRtsp.main(new String[]{});
//
//    }
//
//    @Test
//    public void startDesktopRtspBak () {
//
//        DesktopScreenApplicationRtspBack.main(new String[]{});
//
//    }
//
//    @Test
//    public void DesktopScreenApplicationRtspClient () {
//
//        DesktopScreenApplicationRtspClient.main(new String[]{});
//
//    }
//
//
//
//    @Test
//    public void startMyLive () {
//
//        MyLive.main(new String[]{});
//
//    }
//
//
//    @Test
//    public void startJavaFxPlayer1 () {
//
//        ScreenGrabber instance = GuiceModuleInjector.getInstance(ScreenGrabber.class);
//
//        FrameGrabber frameGrabber = instance.desktopScreenCapture();
//
//
//    }
//    @Test
//    public void startJavaFxPlayer () {
//
//        JavaFxPlayVideoAndAudio.main(new String[]{});
//
//    }
//
//
//    @Test
//    @SneakyThrows
//    public void test() {
//        CanvasFrame canvasFrame = new CanvasFrame("Video Processing");
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//
//        var screenDevices = ge.getScreenDevices();
//
//        for (var screenDevice : screenDevices) {
//            log.info("{}",screenDevice);
//        }
//        Integer screen = 0;
//
//        var screenDevice = screenDevices[screen];
//        var refreshRate = screenDevice.getDisplayMode().getRefreshRate();
//
//        log.info("refreshRate : {}",refreshRate);
//
//        refreshRate = 10 ;
//
//        var desktop = FFmpegFrameGrabber.createDefault("desktop");
//
//        var audio = FFmpegFrameGrabber.createDefault("audio=virtual-auodi-capturer");
//
//        audio.setFormat("dshow");
//
//        desktop.setFormat("gdigrab");
//
//        desktop.setFrameRate(refreshRate);
//
//        desktop.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//
//        desktop.setVideoFrameNumber(0);
//
//        desktop.start();
//
//        desktop.setVideoBitrate(2000000);
//
//
//        try {
//
//            var recorder = FrameRecorder.createDefault("output2.avi", desktop.getImageWidth(), desktop.getImageHeight());
//
//            recorder.setFormat("avi");
//
//            recorder.start();
//
//
//            new Thread(()->{
//                try {
//                    Frame frame = null;
//
//                while ( (frame = desktop.grab()) != null){
//
//                    canvasFrame.setCanvasSize(frame.imageWidth, frame.imageHeight);
//
//                    canvasFrame.showImage(frame);
//
//                    recorder.setSampleRate(desktop.getSampleRate());
//
////                recorder.record(frame);
//
//                    log.info("{}",frame);
//                    // Exit the application when the user closes the window
////                if (canvasFrame.getCanvas().isShowing()) {
////                    canvasFrame.setDefaultCloseOperation(CanvasFrame.DO_NOTHING_ON_CLOSE);
////                } else {
////                    break;
////                }
//
//                }
//
////                    recorder.stop();
////                    desktop.stop();
////
////                    recorder.release();
////                    desktop.release();
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
//
//    }
//}
