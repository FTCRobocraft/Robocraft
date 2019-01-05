package org.firstinspires.ftc.teamcode.action;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

public class DumperServoAction implements Action {

    double position;
    double timeout;
    ElapsedTime elapsedTime;

    public DumperServoAction(double position, double timeout) {
        this.position = position;
        this.timeout = timeout;
    }

    /**
     * Function called when the action is first executed
     *
     * @param hardware
     */
    @Override
    public void init(BaseHardware hardware) {
        elapsedTime = new ElapsedTime();
        elapsedTime.reset();
    }

    /**
     * Function that is called for every iteration of the OpMode loop
     *
     * @param hardware
     * @return Return true when the action is complete.
     */
    @Override
    public boolean doAction(BaseHardware hardware) {
        if (hardware instanceof RoverRuckusHardware) {
            ((RoverRuckusHardware) hardware).dumperLeftServo.setPosition(position);
            ((RoverRuckusHardware) hardware).dumperRightServo.setPosition(position);
        }
        return elapsedTime.milliseconds() > timeout;
    }
}
