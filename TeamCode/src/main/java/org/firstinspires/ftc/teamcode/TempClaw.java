package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.Servo;

public class TempClaw {
    private double speedMultiplier = 0.25;
    private double clawOpen = 0.96;
    private double clawClose = 0.5;
    private Servo grabber;
    private Motor angle;

    TempClaw(Servo g, Motor a) {
        grabber = g;
        angle = a;
        a.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        openClaw();
    }

    public void openClaw() {
        grabber.setPosition(clawOpen);
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