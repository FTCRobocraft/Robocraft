package org.firstinspires.ftc.teamcode.util;

import android.app.Activity;
import android.util.Log;
import android.view.SurfaceView;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.CVAction;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by djfigs1 on 11/18/16.
 */

public class ActionExecutor extends RoverRuckusHardware implements CameraBridgeViewBase.CvCameraViewListener2 {

    public ActionSequence actionSequence;
    public boolean initVuforia = false;
    public boolean initOpenCV = false;
    private Action action = null;

    @Override
    public void init() {
        super.init();
        if (initVuforia) {
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            parameters.vuforiaLicenseKey = this.vulforiaKey;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

            // Get Relics
            this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
            this.relicTemplate = this.relicTrackables.get(0);
            this.relicTemplate.setName("relicVuMarkTemplate");
        }

        if (initOpenCV) {
            this.mLoaderCallback = new BaseLoaderCallback(this.hardwareMap.appContext) {
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
    }

    @Override
    public void start() {
        super.start();
        if (initVuforia) {
            relicTrackables.activate();
        }

        if (initOpenCV) {
            if (!OpenCVLoader.initDebug()) {
                Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
                OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, hardwareMap.appContext, mLoaderCallback);
            } else {
                Log.d("OpenCV", "OpenCV library found inside package. Using it!");
                mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
                mOpenCvCameraView.enableView();
            }
        }
    }

    int actionNumber = 1;

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();
        if (action != null) {
            if (action.doAction(this)) {
                actionSequence.currentActionComplete();
                action = actionSequence.getCurrentAction();
                actionNumber++;
            } else {
                telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, actionSequence.numberOfActions(),
                        (int)((double) actionNumber / (double) actionSequence.numberOfActions() * 100.0));
                telemetry.addData("Current Action", action.getClass().getSimpleName());
            }
        }
        else {
            requestOpModeStop();
        }


    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        this.imageMat = inputFrame.rgba();
        Mat rotatedImageMat = new Mat();
        Core.rotate(this.imageMat, rotatedImageMat, Core.ROTATE_90_CLOCKWISE);
        Imgproc.resize(rotatedImageMat, rotatedImageMat, this.imageMat.size());

        if (action instanceof CVAction) {
            this.imageMat = ((CVAction) action).processMat(this, this.imageMat);
        }
        return this.imageMat;
    }
}

