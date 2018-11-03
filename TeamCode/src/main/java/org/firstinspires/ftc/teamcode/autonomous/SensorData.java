package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

/**
 * Created by djfigs1 on 10/21/17.
 */

public class SensorData extends RelicRecoveryHardware {

    @Override
    public void loop() {
        if (jewelSensor != null) {
            telemetry.addData("Jewel Sensor", "R: %s G: %s B: %s",
                    jewelSensor.red(),
                    jewelSensor.blue(),
                    jewelSensor.green());
        }

        if (revIMU != null) {
            telemetry.addData("REV Temperature", "%s", revIMU.getTemperature().temperature);

            telemetry.addData("REV Gyro", "X: %s Y: %s Z: %s",
                    revIMU.getAngularOrientation().firstAngle,
                    revIMU.getAngularOrientation().secondAngle,
                    revIMU.getAngularOrientation().thirdAngle);

            telemetry.addData("REV Velocity", "X: %s Y: %s Z: %s",
                    revIMU.getVelocity().xVeloc,
                    revIMU.getVelocity().yVeloc,
                    revIMU.getVelocity().zVeloc);

            telemetry.addData("REV AO", "X: %s Y: %s Z: %s",
                    revIMU.getAngularOrientation().firstAngle,
                    revIMU.getAngularOrientation().secondAngle,
                    revIMU.getAngularOrientation().thirdAngle);

            telemetry.addData("REV OA", "X: %s Y: %s Z: %s",
                    revIMU.getOverallAcceleration().xAccel,
                    revIMU.getOverallAcceleration().yAccel,
                    revIMU.getOverallAcceleration().zAccel);

            telemetry.addData("REV Accel", "X: %s Y: %s Z: %s",
                    revIMU.getAcceleration().xAccel,
                    revIMU.getAcceleration().yAccel,
                    revIMU.getAcceleration().zAccel);

            telemetry.addData("REV L-Accel", "X: %s Y: %s Z: %s",
                    revIMU.getLinearAcceleration().xAccel,
                    revIMU.getLinearAcceleration().yAccel,
                    revIMU.getLinearAcceleration().zAccel);

            telemetry.addData("REV Magnetic", "X: %s Y: %s Z: %s",
                    revIMU.getMagneticFieldStrength().x,
                    revIMU.getMagneticFieldStrength().y,
                    revIMU.getMagneticFieldStrength().z);

            telemetry.addData("REV Gravity", "X: %s Y: %s Z: %s",
                    revIMU.getGravity().xAccel,
                    revIMU.getGravity().yAccel,
                    revIMU.getGravity().zAccel);
        }
    }
}
