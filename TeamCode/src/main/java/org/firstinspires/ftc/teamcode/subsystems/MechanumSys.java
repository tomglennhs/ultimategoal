package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MechanumSys extends SubsystemBase {

    static final double WHEEL_DIAMETER = 4.0;

    private MotorEx fl, fr, br, bl;
    private MecanumDrive dt;
    private Motor.Encoder leftEnc, rightEnc;

    public MechanumSys(final HardwareMap hMap) {
        fl = new MotorEx(hMap, "FL");
        fr = new MotorEx(hMap, "FR");
        br = new MotorEx(hMap, "BR");
        bl = new MotorEx(hMap, "BL");

        bl.setInverted(true);
        fl.setInverted(false);
        fr.setInverted(false);
        br.setInverted(true);
        leftEnc = bl.encoder;
        rightEnc = br.encoder;

        resetEncoders();
        dt = new MecanumDrive(false, fl, fr, bl, br);
    }

    public void drive(double fwd, double strafe, double turn) {
        dt.driveRobotCentric(strafe, fwd, turn);
    }

    public void driveDist(double inchDistance, double pwr) {
        while (getAverageEncoderDistance() <= inchDistance) {
            dt.driveRobotCentric(0, pwr, 0);
        }
        dt.driveRobotCentric(0, pwr, 0);
    }

    public void driveDist(double inchDistance) {
        driveDist(inchDistance, 0.7);
    }

    public double getLeftEncoderVal() {
        return leftEnc.getPosition();
    }

    public double getLeftEncoderDistance() {
        return leftEnc.getRevolutions() * WHEEL_DIAMETER * Math.PI;
    }

    public double getRightEncoderVal() {
        return rightEnc.getPosition();
    }

    public double getRightEncoderDistance() {
        return rightEnc.getRevolutions() * WHEEL_DIAMETER * Math.PI;
    }

    public void resetEncoders() {
        leftEnc.reset();
        rightEnc.reset();
    }

    public double getAverageEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
    }

}
