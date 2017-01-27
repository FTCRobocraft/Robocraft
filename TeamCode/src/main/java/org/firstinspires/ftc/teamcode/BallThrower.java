package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by lvern on 1/25/2017.
 */

public class BallThrower extends RobotHardware {

    public void waitFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override public void loop() {

        if(gamepad1.x) {
            shooterMotor.setPower(1);
            waitFor(500);
            shooterMotor.setPower(0);
        }
    }
}