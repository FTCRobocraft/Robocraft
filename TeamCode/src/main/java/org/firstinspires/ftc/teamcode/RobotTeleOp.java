package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp", group="Manual")
public class RobotTeleOp extends RobotTelemetry {

    private boolean manual = true;
    private boolean servoStartPosition = true;

    enum AUTO_STATE {
        FIND_LINE,
        FOLLOW_LINE,
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
    long startTime;
    long endTime;
    double SLOW_SPEED = 0.07;
    double QUICK_SPEED = 0.10;
    int COLOR_THRESHOLD = 5;
    int BEACON_DISTANCE = 8;
    VV_LINE_COLOR lineColor = VV_LINE_COLOR.BLUE;

    float SPEED_SCALE = 5F;


    @Override public void init() {
        super.init();
        beaconPosition(1);
        servoStartPosition = true;
    }

    @Override
    public void loop() {
        //region Old
        //
        // GAMEPAD 1
        // Manage the drive wheel motors.
        //
        //Manage the arm servos.
        //GAMEPAD 2
        //arm_1_position(get_arm_1_position() + gamepad2.left_stick_ y);
        //arm_2_position(get_arm_2_position() + gamepad2.right_stick_y);


        //if (gamepad2.x){ Nah.
        //    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        //    toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
        //
        //    arm_home_position();
        //}

        /*Raw Controls
        double deadzone = .05;
        if (Math.abs(gamepad2.left_stick_y - prev_left_y) > deadzone) {
            arm_1_position(-gamepad2.left_stick_y);
            prev_left_y = gamepad2.left_stick_y;
        }
        if (Math.abs(gamepad2.right_stick_y - prev_right_y) > deadzone) {
            arm_2_position(-gamepad2.right_stick_y);
            prev_right_y = gamepad2.right_stick_y;
        }
        */
        //endregion

        //Telemetry Updates
        telemetry.addData("Manual", manual);
        telemetry.addData("State", current_state.toString());
        telemetry.addData("BServo", getBeaconPosition());


        //ABXY Buttons
        if (gamepad1.x){
            //Pushes the blue side of a beacon
            if (!xPress){

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

        if (gamepad1.b){
            //Pushes the red side of the button.
            if (!bPress){
                team = TEAM.RED;
                pressed = false;
                firstLaunch = true;
                current_state = AUTO_STATE.PREP_BEACON;
                manual = !manual;
                bPress = true;
            }
        }else{
            bPress = false;
        }


        if (gamepad1.y){
            //Automatically follows the line.
            if (!yPress){
                beaconPosition(1);
                current_state = AUTO_STATE.FIND_LINE;
                manual = !manual;
                yPress = true;
            }
        } else {
            yPress = false;
        }

        if (gamepad1.a){
            //Switches the servo sides.
            if (!aPress){
                if (manual){
                    if (servoStartPosition) {
                        beaconPosition(0);
                    }
                    else {
                        beaconPosition(1);
                    }
                    servoStartPosition = !servoStartPosition;
                }
                aPress = true;
            }
        } else {
            aPress = false;
        }

        if (manual){
            float l_left_drive_power = scale_motor_power(-gamepad1.left_stick_y) / SPEED_SCALE;
            float l_right_drive_power = scale_motor_power(-gamepad1.right_stick_y) / SPEED_SCALE;

            if (gamepad1.dpad_up || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_down){
                DPADPower();
            }else{
                set_drive_power(l_left_drive_power, l_right_drive_power);
            }

            if (gamepad2.left_bumper){
                beaconPosition(1);
            }
            if (gamepad2.right_bumper){
                beaconPosition(0);
            }

        }
        else
        {
            switch (current_state) {
                case FIND_LINE:
                    //Move until we find a white line
                    set_drive_power(SLOW_SPEED, SLOW_SPEED);

                    if (getLineFollowState(lineColor, COLOR_THRESHOLD) != ROBOT_LINE_FOLLOW_STATE.NONE){
                        current_state = AUTO_STATE.FOLLOW_LINE;
                    }
                    break;

                case FOLLOW_LINE:
                    //Follow the line and stay center on it until we get within the distance we need to press the beacon.
                    if (rangeSensor.cmUltrasonic() >= BEACON_DISTANCE){
                        switch (getLineFollowState(lineColor, COLOR_THRESHOLD)){
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
                    }else{
                        current_state = AUTO_STATE.END;
                    }
                    break;

                //---------------------------

                case PREP_BEACON:
                    if ((getBeaconColor() == VV_BEACON_COLOR.BLUE && team == TEAM.RED) || (getBeaconColor() == VV_BEACON_COLOR.RED && team == TEAM.BLUE)) {
                        beaconPosition(0);
                        current_state = AUTO_STATE.WAIT_FOR_SERVO;
                    }else{
                        current_state = AUTO_STATE.PUSH_BEACON;
                    }
                    break;

                case WAIT_FOR_SERVO:
                    if (firstLaunch){
                        //Set times.
                        startTime = System.currentTimeMillis();
                        endTime = startTime + 2500;
                        firstLaunch = false;
                    }

                    if (System.currentTimeMillis() >= endTime){
                        current_state = AUTO_STATE.PUSH_BEACON;
                        firstLaunch = true;
                    }
                    break;

                case PUSH_BEACON:
                    if (firstLaunch){
                        //Set times.
                        startTime = System.currentTimeMillis();
                        endTime = startTime + 750;
                        firstLaunch = false;
                    }

                    if (pressed){
                        //Back up for roughly 1/4 a second.
                        if (System.currentTimeMillis() >= endTime){
                            stopdrive();
                            pressed = false;
                            firstLaunch = true;
                            current_state = AUTO_STATE.END;
                        }else{
                            set_drive_power(-SLOW_SPEED, -SLOW_SPEED);
                        }
                    }else{
                        //Go forward for roughly 1/4 a second.
                        if (System.currentTimeMillis() >= endTime){
                            stopdrive();
                            pressed = true;
                            firstLaunch = true;
                        }else{
                            set_drive_power(SLOW_SPEED, SLOW_SPEED);
                        }
                    }
                    break;

                case END:
                    manual = true;
                    break;
            }
        }
    }

    void DPADPower() {
        if (gamepad1.dpad_up){
            set_drive_power(0.1, 0.1);
        }
        if (gamepad1.dpad_left){
            set_drive_power(-0.1, 0.1);
        }
        if (gamepad1.dpad_right){
            set_drive_power(0.1, -0.1);
        }
        if (gamepad1.dpad_down){
            set_drive_power(-0.1, -0.1);
        }
    }
}
