//package com.github.bytecpp;
//
//import cn.hutool.core.exceptions.ExceptionUtil;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.ffmpeg.global.avutil;
//import org.bytedeco.javacv.*;
//
//import java.io.ByteArrayOutputStream;
//
//
///**
// * @author pengpeng
// * @description
// * @date 2023/11/2
// */
//@Slf4j
//public class CamareScreenCapture {
//
//    private FrameGrabber grabber = null ;
//
//    private FFmpegFrameRecorder recorder;// 推流录制器
//
//
//    @Getter
//    @AllArgsConstructor
//    public enum CameraType{
//        RTSP("rtsp"),
//        RTMP("rtmp"),
//        DESKTOP("desktop"),
//        ;
//        private final String name;
//
//    }
//
//    public FrameGrabber createCamera() {
//
//        FrameGrabber grabber = new OpenCVFrameGrabber(0);
////        FrameGrabber grabber = new FFmpegFrameGrabber("desktop");
//
//        grabber.setFormat("gdigrab");
////        linux use this
////        grabber.setFormat("x11grab");
//        grabber.setFrameRate(28);
//
//        this.grabber = grabber;
//        return grabber;
//    }
//
//    /**
//     * 创建拉流器
//     *
//     * @return
//     */
//    protected FrameGrabber createGrabber(CameraType cameraType) {
//        // 拉流器
//        grabber = new FFmpegFrameGrabber(cameraType.getName());
//        // 超时时间(15秒)
////        grabber.setOption("stimeout", cameraDto.getNetTimeout());
//        grabber.setOption("threads", "1");
//        // grabber.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
//        // 设置缓存大小，提高画质、减少卡顿花屏
//        grabber.setOption("buffer_size", "1024000");
//
//        // 读写超时，适用于所有协议的通用读写超时
////        grabber.setOption("rw_timeout", cameraDto.getReadOrWriteTimeout());
//        // 探测视频流信息，为空默认5000000微秒
////        grabber.setOption("probesize", cameraDto.getReadOrWriteTimeout());
//        // 解析视频流信息，为空默认5000000微秒
////        grabber.setOption("analyzeduration", cameraDto.getReadOrWriteTimeout());
//
//        //针对某些协议或数据有问题的流，一起动就关闭，可以尝试下面参数
////		grabber.setAudioStream(1);
//
//        // 如果为rtsp流，增加配置
//        if ("rtsp".equals(cameraType.getName())) {
//            // 设置打开协议tcp / udp
//            grabber.setOption("rtsp_transport", "tcp");
//            // 首选TCP进行RTP传输
//            grabber.setOption("rtsp_flags", "prefer_tcp");
//
//        } else if ("rtmp".equals(cameraType.getName())) {
//            // rtmp拉流缓冲区，默认3000毫秒
//            grabber.setOption("rtmp_buffer", "1000");
//            // 默认rtmp流为直播模式，不允许seek
//            // grabber.setOption("rtmp_live", "live");
//
//        } else if ("desktop".equals(cameraType.getName())) {
//            // 支持本地屏幕采集，可以用于监控屏幕、局域网和wifi投屏等
//            grabber.setFormat("gdigrab");
//            grabber.setOption("draw_mouse", "1");// 绘制鼠标
//            grabber.setNumBuffers(0);
//            grabber.setOption("fflags", "nobuffer");
//            grabber.setOption("framerate", "25");
//            grabber.setFrameRate(25);
//        }
//
//        try {
//            grabber.start();
//            log.info("\r\n{}\r\n启动拉流器成功", cameraType.name);
////            return grabberStatus = true;
//        } catch (FrameGrabber.Exception e) {
////            MediaService.cameras.remove(cameraDto.getMediaKey());
//            log.error("\r\n{}\r\n启动拉流器失败，网络超时或视频源不可用", cameraType.name);
////			e.printStackTrace();
//        }
////        return grabberStatus = false;
//        return grabber;
//    }
//
//
//
//    /**
//     * 创建转码推流录制器
//     *
//     * @return
//     */
//    protected FFmpegFrameRecorder createTransterOrRecodeRecorder() {
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        recorder = new FFmpegFrameRecorder(bos, grabber.getImageWidth(), grabber.getImageHeight(),
//                grabber.getAudioChannels());
//
//        recorder.setFormat("avi");
//        if (true) {
//            // 转码
//            recorder.setInterleaved(false);
//            recorder.setVideoOption("tune", "zerolatency");
//            recorder.setVideoOption("preset", "ultrafast");
//            recorder.setVideoOption("crf", "26");
//            recorder.setVideoOption("threads", "1");
//            recorder.setFrameRate(25);// 设置帧率
//            recorder.setGopSize(25);// 设置gop,与帧率相同，相当于间隔1秒chan's一个关键帧
////			recorder.setVideoBitrate(500 * 1000);// 码率500kb/s
////			recorder.setVideoCodecName("libx264");	//javacv 1.5.5无法使用libx264名称，请使用下面方法
//            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
//            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
////			recorder.setAudioCodecName("aac");
//            recorder.setOption("keyint_min", "25");	//gop最小间隔
//
//            /**
//             * 启用RDOQ算法，优化视频质量 1：在视频码率和视频质量之间取得平衡 2：最大程度优化视频质量（会降低编码速度和提高码率）
//             */
//            recorder.setTrellis(1);
//            recorder.setMaxDelay(0);// 设置延迟
//            try {
//                recorder.start();
//                return recorder;
//            } catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
//                log.error("启动转码录制器失败", e1);
////                MediaService.cameras.remove(cameraDto.getMediaKey());
//            }
//        } else {
//            // 转复用
//            // 不让recorder关联关闭outputStream
//            recorder.setCloseOutputStream(false);
//            try {
//                recorder.start();
//                return recorder;
//            } catch (org.bytedeco.javacv.FrameRecorder.Exception e) {
////                log.warn("\r\n{}\r\n启动转复用录制器失败", cameraDto.getUrl());
//                // 如果转复用失败，则自动切换到转码模式
////                transferFlag = false;
//                if (recorder != null) {
//                    try {
//                        recorder.stop();
//                    } catch (org.bytedeco.javacv.FrameRecorder.Exception e1) {
//                    }
//                }
////                if (createTransterOrRecodeRecorder()) {
//////                    log.error("\r\n{}\r\n切换到转码模式", cameraDto.getUrl());
////                    return true;
////                }
////                log.error("\r\n{}\r\n切换转码模式失败", cameraDto.getUrl());
//                log.error("\r\n{}\r\n切换转码模式失败 \n {} ", ExceptionUtil.stacktraceToString(e));
//            }
//        }
//        return recorder;
//    }
//
//
//    public void grabber(){
//        // Create a frame grabber for your camera (you can also load a video file)
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//
//        try {
//            // Start the grabber
//            grabber.start();
//
//            // Create a canvas frame for displaying the video
//            CanvasFrame canvasFrame = new CanvasFrame("Video Processing");
//
//            canvasFrame.setCanvasSize(800, 600);
//
//            // Create a frame converter
////            OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();
//
//            while (true) {
//                Frame frame = grabber.grab();
//                if (frame == null) {
//                    break;
//                }
//
//
////                frame.data
//
//                // Process the frame (e.g., perform some OpenCV operations)
//                // Modify the frame here as needed
//
//                // Display the processed frame
////                canvasFrame.showImage(frameConverter.convert(frame));
////                canvasFrame.showImage(frame);
//
//                // Exit the application when the user closes the window
//                if (canvasFrame.getCanvas().isShowing()) {
//                    canvasFrame.setDefaultCloseOperation(CanvasFrame.DO_NOTHING_ON_CLOSE);
//                } else {
//                    break;
//                }
//            }
//
//            // Release resources
//            grabber.stop();
//            canvasFrame.dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
