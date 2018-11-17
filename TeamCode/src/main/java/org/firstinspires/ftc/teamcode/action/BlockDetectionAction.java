package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;

public class BlockDetectionAction implements Action {

    public RoverRuckusHardware.GOLD_MINERAL_POSITION position = RoverRuckusHardware.GOLD_MINERAL_POSITION.UNKNOWN;
    double timeout;
    double endTime;

    public BlockDetectionAction(double timeout) {
        this.timeout = timeout;
    }

    @Override
    public void init(BaseHardware hardware) {
        endTime = System.currentTimeMillis() + timeout;
    }

    @Override
    public boolean doAction(BaseHardware hardware) {
        // Get all new recognitions
        List<Recognition> updatedRecognitions = hardware.tfod.getUpdatedRecognitions();

        // Check if anything new is detected
        if (updatedRecognitions != null) {
            hardware.telemetry.addData("# Object Detected", updatedRecognitions.size());

            // Check if all three objects are detected
            if (updatedRecognitions.size() == 3) {
                int goldMineralX = -1;
                int silverMineral1X = -1;
                int silverMineral2X = -1;

                // Get X coordinate of each recognition
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        goldMineralX = (int) recognition.getLeft();
                    } else if (silverMineral1X == -1) {
                        silverMineral1X = (int) recognition.getLeft();
                    } else {
                        silverMineral2X = (int) recognition.getLeft();
                    }
                }

                // Determine position of gold mineral
                if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                    if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                        position = RoverRuckusHardware.GOLD_MINERAL_POSITION.LEFT;
                        hardware.telemetry.addData("Gold Mineral Position", "Left");
                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                        position = RoverRuckusHardware.GOLD_MINERAL_POSITION.RIGHT;
                        hardware.telemetry.addData("Gold Mineral Position", "Right");
                    } else {
                        position = RoverRuckusHardware.GOLD_MINERAL_POSITION.CENTER;
                        hardware.telemetry.addData("Gold Mineral Position", "Center");
                    }
                    return true;
                }
            }
        }

        return System.currentTimeMillis() > endTime;
    }

}
