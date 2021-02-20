package org.firstinspires.ftc.teamcode.subsystems;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.VelocityControl;

public class ShooterSys extends SubsystemBase {
    private final MotorEx motor;
    private boolean isRunning;
    private final double runVelocity = 24.0;
    private final double stopVelocity = 0.0;
    private final double velocityTolerance = 0.5;
    private final maxVelocityTolerance = runVelocity + velocityTolerance;
    private final minVelocityTolerance = runVelocity - velocityTolerance;

    public ShooterSys(final HardwareMap hMap) {
        motor = new MotorEx(hMap, "shooter");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        motor.setRunMode(VelocityControl);
        isRunning = false;
    }

    public void start() {
        // i believe setVelocity units are counts per revolution and the max for ultraplanetary motors is 28. we'll see if im wrong when we test. 
        // if that is wrong, the units are probably ticks per second
        motor.setVelocity(runVelocity);
        isRunning = true;
    }

    public void stop() {
        motor.setVelocity(stopVelocity);
        isRunning = false;
    }

    public void toggle() {
        if (isRunning) {
            stop();
        } else {
            start();
        }
    }

    public double getVelocity() {
        return motor.getVelocity();
    }
    
    public double getExpectedVelocity() {
        if (isRunning) {
            return runVelocity;
        }
        return stopVelocity;
    }

    public double getCPR() {
        return motor.getCPR();
    }

    public double get() {
        return motor.get();
    }

    public double getMaxRPM() {
        return motor.getMaxRPM();
    }
    
    public boolean readyForRing() {
        if (isRunning) {
            private final double currentVel = getVelocity();
            return currentVel < maxVelocityTolerance && currentVel > minVelocityTolerance;
        }
        return false;
    }


}
