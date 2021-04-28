package org.firstinspires.ftc.teamcode.examples;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

//@TeleOp(name = "SensorTesting20201010 but in Java", group = "Testing")
public class JavaSensorTest extends LinearOpMode {

    private ColorSensor sensorColorRange_REV_ColorRangeSensor;
    private DistanceSensor dist;
    private TouchSensor touch;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float gain;
        NormalizedRGBA normalizedColors;
        float hue;
        float saturation;
        float value;
        int color;

        sensorColorRange_REV_ColorRangeSensor = hardwareMap.get(ColorSensor.class, "sensorColorRange");
        dist = hardwareMap.get(DistanceSensor.class, "dist");
        touch = hardwareMap.get(TouchSensor.class, "touch");

        // This op mode demonstrates the color and distance features of the REV sensor.
        gain = 1;
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Display distance info.
                telemetry.addData("Cm (Color Sensor)", ((DistanceSensor) sensorColorRange_REV_ColorRangeSensor).getDistance(DistanceUnit.CM));
                telemetry.addData("Cm (Dist Sensor)", dist.getDistance(DistanceUnit.CM));
                telemetry.addData("Touch Sensor", touch.isPressed());
                // Adjust the gain.
                if (gamepad1.a) {
                    gain += 0.005;
                } else if (gamepad1.b && gain >= 1.005) {
                    gain += -0.005;
                }
                ((NormalizedColorSensor) sensorColorRange_REV_ColorRangeSensor).setGain(gain);
                // Read color from the sensor.
                normalizedColors = ((NormalizedColorSensor) sensorColorRange_REV_ColorRangeSensor).getNormalizedColors();
                // Convert RGB values to Hue, Saturation, and Value.
                // See https://en.wikipedia.org/wiki/HSL_and_HSV for details on HSV color model.
                color = normalizedColors.toColor();
                hue = JavaUtil.colorToHue(color);
                saturation = JavaUtil.colorToSaturation(color);
                value = JavaUtil.colorToValue(color);
                // Show the color on the Robot Controller screen.
                JavaUtil.showColor(hardwareMap.appContext, color);
                // Use hue to determine if it's red, green, blue, etc..
                if (hue < 30) {
                    telemetry.addData("Color", "Red");
                } else if (hue < 60) {
                    telemetry.addData("Color", "Orange");
                } else if (hue < 90) {
                    telemetry.addData("Color", "Yellow");
                } else if (hue < 150) {
                    telemetry.addData("Color", "Green");
                } else if (hue < 225) {
                    telemetry.addData("Color", "Blue");
                } else if (hue < 350) {
                    telemetry.addData("Color", "purple");
                } else {
                    telemetry.addData("Color", "Red");
                }
                if (value < 0.16) {
                    telemetry.addData("Check Val", "Is surface black?");
                }
                // Check to see if it might be black or white.
                if (saturation < 0.2) {
                    telemetry.addData("Check Sat", "Is surface white?");
                }
                telemetry.update();
                // end of opmode active loop
            }
            JavaUtil.showColor(hardwareMap.appContext, Color.parseColor("white"));
        }
    }
}
