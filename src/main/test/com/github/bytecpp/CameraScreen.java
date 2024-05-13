//package com.github.bytecpp;
//
//import com.github.meeting.common.connect.connection.client.ClientLifeStyle;
//import com.github.meeting.common.connect.connection.client.ClientToolkit;
//import com.github.meeting.common.connect.connection.client.ReactiveClientAction;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.PooledByteBufAllocator;
//import org.bytedeco.javacv.*;
//
//import java.io.ByteArrayOutputStream;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//
//public class CameraScreen {
//    public static void main(String[] args) throws Exception {
//
//        ClientLifeStyle connect = ClientToolkit.clientLifeStyle()
//                .connect(new InetSocketAddress("localhost", 8080));
//
//        CamareScreenCapture camareScreenCapture = new CamareScreenCapture();
//
//        ReactiveClientAction reactiveClientAction = ClientToolkit.reactiveClientAction();
//
//        // Create a frame grabber for your camera (you can also load a video file)
//        FrameGrabber grabber = camareScreenCapture.createCamera();
////
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//
//        OpenCVFrameRecorder recorder = OpenCVFrameRecorder.createDefault("bos.avi", grabber.getImageWidth(), grabber.getImageHeight());
//        recorder.setFormat("avi");
//
////        recorder.setGopSize(refreshRate * 2);
//
//        recorder.setFrameRate(28);
//
//        recorder.setVideoBitrate(2000000);
//
//
//        recorder.start();
//
//
//        new Thread(()-> {
//            while (true){
////                recorder.get
//                if (bos.size() > 0){
//                    ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
//                    buffer.writeBytes(bos.toByteArray());
//
////                    DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0,
////                            RtspMethods.OPTIONS, "/live", buffer);
//
//                    reactiveClientAction.sendObject(buffer).subscribe();
//
//                    bos.reset();
//                }
//
//            }
//        }).start();
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
//
//            while (true) {
//                Frame frame = grabber.grab();
//                if (frame == null) {
//                    break;
//                }
//                if (frame.data != null){
//                    ByteBuffer data = frame.data;
//                    bos.write(data.array());
//                }
//
//                // Display the processed frame
////                canvasFrame.showImage(frameConverter.convert(frame));
//                canvasFrame.showImage(frame);
//                recorder.record(frame);
//                recorder.flush();
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
//            recorder.stop();
//            canvasFrame.dispose();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}