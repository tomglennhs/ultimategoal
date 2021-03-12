package org.firstinspires.ftc.teamcode.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.RawPower;
import static com.arcrobotics.ftclib.hardware.motors.Motor.RunMode.VelocityControl;

public class ConveyorSys extends SubsystemBase {
    private final MotorEx motor;
    private boolean isRunning;

    public ConveyorSys(final HardwareMap hMap) {
        motor = new MotorEx(hMap, "conveyor");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.setRunMode(RawPower);
        motor.setInverted(false);
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
}
