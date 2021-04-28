package org.firstinspires.ftc.teamcode.commands.claw;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.subsystems.ClawSys;

public class ClawCtrlCmd extends CommandBase {
    public ClawSys ss;
    public GamepadEx ctrl;
    double spd, trig;
    double stay = 0.1;

    public ClawCtrlCmd(final ClawSys subsystem, final GamepadEx c) {
        ss = subsystem;
        ctrl = c;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {

        trig = ctrl.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - ctrl.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER);

        // this is a hack to "brake" motors
        if (Math.abs(trig) > stay) {
            spd = trig;
        } else {
            spd = stay;
        }
        ss.angleSpeed(spd);
        if (ctrl.getButton(GamepadKeys.Button.A)) {
            ss.openClaw();
        } else {
            ss.closeClaw();
        }
    }


}
