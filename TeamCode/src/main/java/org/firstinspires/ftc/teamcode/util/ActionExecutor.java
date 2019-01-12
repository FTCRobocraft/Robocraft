package org.firstinspires.ftc.teamcode.util;

import android.hardware.camera2.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;

/**
 * Created by djfigs1 on 11/18/16.
 */

public class ActionExecutor extends RoverRuckusHardware {

    public ActionSequence actionSequence;
    public boolean initVuforia = false;
    public boolean initTFOD = false;
    public boolean enableFlashlight = true;
    private Action action = null;

    @Override
    public void init() {
        super.init();

        if (initVuforia) {
            VuforiaLocalizer.Parameters parameters;
            if (initTFOD) {
                parameters = new VuforiaLocalizer.Parameters();
            } else {
                int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
                parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
            }

            parameters.vuforiaLicenseKey = this.vulforiaKey;
            parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
            this.vuforia = ClassFactory.getInstance().createVuforia(parameters);
            com.vuforia.CameraDevice.getInstance().setFlashTorchMode(enableFlashlight);
        }

        if (initTFOD) {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        }

    }

    @Override
    public void start() {
        super.start();
        if (initTFOD) {
            tfod.activate();
        }

    }

    @Override
    public void stop() {

        if (initVuforia) {
            com.vuforia.CameraDevice.getInstance().setFlashTorchMode(false);
        }
        if (initTFOD) {
            tfod.deactivate();
        }
    }

    int actionNumber = 1;
    boolean didInit = false;

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();

        if (action != null) {
            if (!didInit) {
                action.init(this);
                didInit = true;
            }

            if (action.doAction(this)) {
                actionSequence.currentActionComplete();
                action = actionSequence.getCurrentAction();
                actionNumber++;
                didInit = false;
            } else {
                telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, actionSequence.numberOfActions(),
                        (int) ((double) actionNumber / (double) actionSequence.numberOfActions() * 100.0));
                telemetry.addData("Current Action", action.getClass().getSimpleName());
            }
        } else {
            requestOpModeStop();
        }
    }
}

