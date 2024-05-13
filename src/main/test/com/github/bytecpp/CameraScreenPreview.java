//package com.github.bytecpp;
//
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.ffmpeg.global.avutil;
//import org.bytedeco.javacv.CanvasFrame;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.FrameGrabber;
//import org.bytedeco.javacv.OpenCVFrameGrabber;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Size;
//import org.bytedeco.opencv.opencv_core.IplImage;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class CameraScreenPreview {
//
//    public static void main(String[] args) throws FrameGrabber.Exception, AWTException {
//        // Start camera preview
//        startCameraPreview();
//
//        // Start screen preview
//        startScreenPreview();
//    }
//
//    private static void startCameraPreview() throws FrameGrabber.Exception {
//        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//        grabber.start();
//        CanvasFrame canvasFrame = new CanvasFrame("Camera Preview", CanvasFrame.getDefaultGamma() / grabber.getGamma());
//        canvasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        while (true) {
//            Frame frame = grabber.grab();
////            IplImage frame = (IplImage) grab;
//            if (frame == null) {
//                break;
//            }
//            canvasFrame.showImage(frame);
////            opencv_imgproc.cvResize(frame, frame, Size.ZERO, 0.5, 0.5, opencv_imgproc.CV_INTER_LINEAR);
//        }
//        canvasFrame.dispose();
//        grabber.stop();
//    }
//
//    private static void startScreenPreview() throws AWTException {
//        Robot robot = new Robot();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Rectangle screenRect = new Rectangle(screenSize);
//        CanvasFrame screenFrame = new CanvasFrame("Screen Preview");
//        screenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        while (true) {
//            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
//            screenFrame.showImage(screenCapture);
//            try {
//                Thread.sleep(100); // Adjust the delay according to your preference
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
