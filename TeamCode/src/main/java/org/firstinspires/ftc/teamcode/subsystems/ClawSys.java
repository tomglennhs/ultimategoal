package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor.Encoder;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSys extends SubsystemBase {
    private final double speedMultiplier = 0.25;
    private final double clawOpen = 0.96;
    private final double clawClose = 0.5;
    private final ServoEx grabber;
    private final MotorEx angle;


    // this needs to be in inches, so we convert the 90mm diameter
    // of our REV Omni Wheels to inches by dividing by 25.4.
    // since this doesn't need to be mutated we declare this as final
    static final double WHEEL_DIAMETER = 90.0 / 25.4;

    public ClawSys(final HardwareMap hMap) {
        grabber = (ServoEx) hMap.get(Servo.class, "claw");
        angle = new MotorEx(hMap, "angleSpeed");;
        angle.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void openClaw() {
        grabber.setPosition(clawOpen);
    }

    public void toggleClaw() {
        if (isOpen()) {
            closeClaw();
        } else {
            openClaw();
        }
    }

    public void closeClaw() {
        grabber.setPosition(clawClose);
    }

    public boolean isOpen() {
        return grabber.getPosition() == clawOpen;
    }

    public boolean isClosed() {
        return grabber.getPosition() == clawClose;
    }

    public double getGrabberPosition() {
        return grabber.getPosition();
    }

    public void angleSpeed(double speed) {
        angle.set(speed*speedMultiplier);
    }

}
