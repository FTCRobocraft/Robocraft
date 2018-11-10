package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.opencv.core.Mat;

public interface CVAction extends Action {
    Mat processMat(RoverRuckusHardware hardware, Mat imageMat);
}
