//package com.github.bytecpp;
//
//import cn.hutool.core.exceptions.ExceptionUtil;
//import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
//import com.github.meeting.common.connect.connection.client.ClientToolkit;
//import com.github.meeting.common.connect.connection.client.ReactiveClientAction;
////import com.github.meeting.common.cv.ScreenShare;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import lombok.extern.slf4j.Slf4j;
////import org.bytedeco.javacv.Frame;
////import org.bytedeco.javacv.JavaFXFrameConverter;
//
//import java.net.InetSocketAddress;
//
//@Slf4j
//public class DesktopScreenApplicationRtspBack extends Application {
//
//
//    @Override
//    public void start(Stage stage) throws Exception {
//        Boolean isStop = Boolean.TRUE;
//        ClientLifeStyle connect = ClientToolkit.clientLifeStyle()
//                .connect(new InetSocketAddress("localhost", 8080));
//
//        ScreenShare screenShare = new ScreenShare();
//
//
//        ReactiveClientAction reactiveClientAction = ClientToolkit.reactiveClientAction();
//
//        stage.setTitle("desktop screen");
//
//        ImageView imageVideo = new ImageView();// 用于软件录制显示
//
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
//
//
//        try {
//
//            screenShare.startGrabber();
//            screenShare.recorderStart();
//
//            screenShare.configSendConsumer((data) ->
//                    reactiveClientAction.sendObject(data).subscribe());
//
//            var javaFXFrameConverter = new JavaFXFrameConverter();
//
//            new Thread(()->{
//                try {
//                    Frame frame = null;
//
//                    while ( (frame = screenShare.grab()) != null){
//
//                        imageVideo.setImage(javaFXFrameConverter.convert(frame));
//
//                        screenShare.recorder(frame);
//
//                    }
//                    screenShare.release();
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
//            log.error("exception \n{}", ExceptionUtil.stacktraceToString(exception));
//
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}
