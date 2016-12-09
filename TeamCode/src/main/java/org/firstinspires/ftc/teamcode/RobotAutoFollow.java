package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.robot.Robot;

/**
 * Created by djfigs1 on 10/14/16.
 */

@Autonomous(name="Beacon Follow")
public class RobotAutoFollow extends RobotHardware {

    enum AUTO_STATE {
        FIND_BLUE_LINE,
        FOLLOW_BLUE_LINE,
        PREP_BEACON,
        WAIT_FOR_SERVO,
        PUSH_BEACON,
        BACK_UP,
        TURN_RIGHT,
        TURN_LEFT,
        END
    }

    boolean firstLaunch = true; //A variable used for initializing variables in different areas of the crApMode.
    boolean pressed;
    long startTime;
    long endTime;
    int prevPos;
    int turnedPos;
    int WAIT_TIME = 3;
    long PUSH_TIME = 750;
    long BACKUP_TIME = 5500;
    double SNAIL_SPEED = 0.03;
    double APPROACH_SPEED = 0.08;
    double FOLLOW_SPEED = 0.06;
    double QUICK_SPEED = 0.12;
    double TURN_SPEED = 0.10;
    double VEER_SPEED = 0.05;
    int COLOR_THRESHOLD = 5;
    double OPT_DISTANCE = 3;
    int SONIC_DISTANCE = 7;
    int HEADING_TURN = -50;
    int HEADING_RANGE = 2;
    int HEADING_DIRECTION;
    VV_LINE_COLOR LINE_COLOR = VV_LINE_COLOR.WHITE;
    boolean second_time = false;

    AUTO_STATE current_state;

    //false -- red
    //true -- blue
    boolean BLUE_TEAM = true;

    @Override
    public void init() {
        super.init();
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        beaconPosition(1);
        current_state = AUTO_STATE.FIND_BLUE_LINE;
    }

    @Override
    public void init_loop() {
        beaconPosition(1);
    }
    @Override
    public void loop() {

        telemetry.addData("State", current_state.toString());
        telemetry.addData("Servo Pos", getBeaconPosition());
        telemetry.addData("Heading", gyroSensor.getHeading());
        telemetry.addData("TurnedPos", turnedPos);

        switch (current_state) {
            case FIND_BLUE_LINE:
                beaconPosition(1);
                if (firstLaunch) {
                    HEADING_DIRECTION = gyroSensor.getHeading();
                    firstLaunch = false;
                }

                if (gyroSensor.getHeading() >= HEADING_DIRECTION + HEADING_RANGE){
                    //Veering Right
                    set_drive_power(APPROACH_SPEED - VEER_SPEED, APPROACH_SPEED);
                }else if (gyroSensor.getHeading() <= HEADING_DIRECTION - HEADING_RANGE){
                    //Veering Left
                    set_drive_power(APPROACH_SPEED, APPROACH_SPEED - VEER_SPEED);
                }else{
                    set_drive_power(APPROACH_SPEED, APPROACH_SPEED);
                }

                if (getLineFollowState(LINE_COLOR, COLOR_THRESHOLD) != ROBOT_LINE_FOLLOW_STATE.NONE){
                    if (second_time){
                        current_state = AUTO_STATE.TURN_LEFT;
                    }else{
                        current_state = AUTO_STATE.FOLLOW_BLUE_LINE;
                    }
                    firstLaunch = true;
                }
                break;

            case FOLLOW_BLUE_LINE:
                if (rangeSensor.cmUltrasonic() >= SONIC_DISTANCE){
                    switch (getLineFollowState(LINE_COLOR, COLOR_THRESHOLD)){
                        case LEFT:
                            set_drive_power(QUICK_SPEED, 0);
                            break;
                        case RIGHT:
                            set_drive_power(0, QUICK_SPEED);
                            break;
                        case BOTH:
                            set_drive_power(FOLLOW_SPEED, FOLLOW_SPEED);
                            break;
                    }
                }else{
                    if (rangeSensor.cmOptical() >= OPT_DISTANCE){
                        set_drive_power(SNAIL_SPEED, SNAIL_SPEED);
                    }else {
                        current_state = AUTO_STATE.PREP_BEACON;
                    }
                }
                break;

            case PREP_BEACON:
                if (getBeaconColor() == VV_BEACON_COLOR.BLUE) {
                    if (!BLUE_TEAM){
                        //red team
                        prepareForBeacon(true);
                        current_state = AUTO_STATE.WAIT_FOR_SERVO;
                    }else{
                        //blue team
                        current_state = AUTO_STATE.PUSH_BEACON;
                    }
                }else {
                    if (!BLUE_TEAM){
                        //red team
                        current_state = AUTO_STATE.PUSH_BEACON;
                    }else{
                        //blue team
                        prepareForBeacon(true);
                        current_state = AUTO_STATE.WAIT_FOR_SERVO;
                    }
                }
                break;

            case WAIT_FOR_SERVO:
                if (firstLaunch){
                    stopdrive();
                    //Set times.
                    startTime = System.currentTimeMillis();
                    endTime = startTime + (WAIT_TIME * 1000);
                    firstLaunch = false;
                }

                prepareForBeacon(true);
                if (System.currentTimeMillis() >= endTime){
                    current_state = AUTO_STATE.PUSH_BEACON;
                    firstLaunch = true;
                }
                break;

            case PUSH_BEACON:
                if (firstLaunch){
                    //Set times.
                    startTime = System.currentTimeMillis();
                    endTime = startTime + PUSH_TIME;
                    firstLaunch = false;
                }

                if (second_time) {
                    current_state = AUTO_STATE.END;
                }

                if (pressed){
                    //Back up for roughly 1/4 a second.
                    if (System.currentTimeMillis() >= endTime){
                        stopdrive();
                        pressed = false;
                        firstLaunch = true;
                        current_state = AUTO_STATE.TURN_RIGHT;
                        second_time = true;
                    }else{
                        set_drive_power(-APPROACH_SPEED, -APPROACH_SPEED);
                    }
                }else{
                    //Go forward for roughly 1/4 a second.
                    if (System.currentTimeMillis() >= endTime){
                        stopdrive();
                        pressed = true;
                        firstLaunch = true;
                    }else{
                        set_drive_power(APPROACH_SPEED, APPROACH_SPEED);
                    }
                }
                break;

            case BACK_UP:
                if (second_time){
                    current_state = AUTO_STATE.END;
                }else{
                    if (firstLaunch){
                        //Set times.
                        startTime = System.currentTimeMillis();
                        endTime = startTime + BACKUP_TIME;
                        firstLaunch = false;
                        HEADING_DIRECTION = gyroSensor.getHeading();
                    }

                    if (System.currentTimeMillis() >= endTime){
                        current_state = AUTO_STATE.TURN_RIGHT;
                        HEADING_DIRECTION = 0;
                        firstLaunch = true;
                    }else{
                        if (gyroSensor.getHeading() >= HEADING_DIRECTION + HEADING_RANGE){
                            //Veering Right
                            set_drive_power(-APPROACH_SPEED, -APPROACH_SPEED + VEER_SPEED);
                        }else if (gyroSensor.getHeading() <= HEADING_DIRECTION - HEADING_RANGE){
                            //Veering Left
                            set_drive_power(-APPROACH_SPEED + VEER_SPEED, -APPROACH_SPEED);
                        }else{
                            set_drive_power(-APPROACH_SPEED, -APPROACH_SPEED);
                        }
                    }
                }
                break;

            case TURN_LEFT:
                int heading2 = gyroSensor.getHeading();
                if (firstLaunch){
                    firstLaunch = false;
                    turnedPos = 0;
                    prevPos = heading2;
                }
                turnedPos += Math.abs(prevPos - heading2);
                if (heading2 > prevPos){
                    turnedPos += 359;
                }

                prevPos = heading2;

                if (turnedPos >= 80){
                    HEADING_DIRECTION = heading2;
                    current_state = AUTO_STATE.FOLLOW_BLUE_LINE;
                    firstLaunch = true;
                }else{
                    set_drive_power(-TURN_SPEED, TURN_SPEED);
                }
                break;

            case TURN_RIGHT:
                int heading = gyroSensor.getHeading();
                if (firstLaunch){
                    firstLaunch = false;
                    turnedPos = 0;
                    prevPos = heading;
                }
                if (prevPos + heading >= 359 && heading < prevPos){
                    prevPos -= 359;
                }

                turnedPos += Math.abs(prevPos - heading);
                prevPos = heading;

                if (turnedPos >= 80){
                    HEADING_DIRECTION = heading;
                    second_time = true;
                    current_state = AUTO_STATE.FIND_BLUE_LINE;
                    firstLaunch = true;
                }else{
                    set_drive_power(TURN_SPEED, -TURN_SPEED);
                }

            break;

            case END:
                requestOpModeStop();
                break;
        }
    }

}
