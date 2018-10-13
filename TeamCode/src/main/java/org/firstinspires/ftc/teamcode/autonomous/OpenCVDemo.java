package org.firstinspires.ftc.teamcode.autonomous;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.RobotHardware;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.osgi.OpenCVInterface;


@Autonomous(name="OpenCV Demo")
public class OpenCVDemo extends RobotHardware{

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this.hardwareMap.appContext) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                    telemetry.addLine("OpenCV loaded successfully");
                    //mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    public void start()
    {
        super.start();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_6, this.hardwareMap.appContext, mLoaderCallback);
    }

}
