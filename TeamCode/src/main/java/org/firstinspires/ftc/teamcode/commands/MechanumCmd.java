
package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

public class MechanumCmd extends CommandBase {
    private MechanumSys ss;
    private double drive, turn, strafe;
    private GamepadEx driverOp;

    public MechanumCmd(MechanumSys subsystem, GamepadEx ctrl) {
        ss = subsystem;
        driverOp = ctrl;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drive = MathUtils.clamp(-driverOp.getLeftY() + driverOp.getRightY(),
                -1,
                1);
        turn  =  -driverOp.getLeftX();
        strafe = -driverOp.getRightX();
        ss.drive(drive, strafe, turn);
    }

}
