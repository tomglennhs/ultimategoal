package org.firstinspires.ftc.teamcode.old;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Mechanum", group = "FTCLib - Java")
public class Mechanum extends LinearOpMode {

    public enum Intake {
        ON(-1.0), OFF(0.0);
        private double state;

        Intake(double speed) {
            this.state = speed;
        }

        public void toggle() {
            if (this.state == ON.state) {
                this.state = OFF.state;
            } else {
                this.state = ON.state;
            }
        }

        public double getState() {
           return state;
        }

    }

    @Override
    public void runOpMode() throws InterruptedException {

        Intake ringIntake = Intake.ON;

        // Initializing gyroscope
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        MotorEx frontLeft = new MotorEx(hardwareMap, "FL", Motor.GoBILDA.NONE);
        MotorEx frontRight = new MotorEx(hardwareMap, "FR", Motor.GoBILDA.NONE);
        MotorEx backLeft = new MotorEx(hardwareMap, "BL", Motor.GoBILDA.NONE);
        MotorEx backRight = new MotorEx(hardwareMap, "BR", Motor.GoBILDA.NONE);
        MotorEx intakeMotor = new MotorEx(hardwareMap, "Intake", Motor.GoBILDA.NONE);

        GamepadEx driver1 = new GamepadEx(gamepad1);

        MecanumDrive mecanum = new MecanumDrive(true, frontLeft, frontRight,
                backLeft, backRight);

        double angle;

        int angleMode = 1;
        int btn = 0;



        waitForStart();

        ButtonReader angleChange = new ButtonReader(driver1, GamepadKeys.Button.A);
        ButtonReader intakeStateChange = new ButtonReader(driver1, GamepadKeys.Button.X);

        while (opModeIsActive()) {
            switch (angleMode) {
                case 1:
                    angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
                case 2:
                    angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).secondAngle;
                case 3:
                    angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;
                default:
                    angle = 0;
                    break;
            }



//            mecanum.driveFieldCentric(
//                    driver1.getLeftX(),
//                    driver1.getLeftY(),
//                    driver1.getRightX(),
//                    angle);

            mecanum.driveRobotCentric(
                    driver1.getLeftX(),
                    MathUtils.clamp(driver1.getLeftY() - driver1.getRightY(), -1, 1),
                    driver1.getRightX());


            if (angleChange.isDown()) {
                angleMode++;
                if (angleMode > 3) {
                    angleMode = 1;
                }
            }

            if (intakeStateChange.isDown()) {
                btn++;
                ringIntake.toggle();
            }

            intakeMotor.set(ringIntake.getState());
            telemetry.addData("Angle Mode (press A to Change)", angleMode);
            telemetry.addData("Intake State (press X to Change)", ringIntake);
            telemetry.addData("X Button presses", btn);
            telemetry.update();
        }

    }
}
