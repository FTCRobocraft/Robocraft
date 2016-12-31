package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/23/16.
 */
public class HeadingNormalizer  {

    public HeadingNormalizer () {
    }

    public double normalizeHeaing(double heading) {
        double result = heading;
        if (heading > 180) {
            result = heading - 360D;
        }
        return result;
    }
}
