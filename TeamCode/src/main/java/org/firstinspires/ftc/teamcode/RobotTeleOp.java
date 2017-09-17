package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp", group="Manual")
public class RobotTeleOp extends RobotTelemetry {

    //region Variables
    private boolean manual = true;
    private boolean servoStartPosition = true;

    enum AUTO_STATE {
        FIND_LINE,
        FOLLOW_LINE,
        ALIGN_BEACONS,
        PREP_BEACON,
        WAIT_FOR_SERVO,
        PUSH_BEACON,
        END
    }

    AUTO_STATE current_state = AUTO_STATE.FIND_LINE;

    TEAM team;
    boolean firstLaunch = false; //A variable used for initializing variables in different areas of the OpMode.
    boolean pressed;
    boolean xPress;
    boolean yPress;
    boolean bPress;
    boolean aPress;
    boolean x2Press;
    boolean shoot;
    long shooterTime;
    long startTime;
    long endTime;
    double SLOW_SPEED = 0.07;
    double QUICK_SPEED = 0.14;
    int COLOR_THRESHOLD = 5;
    int BEACON_DISTANCE = 18;
    double alignRange = 0.05;
    VV_LINE_COLOR lineColor = VV_LINE_COLOR.BLUE;

    float SPEED_SCALE = 4F;
    //endregion

    @Override
    public void init() {
        super.init();
        beaconPosition(1);
        servoStartPosition = true;
    }

    @Override
    public void loop() {
        //region Telemetry Updates
        telemetry.addData("Manual", manual);
        telemetry.addData("State", current_state.toString());
        //endregion

        //region Gamepad 1
        if (gamepad1.x) {
            //Pushes the blue side of a beacon
            if (!xPress) {
                xPress = true;
                team = TEAM.BLUE;
                pressed = false;
                firstLaunch = true;
                current_state = AUTO_STATE.PREP_BEACON;
                manual = !manual;
            }
        } else {
            xPress = false;
        }

        if (gamepad1.b) {
            //Pushes the red side of the button.
            if (!bPress) {
                team = TEAM.RED;
                pressed = false;
                firstLaunch = true;
                current_state = AUTO_STATE.PREP_BEACON;
                manual = !manual;
                bPress = true;
            }
        } else {
            bPress = false;
        }


        if (gamepad1.a) {
            //Automatically follows the line.
            if (!aPress) {
                beaconPosition(1);
                current_state = AUTO_STATE.FIND_LINE;
                manual = !manual;
                aPress = true;
            }
        } else {
            aPress = false;
        }

        if (gamepad1.y) {
            //Switches the servo sides.
            if (!yPress) {
                if (manual) {
                    if (servoStartPosition) {
                        beaconPosition(0);
                    } else {
                        beaconPosition(1);
                    }
                    servoStartPosition = !servoStartPosition;
                }
                yPress = true;
            }
        } else {
            yPress = false;
        }

        if (gamepad1.right_bumper){
            if (!x2Press) {
                shooterTime = System.currentTimeMillis() + 500;
                shoot = !shoot;
                x2Press = true;
            }
        }else{
            x2Press = false;
        }
        //endregion

        if (manual) {
            //region Manual-Code
            float l_left_drive_power = scale_motor_power(-gamepad1.left_stick_y) / SPEED_SCALE;
            float l_right_drive_power = scale_motor_power(-gamepad1.right_stick_y) / SPEED_SCALE;

            //If the DPAD is being pressed, activate the DPAD function to respond to the inputs.
            //Else, set the drive power based on Gamepad 1's analog sticks.
            if (gamepad1.dpad_up || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_down) {
                DPADPower();
            } else {
                set_drive_power(l_left_drive_power, l_right_drive_power);
            }

            //Activated when Gamepad 2 presses x. It shoots a ball by activating
            //the shooter motor for 500 milliseconds.
            if (shoot) {
                if (System.currentTimeMillis() >= shooterTime) {
                    shooterMotor.setPower(0);
                    shoot = false;
                }else{
                    shooterMotor.setPower(1);
                }
            }
            //endregion

        } else {
            //region Auto-Code
            switch (current_state) {
                case FIND_LINE:
                    //Move until we find a white line
                    set_drive_power(SLOW_SPEED, SLOW_SPEED);

                    if (getLineFollowState(lineColor, COLOR_THRESHOLD) != ROBOT_LINE_FOLLOW_STATE.NONE) {
                        current_state = AUTO_STATE.FOLLOW_LINE;
                    }
                    break;

                case FOLLOW_LINE:
                    //Follow the line and stay center on it until we get within the distance we need to press the beacon.
                    if (leftRangeSensor.cmUltrasonic() >= BEACON_DISTANCE) {
                        switch (getLineFollowState(lineColor, COLOR_THRESHOLD)) {
                            case LEFT:
                                set_drive_power(QUICK_SPEED, 0);
                                break;
                            case RIGHT:
                                set_drive_power(0, QUICK_SPEED);
                                break;
                            case BOTH:
                                set_drive_power(SLOW_SPEED, SLOW_SPEED);
                                break;
                        }
                    } else {
                        current_state = AUTO_STATE.END;
                    }
                    break;

                //---------------------------

                case ALIGN_BEACONS:
                    double leftCm = leftRangeSensor.cmUltrasonic();
                    double rightCm = getRightCm();

                    if (leftCm <= rightCm + alignRange && rightCm <= leftCm + alignRange){
                        current_state = AUTO_STATE.PREP_BEACON;
                    }
                    else {
                        if (leftCm >= rightCm + alignRange) {
                            set_drive_power(0.09, -0.09);
                        } else {
                            if (rightCm >= leftCm + alignRange) {
                                set_drive_power(-0.09, 0.09);
                            }
                        }
                    }
                    break;

                case PREP_BEACON:
                    if ((getBeaconColor() == VV_BEACON_COLOR.BLUE && team == TEAM.RED) || (getBeaconColor() == VV_BEACON_COLOR.RED && team == TEAM.BLUE)) {
                        beaconPosition(0);
                        current_state = AUTO_STATE.WAIT_FOR_SERVO;
                    } else {
                        current_state = AUTO_STATE.PUSH_BEACON;
                    }
                    break;

                case WAIT_FOR_SERVO:
                    if (firstLaunch) {
                        //Set times.
                        startTime = System.currentTimeMillis();
                        endTime = startTime + 1000;
                        firstLaunch = false;
                    }

                    if (System.currentTimeMillis() >= endTime) {
                        current_state = AUTO_STATE.PUSH_BEACON;
                        firstLaunch = true;
                    }
                    break;

                case PUSH_BEACON:
                    if (firstLaunch) {
                        //Set times.
                        startTime = System.currentTimeMillis();
                        endTime = startTime + 750;
                        firstLaunch = false;
                    }

                    if (pressed) {
                        //Back up for roughly 1/4 a second.
                        if (System.currentTimeMillis() >= endTime) {
                            stopdrive();
                            pressed = false;
                            firstLaunch = true;
                            current_state = AUTO_STATE.END;
                        } else {
                            set_drive_power(-SLOW_SPEED, -SLOW_SPEED);
                        }
                    } else {
                        //Go forward for roughly 1/4 a second.
                        if (System.currentTimeMillis() >= endTime) {
                            stopdrive();
                            pressed = true;
                            firstLaunch = true;
                        } else {
                            set_drive_power(SLOW_SPEED, SLOW_SPEED);
                        }
                    }
                    break;

                case END:
                    manual = true;
                    break;
            }
            //endregion
        }
    }

    //region Functions
    void DPADPower() {
        if (gamepad1.dpad_up) {
            set_drive_power(0.1, 0.1);
        }
        if (gamepad1.dpad_left) {
            set_drive_power(-0.1, 0.1);
        }
        if (gamepad1.dpad_right) {
            set_drive_power(0.1, -0.1);
        }
        if (gamepad1.dpad_down) {
            set_drive_power(-0.1, -0.1);
        }
    }
    //endregion
}