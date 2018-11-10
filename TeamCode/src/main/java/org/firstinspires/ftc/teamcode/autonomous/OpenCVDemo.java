package org.firstinspires.ftc.teamcode.autonomous;
/*
import android.app.Activity;
import android.util.Log;
import android.view.SurfaceView;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.RelicRecoveryHardware;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;


@Autonomous(name="OpenCV Demo")
public class OpenCVDemo extends RelicRecoveryHardware implements CameraBridgeViewBase.CvCameraViewListener2 {

    enum CVDetectedObject {
        CUBE,
        SPHERE,
        UNKNOWN
    }

    Mat imageMat;
    private BaseLoaderCallback mLoaderCallback;
    private CameraBridgeViewBase mOpenCvCameraView;

    private int red = -1;
    private int green = -1;
    private int blue = -1;
    private final int YELLOW_BLUE_THRESHOLD = 40;

    private int focusSize = 50;
    private Point averagePoint1 = new Point(640/2 - focusSize/2,480/2 - focusSize/2);
    private Point averagePoint2 = new Point(640/2 + focusSize/2,480/2 + focusSize/2);
    private Point textPoint = new Point(10, 450);

    public void onCameraViewStarted(int width, int height) {

    }

    public void onCameraViewStopped() {
        imageMat.release();
    }

    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        imageMat = inputFrame.rgba();
        Mat rotatedImageMat = new Mat();

        Core.rotate(imageMat, rotatedImageMat, Core.ROTATE_90_CLOCKWISE);
        Imgproc.resize(rotatedImageMat, rotatedImageMat, imageMat.size());
        imageMat = rotatedImageMat;

        Mat avgMat = imageMat.submat((int) averagePoint1.y, (int) averagePoint2.y,
                (int)averagePoint1.x, (int) averagePoint2.x);

        Scalar avg = Core.mean(avgMat);
        red = (int) avg.val[0];
        green = (int) avg.val[1];
        blue = (int) avg.val[2];

        Imgproc.rectangle(imageMat, averagePoint1,averagePoint2, new Scalar(255,0,0));
        Imgproc.putText(imageMat, String.format("%d %d %d block: %b", red,green,blue,isCube()), textPoint, Core.FONT_HERSHEY_PLAIN, 2.0, new Scalar(255,215,0));
        return imageMat;
    }

    @Override
    public void init() {
        mLoaderCallback = new BaseLoaderCallback(this.hardwareMap.appContext) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                        telemetry.addData("OpenCV", "Success");
                        imageMat = new Mat();
                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                    } break;
                }
            }
        };

        final int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("OpenCvView", "id", hardwareMap.appContext.getPackageName());
        final Activity activity = (Activity) hardwareMap.appContext;
        final CameraBridgeViewBase.CvCameraViewListener2 cameraViewListener2 = this;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOpenCvCameraView = (CameraBridgeViewBase) activity.findViewById(cameraMonitorViewId);
                mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
                mOpenCvCameraView.setCvCameraViewListener(cameraViewListener2);
            }
        });

    }


    @Override
    public void start()
    {
        super.start();

        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, hardwareMap.appContext, mLoaderCallback);
        } else {
            Log.d("OpenCV", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
            mOpenCvCameraView.enableView();
        }
    }

    @Override
    public void loop() {
        if (blue != -1) {
            if (blue > YELLOW_BLUE_THRESHOLD) {
                telemetry.addLine("Probably a sphere");
            }else {
                telemetry.addLine("Probably a block");
            }
        } else {
            telemetry.addLine("idk lol");
        }
    }

    @Override
    public void stop() {
        final int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("OpenCvView", "id", hardwareMap.appContext.getPackageName());
        final Activity activity = (Activity) hardwareMap.appContext;
        final CameraBridgeViewBase.CvCameraViewListener2 cameraViewListener2 = this;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOpenCvCameraView = (CameraBridgeViewBase) activity.findViewById(cameraMonitorViewId);
                mOpenCvCameraView.setVisibility(SurfaceView.INVISIBLE);
                mOpenCvCameraView.disableView();
            }
        });

    }

    boolean isCube() {
        if (blue != -1) {
            if (blue > YELLOW_BLUE_THRESHOLD) {
                telemetry.addLine("Probably a sphere");
                return false;
            }else {
                telemetry.addLine("Probably a block");
                return true;
            }
        } else {
            telemetry.addLine("idk lol");
            return false;
        }
    }

}
*/