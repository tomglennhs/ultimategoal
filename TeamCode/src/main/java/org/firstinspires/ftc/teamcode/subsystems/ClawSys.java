package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSys extends SubsystemBase {
    private final double speedMultiplier = 0.5;
    private final double clawOpen = 0.7;
    private final double clawClose = 0;
    private final Servo grabber;
    private final MotorEx angle;

    public ClawSys(final HardwareMap hMap) {
        grabber = hMap.get(Servo.class, "claw");
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
