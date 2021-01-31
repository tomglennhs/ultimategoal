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
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;

@TeleOp(name = "HDrive", group = "Drive")
public class HDrive extends LinearOpMode {

    double speed_multiplier = 1.0;
    double shooter_on = 1.0;
    double shooter_off = 0.0;
    double shooter_power = shooter_off;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        
        double drive, turn, left, right, max, strafe, angleSpeed;
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);
        GamepadEx gamepad2Ex = new GamepadEx(gamepad2);
        Motor leftMotor = new Motor(hardwareMap, "Left_Motor", Motor.GoBILDA.NONE);
        Motor centerMotor = new Motor(hardwareMap, "Center_Motor", Motor.GoBILDA.NONE);
        Motor rightMotor = new Motor(hardwareMap, "Right_Motor", Motor.GoBILDA.NONE);
        Motor shooter = new Motor(hardwareMap, "shooter", Motor.GoBILDA.NONE);

        Servo grabber = hardwareMap.get(Servo.class, "serve");
        Motor claw_angle = new Motor(hardwareMap, "claw", Motor.GoBILDA.NONE);

        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule module : allHubs) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        TempClaw claw = new TempClaw(grabber, claw_angle);
        Motor[] motors = {leftMotor, rightMotor};
        // i would use FTCLib's HDrive drivebase but it was being weird with strafing for some reason.
        DifferentialDrive dt = new DifferentialDrive(true, motors);

        telemetry.addData("Status", "Ready To Run");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule module : allHubs) {
                module.clearBulkCache();
            }
            
            drive = MathUtils.clamp(-gamepad1Ex.getLeftY() + gamepad1Ex.getRightY(), -1, 1);
            turn  =  -gamepad1Ex.getLeftX();
            strafe = -gamepad1Ex.getRightX();
        
            if (gamepad1Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                shooter_power = shooter_off;
            } else if (gamepad1Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                shooter_power = shooter_on;
            }

            if (gamepad2Ex.getButton(GamepadKeys.Button.LEFT_BUMPER) || gamepad1Ex.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                claw.openClaw();
            } else if (gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER) || gamepad2Ex.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                claw.closeClaw();
            }

            dt.arcadeDrive(drive, turn);
            centerMotor.set(strafe);
            // shooter.set(shooter_power);

            angleSpeed = MathUtils.clamp(gamepad2Ex.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - gamepad2Ex.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), -1, 1);
            claw.angleSpeed(angleSpeed);

            // telemetry.addData("Speed Multiplier", speed_multiplier);
            telemetry.addData("Claw Open?", claw.isOpen());
            telemetry.addData("Fwd", drive);
            telemetry.addData("Turn", turn);
            telemetry.addData("Strafe", strafe);
            telemetry.update();

        }
    }
}

