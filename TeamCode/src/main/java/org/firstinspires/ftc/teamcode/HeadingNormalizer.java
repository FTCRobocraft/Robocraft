package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/23/16.
 */
public class HeadingNormalizer {

    private double initialHeading = 0;

    public HeadingNormalizer(double initialHeading) {
        this.initialHeading = initialHeading;
    }

    private double normalize(double heading) {
        double result = heading;
        if (heading > 180) {
            result = heading - 360D;
        }
        return result;
    }

    public double normalizeHeading(double heading) {
        double result = normalize(heading - initialHeading);
        return result;
    }
}