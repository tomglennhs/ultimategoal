package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.Servo;

public class TempClaw {
    private double speedMultiplier = 0.1;
    private double clawOpen = 0.0;
    private double clawClose = 1.0;
    private Servo grabber;
    private MotorEx angle;

    TempClaw(Servo g, MotorEx a) {
        grabber = g;
        angle = a;
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

    public void angleSpeed(double speed) {
        angle.setVelocity(speed*speedMultiplier);
    }

}