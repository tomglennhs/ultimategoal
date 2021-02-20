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

    public ShooterSys(final HardwareMap hMap, MultipleTelemetry telemetry) {
        motor = new MotorEx(hMap, "shooter");;
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        motor.setRunMode(VelocityControl);
        isRunning = false;
    }

    public void start() {
        motor.set(1);
        isRunning = true;
    }

    public void stop() {
        motor.set(0);
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

    public double getCorrectedVelocity() {
        return motor.getCorrectedVelocity();
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


}
