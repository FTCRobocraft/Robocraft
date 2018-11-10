package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CameraBlockDetectionAction implements CVAction {

    private final int YELLOW_BLUE_THRESHOLD = 40;
    private int focusSize = 50;
    private Point averagePoint1 = new Point(640/2 - focusSize/2,480/2 - focusSize/2);
    private Point averagePoint2 = new Point(640/2 + focusSize/2,480/2 + focusSize/2);
    private Point textPoint = new Point(10, 450);
    int red = -1;
    int green = -1;
    int blue = -1;
    boolean processed = false;

    public enum DETECTED_OBJECT {
        CUBE,
        SPHERE,
        UNKNOWN
    }

    public DETECTED_OBJECT object = DETECTED_OBJECT.UNKNOWN;

    @Override
    public boolean doAction(RoverRuckusHardware hardware) {
        if (processed) {
            this.object = this.isCube(hardware) ? DETECTED_OBJECT.CUBE : DETECTED_OBJECT.SPHERE;
        }

        return this.object == DETECTED_OBJECT.SPHERE;
    }

    @Override
    public Mat processMat(RoverRuckusHardware hardware, Mat imageMat) {
        Mat avgMat = hardware.imageMat.submat((int) averagePoint1.y, (int) averagePoint2.y,
                (int)averagePoint1.x, (int) averagePoint2.x);

        Scalar avg = Core.mean(avgMat);
        this.red = (int) avg.val[0];
        this.green = (int) avg.val[1];
        this.blue = (int) avg.val[2];

        Imgproc.rectangle(imageMat, averagePoint1, averagePoint2, new Scalar(255,0,0));
        Imgproc.putText(imageMat, String.format("%d %d %d block: %b", red,green,blue,isCube(hardware)), textPoint, Core.FONT_HERSHEY_PLAIN, 2.0, new Scalar(255,215,0));
        processed = true;
        return imageMat;
    }

    boolean isCube(RoverRuckusHardware hardware) {
        if (blue != -1) {
            if (blue > YELLOW_BLUE_THRESHOLD) {
                hardware.telemetry.addLine("Probably a sphere");
                return false;
            }else {
                hardware.telemetry.addLine("Probably a block");
                return true;
            }
        } else {
            hardware.telemetry.addLine("idk lol");
            return false;
        }
    }

}
