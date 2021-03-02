package org.firstinspires.ftc.teamcode.subsystems;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class ShooterSys extends SubsystemBase {
    private final MotorEx motor;
    private boolean isRunning;
    // eventually i'll want to make these tunable by ftc dashboard
    private final boolean invert = false;
    // velocity is in degrees per second. the theoretical max is 36_000 deg/s since the motor has a max rpm of 6000
    private final double runVelocity = 34_000.0;
    private final double stopVelocity = 0.0;
    private final double velocityTolerance = 50.0;
    private final double maxVelocityTolerance = runVelocity + velocityTolerance;
    private final double minVelocityTolerance = runVelocity - velocityTolerance;


    public ShooterSys(final HardwareMap hMap) {
        motor = new MotorEx(hMap, "shooter");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        motor.setRunMode(Motor.RunMode.VelocityControl);
        motor.setInverted(invert);
        isRunning = false;
    }

    public void start() {
        motor.setVelocity(runVelocity, AngleUnit.DEGREES);
        isRunning = true;
    }

    public void stop() {
        motor.setVelocity(stopVelocity, AngleUnit.DEGREES);
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
        return motor.getVelocity(AngleUnit.DEGREES);
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
            final double currentVel = getVelocity();
            return currentVel < maxVelocityTolerance && currentVel > minVelocityTolerance;
        }
        return false;
    }

    public boolean getState() {
        return isRunning;
    }

}
