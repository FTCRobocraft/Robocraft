package org.firstinspires.ftc.teamcode.teleop;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Not really created by djfigs1 on 9/23/17. Created when Luke wanted his gyro code tested. lol XD
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="RevMotorTest")
public class Manual extends RobotHardware {

    final float dpad_speed =  1f;

    final double leftServoOpen = 0;
    final double rightServoOpen = 0;
    final double leftServoClosed = 0;
    final double rightServoClosed = 0;


    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.init();
    }

    @Override
    public void loop() {
        stopDrive();

        //region Gamepad 1


        //region Gamepad 2
        /*
        This code maps the Gamepad 2's buttons A and B to open and close the grabber.
         */
        if (gamepad2.a) {
            lift_leftServo.setPosition(leftServoClosed);
            lift_rightServo.setPosition(rightServoClosed);
        } else if (gamepad2.b) {
            lift_leftServo.setPosition(leftServoOpen);
            lift_rightServo.setPosition(rightServoOpen);
        }

        /*
        Constantly set the power of the vertical continuous servo to whatever input is being detected
        by the gamepad.
        */
        //lift_verticalServo.setPower(gamepad2.left_stick_y);

        clawServo.setPosition(clawServo.getPosition() + gamepad2.left_stick_y / 10);
        //endregion
    }

}
