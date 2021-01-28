package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HDrive", group = "Drive")
public class HDrive extends LinearOpMode {

    double speed_multiplier = 1.0;
    double shooter_on = 1.0;
    double shooter_off = 0.0;
    double shooter_power = shooter_off;

    @Override
    public void runOpMode() throws InterruptedException {
        // Place your init code here. Initialize motors and sensors here
        telemetry.addData("Status", "Starting...");
        telemetry.update();

        double drive, turn, left, right, max, strafe;
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);
        MotorEx leftMotor = new MotorEx(hardwareMap, "Left_Motor", Motor.GoBILDA.NONE);
        MotorEx centerMotor = new MotorEx(hardwareMap, "Center_Motor", Motor.GoBILDA.NONE);
        MotorEx rightMotor = new MotorEx(hardwareMap, "Right_Motor", Motor.GoBILDA.NONE);
        MotorEx shooter = new MotorEx(hardwareMap, "shooter", Motor.GoBILDA.NONE);


        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            /* The following code is mostly copy-pasted verbatim from the PushbotTeleopPOV_Linear sample OpMode. */

            drive = MathUtils.clamp(gamepad1.left_stick_y - gamepad1.right_stick_y, -1, 1);
            turn  =  gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;

            // Combine drive and turn for blended motion.
            left  = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }
        
            if (gamepad1Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                shooter_power = shooter_off;
            } else if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                shooter_power = shooter_on;
           }
    
            leftMotor.set(left*speed_multiplier);
            rightMotor.set(right*speed_multiplier);
            centerMotor.set(strafe*speed_multiplier);
            shooter.set(shooter_power);


            telemetry.addData("Speed Multiplier", speed_multiplier);
            telemetry.addData("Center Motor Velocity", centerMotor.getVelocity());
            telemetry.addData("Left Motor Velocity", leftMotor.getVelocity());
            telemetry.addData("Right Motor Velocity", rightMotor.getVelocity());
            telemetry.addData("Shooter Motor Velocity", shooter.getVelocity());
            telemetry.update();

        }

    }
}
