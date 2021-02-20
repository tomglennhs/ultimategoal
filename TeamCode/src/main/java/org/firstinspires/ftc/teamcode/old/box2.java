package org.firstinspires.ftc.teamcode.old;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "HDrive B (1 Ring)", group = "HDrive", preselectTeleOp="HDrive Teleop")
public class box2 extends PrimaryAuton {
    @Override
    public void initThings() {
        // 9 feet -> inches
        distanceL = 9.0*12.0;
        distanceR = distanceL;
    }
}
