package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class PushBeaconAction implements Action {

    enum beaconStates {
        PREP_BEACON,
        WAIT_FOR_SERVO,
        PUSH_BEACON
    }

    RobotHardware.TEAM team;
    double speed;
    boolean firstLaunch = true;
    boolean pressed = false;
    double startTime;
    double endTime;

    final double waitTime = 1500;
    final double pushTime = 1000;

    beaconStates current_state = beaconStates.PREP_BEACON;

    public PushBeaconAction(RobotHardware.TEAM team, double speed){
        this.team = team;
        this.speed = speed;
    }

    public boolean doAction(RobotHardware hardware){
        boolean finished = false;
        switch (current_state){
            case PREP_BEACON:
                if (hardware.getBeaconColor() == RobotHardware.VV_BEACON_COLOR.BLUE) {
                    if (team == RobotHardware.TEAM.RED){
                        //red team
                        hardware.beaconPosition(0);
                        current_state = beaconStates.WAIT_FOR_SERVO;
                    }else{
                        //blue team
                        current_state = beaconStates.PUSH_BEACON;
                    }
                }else {
                    if (team == RobotHardware.TEAM.RED){
                        //red team
                        current_state = beaconStates.PUSH_BEACON;
                    }else{
                        //blue team
                        hardware.beaconPosition(0);
                        current_state = beaconStates.WAIT_FOR_SERVO;
                    }
                }
                break;

            case WAIT_FOR_SERVO:
                if (firstLaunch){
                    hardware.stopdrive();
                    //Set times.
                    startTime = System.currentTimeMillis();
                    endTime = startTime + (waitTime);
                    firstLaunch = false;
                }

                hardware.beaconPosition(0);
                if (System.currentTimeMillis() >= endTime){
                    current_state = beaconStates.PUSH_BEACON;
                    firstLaunch = true;
                }
                break;

            case PUSH_BEACON:
                if (firstLaunch){
                    //Set times.
                    startTime = System.currentTimeMillis();
                    endTime = startTime + pushTime;
                    firstLaunch = false;
                }

                if (pressed){
                    //Back up for roughly 1/4 a second.
                    if (System.currentTimeMillis() >= endTime){
                        //Done
                        hardware.stopdrive();
                        finished = true;
                    }else{
                        hardware.set_drive_power(-this.speed, -this.speed);
                    }
                }else{
                    //Go forward for roughly 1/4 a second.
                    if (System.currentTimeMillis() >= endTime){
                        hardware.stopdrive();
                        pressed = true;
                        firstLaunch = true;
                    }else{
                        hardware.set_drive_power(this.speed, this.speed);
                        //i like monkeys
                        //Missing Hud
                    }
                }
                break;
        }
        return finished;
    }
}