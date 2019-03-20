package org.firstinspires.ftc.teamcode.action;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

public class LandAction implements Action {

    public static final double DROP_TIME = 14500;
    ElapsedTime elapsedTime;

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
        //TODO: spin motor
        if (hardware instanceof RoverRuckusHardware) {
            ((RoverRuckusHardware) hardware).liftHexMotor.setPower(1);
        }

        if (elapsedTime.milliseconds() >= DROP_TIME) {
            ((RoverRuckusHardware) hardware).liftHexMotor.setPower(0);
            return true;
        }

        return false;
    }
}
