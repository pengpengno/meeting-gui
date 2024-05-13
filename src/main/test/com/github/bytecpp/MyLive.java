//package com.github.bytecpp;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.VBox;
//import javafx.scene.robot.Robot;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.javacv.*;
//
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.TargetDataLine;
//import java.io.File;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.ShortBuffer;
//import java.util.Timer;
//import java.util.TimerTask;
///**
// * @author lingkang
// * Created by 2022/5/10
// */
//public class MyLive extends Application {
//    private static final int frameRate = 24;// 录制的帧率
//    private static boolean isStop = false;
//
//    private static TargetDataLine line;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("lingkang-桌面录屏大师");
//        ImageView imageVideo = new ImageView();// 用于软件录制显示
//        imageVideo.setFitWidth(800);
//        imageVideo.setFitHeight(600);
//        Button button = new Button("停止录制");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                isStop = true;
//                if (line != null) {// 马上停止声音录入
//                    try {
//                        line.close();
//                    } catch (Exception e) {
//                    }
//                }
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("info");
//                alert.setHeaderText("已经停止录制");
//                alert.setOnCloseRequest(event1 -> alert.hide());
//                alert.showAndWait();
//            }
//        });
//
//        VBox box = new VBox();
//        box.getChildren().addAll(button, imageVideo);
//        primaryStage.setScene(new Scene(box));
//        primaryStage.setHeight(600);
//        primaryStage.setWidth(800);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {// 退出时停止
//                isStop = true;
//                System.exit(0);
//            }
//        });
//
//        // 帧记录
//        // window 建议使用 FFmpegFrameGrabber("desktop") 进行屏幕捕捉
//        FrameGrabber grabber = new FFmpegFrameGrabber("desktop");
//        grabber.setFormat("gdigrab");
//        grabber.setFrameRate(frameRate);// 帧获取间隔
//        // 捕获指定区域，不设置则为全屏
////        grabber.setImageHeight(600);
////        grabber.setImageWidth(800);
//        // grabber.setOption("offset_x", "200");
//        // grabber.setOption("offset_y", "200");//必须设置了大小才能指定区域起点，参数可参考 FFmpeg 入参
//        grabber.start();
//
//        // 直播推流
////        final FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(
////                "rtmp://10.8.4.191/live/livestream",
////                grabber.getImageWidth(), grabber.getImageHeight(), 2);
//
//        var recorder = FrameRecorder.createDefault("output.flv",
//                grabber.getImageWidth(), grabber.getImageHeight());
//
//        // 用于存储视频 , 调用stop后，需要释放，就会在指定位置输出文件，，这里我保存到D盘
//        //FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault(file, grabber.getImageWidth(), grabber.getImageHeight());
//        recorder.setInterleaved(true);
//        // https://trac.ffmpeg.org/wiki/StreamingGuide
//        recorder.setVideoOption("tune", "zerolatency");// 加速
//        // https://trac.ffmpeg.org/wiki/Encode/H.264
//        recorder.setVideoOption("preset", "ultrafast");
//        recorder.setFrameRate(frameRate);// 设置帧率，重要！
//        // Key frame interval, in our case every 2 seconds -> 30 (fps) * 2 = 60
//        recorder.setGopSize(frameRate * 2);
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);// 编码，使用编码能让视频占用内存更小，根据实际自行选择
//        // https://trac.ffmpeg.org/wiki/Encode/H.264
//        recorder.setVideoOption("crf", "28");
//        // 2000 kb/s  720P
//        recorder.setVideoBitrate(2000000);
//        recorder.setFormat("flv");
//
//
//        // 添加音频录制
//        // 不可变音频
//        recorder.setAudioOption("crf", "0");
//        // 最高音质
//        recorder.setAudioQuality(0);
//        // 192 Kbps
//        recorder.setAudioBitrate(192000);
//        recorder.setSampleRate(44100);
//        recorder.setAudioChannels(2);
//        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
//
//        recorder.start();
//
//        // 44100  16声道
//        AudioFormat audioFormat = new AudioFormat(44100.0F, 16, 2, true, false);
//        DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
//        // 可以捕捉不同声道
//        line = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
//        // 录制声音
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    line.open(audioFormat);
//                    line.start();
//
//                    final int sampleRate = (int) audioFormat.getSampleRate();
//                    final int numChannels = audioFormat.getChannels();
//
//                    // 缓冲区
//                    final int audioBufferSize = sampleRate * numChannels;
//                    final byte[] audioBytes = new byte[audioBufferSize];
//                    Timer timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            try {
//                                if (isStop) {// 停止录音
//                                    line.stop();
//                                    line.close();
//                                    System.out.println("已经停止！");
//                                    timer.cancel();
//                                }
//
//                                // 读取音频
//                                // read会阻塞
//                                int readLenth = 0;
//                                while (readLenth == 0)
//                                    readLenth = line.read(audioBytes, 0, line.available());
//
//                                // audioFormat 定义了音频输入为16进制，需要将字节[]转为短字节[]
//                                // FFmpegFrameRecorder.recordSamples 源码中的 AV_SAMPLE_FMT_S16
//                                int rl = readLenth / 2;
//                                short[] samples = new short[rl];
//
//                                // short[] 转换为 ShortBuffer
//                                ByteBuffer.wrap(audioBytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(samples);
//
//                                ShortBuffer sBuff = ShortBuffer.wrap(samples, 0, rl);
//
//                                // 记录
////                                recorder.record(sampleRate, numChannels, sBuff);
////                                recorder.record();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, 1000, 1000 / frameRate);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    // 获取屏幕捕捉的一帧
//                    Frame frame = null;
//                    // 屏幕录制，由于已经对音频进行了记录，需要对记录时间进行调整即可
//                    // 即上面调用了 recorder.recordSamples 需要重新分配时间，否则视频输出时长等于实际 的2倍
//                    while ((frame = grabber.grab()) != null) {
//                        if (isStop) {
//                            try {
//                                // 停止
//                                recorder.stop();
//                                grabber.stop();
//                                // 释放内存，我们都知道c/c++需要手动释放资源
//                                recorder.release();
//                                grabber.release();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        }
//
//                        // 将这帧放到录制
//                        recorder.record(frame);
//                        Image convert = new JavaFXFrameConverter().convert(frame);
//                        imageVideo.setImage(convert);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
