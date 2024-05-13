//package com.github.fx;
//
//import com.github.meeting.gui.app.controller.VideoController;
//import javafx.concurrent.Worker;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import netscape.javascript.JSObject;
//
///**
// * Description:
// * <p>
// * </p>
// *
// * @author pengpeng
// * @version 1.0
// * @since 2024/4/18
// */
//public class JsLoaderTest {
//
//    public void loadjs() {
//
//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//
//        // 加载HTML文件
//        webEngine.load(getClass().getResource("your_html_file.html").toExternalForm());
//
//        // 将Java对象注入到WebView中
//        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue == Worker.State.SUCCEEDED) {
//                JSObject window = (JSObject) webEngine.executeScript("window");
//                window.setMember("javaApp", new VideoController());
//                // 使用的时候就是 在 js 中 javaApp 来调用
//            }
//        });
//
//        Scene scene = new Scene(webView, 800, 600);
//
////        primaryStage.setTitle("FXML WebView Example");
////        primaryStage.setScene(scene);
////        primaryStage.show();
//    }
//}