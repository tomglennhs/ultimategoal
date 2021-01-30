package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.TempClaw;
import com.qualcomm.robotcore.hardware.Servo;
import com.arcrobotics.ftclib.util.MathUtils;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HDrive", group = "Drive")
public class HDrive extends LinearOpMode {

    double speed_multiplier = 1.0;
    double shooter_on = 1.0;
    double shooter_off = 0.0;
    double shooter_power = shooter_off;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        double drive, turn, left, right, max, strafe, angleSpeed;
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);
        GamepadEx gamepad2Ex = new GamepadEx(gamepad2);
        MotorEx leftMotor = new MotorEx(hardwareMap, "Left_Motor", Motor.GoBILDA.NONE);
        MotorEx centerMotor = new MotorEx(hardwareMap, "Center_Motor", Motor.GoBILDA.NONE);
        MotorEx rightMotor = new MotorEx(hardwareMap, "Right_Motor", Motor.GoBILDA.NONE);
        MotorEx shooter = new MotorEx(hardwareMap, "shooter", Motor.GoBILDA.NONE);

        Servo grabber = hardwareMap.get(Servo.class, "serve");
        MotorEx claw_angle = new MotorEx(hardwareMap, "claw", Motor.GoBILDA.NONE);

        TempClaw claw = new TempClaw(grabber, claw_angle);

        telemetry.addData("Status", "Ready To Run");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Started Loop", "yes");
            telemetry.update();

            /* The following code is mostly copy-pasted verbatim from the PushbotTeleopPOV_Linear sample OpMode. */

            drive = MathUtils.clamp(-gamepad1Ex.getLeftY() - -gamepad1Ex.getRightY(), -1, 1);
            turn  =  -gamepad1Ex.getRightX();
            strafe = -gamepad1Ex.getLeftX();

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
    

           if (gamepad2Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
            claw.openClaw();
        } else if (gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
            claw.closeClaw();
       }

            leftMotor.setVelocity(left*speed_multiplier);
            rightMotor.setVelocity(right*speed_multiplier);
            centerMotor.setVelocity(strafe*speed_multiplier);
            shooter.setVelocity(shooter_power);

            angleSpeed = MathUtils.clamp(gamepad2Ex.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - gamepadEx.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), -1, 1);
            claw.angleSpeed(angleSpeed);

            telemetry.addData("Speed Multiplier", speed_multiplier);
            telemetry.addData("Center Motor Velocity", centerMotor.getVelocity());
            telemetry.addData("Left Motor Velocity", leftMotor.getVelocity());
            telemetry.addData("Right Motor Velocity", rightMotor.getVelocity());
            telemetry.addData("Shooter Motor Velocity", shooter.getVelocity());
            telemetry.update();

        }
    }
}

