package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;

public class HDriveSys extends SubsystemBase {
    private MotorEx l, r, c;
    private DifferentialDrive dt;
    private Encoder leftEnc, rightEnc;

    // this needs to be in inches, so we convert the 90mm diameter
    // of our REV Omni Wheels to inches by dividing by 25.4.
    // since this doesn't need to be mutated we declare this as final
    static final double WHEEL_DIAMETER = 90.0 / 25.4;

    public HDriveSys(final HardwareMap hMap) {
        l = new MotorEx(hMap, "Left_Motor");
        r = new MotorEx(hMap, "Right_Motor");
        c = new MotorEx(hMap, "Center_Motor");
        l.setInverted(false);
        r.setInverted(true);
        leftEnc = l.encoder;
        rightEnc = r.encoder;


        MotorEx[] motors = {l, r};
        resetEncoders();
        dt = new DifferentialDrive(true, motors);
    }

    public void drive(double fwd, double strafe, double turn) {
        dt.arcadeDrive(fwd, turn);
        c.set(strafe);
    }

    public void driveDist(double inchDistance, double pwr) {
        double offset = getAverageEncoderDistance();
        while (getAverageEncoderDistance()-offset <= inchDistance) {
            dt.arcadeDrive(pwr, 0);
        }
        dt.arcadeDrive(0,0);
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
