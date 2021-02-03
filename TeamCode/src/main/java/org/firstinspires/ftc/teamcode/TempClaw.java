package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

public class TempClaw {
    private final double speedMultiplier = 0.25;
    private final double clawOpen = 0.96;
    private final double clawClose = 0.5;
    private final Servo grabber;
    private final Motor angle;

    TempClaw(Servo g, Motor a, boolean openClawOnInit) {
        grabber = g;
        angle = a;
        a.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        if (openClawOnInit) {
            openClaw();
        }
    }
    TempClaw(Servo g, Motor a) {
        this(g, a, true);
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