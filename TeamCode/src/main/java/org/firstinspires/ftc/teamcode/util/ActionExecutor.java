package org.firstinspires.ftc.teamcode.util;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.action.Action;

/**
 * Created by djfigs1 on 11/18/16.
 */

public class ActionExecutor extends RobotHardware {

    public ActionSequence actionSequence;
    public boolean initVuforia = false;
    private Action action = null;

    @Override
    public void init() {
        super.init();
        if (initVuforia) {
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            parameters.vuforiaLicenseKey = this.vulforiaKey;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

            // Get Relics
            this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
            this.relicTemplate = this.relicTrackables.get(0);
            this.relicTemplate.setName("relicVuMarkTemplate");
        }
    }

    @Override
    public void start() {
        super.start();
        relicTrackables.activate();
    }

    int actionNumber = 1;

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();
        if (action != null) {
            if (action.doAction(this)) {
                actionSequence.currentActionComplete();
                action = actionSequence.getCurrentAction();
                actionNumber++;
            } else {
                telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, actionSequence.numberOfActions(),
                        (int)((double) actionNumber / (double) actionSequence.numberOfActions() * 100.0));
                telemetry.addData("Current Action", action.getClass().getSimpleName());
            }
        }
        else {
            requestOpModeStop();
        }


    }
}

