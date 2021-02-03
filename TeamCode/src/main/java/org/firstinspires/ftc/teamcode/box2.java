package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auton B", group = "HDrive", preselectTeleOp="HDrive Teleop")
public class box2 extends PrimaryAuton {
    @Override
    public void initThings() {
        // 9 feet -> inches
        distanceL = 9.0*12.0;
        distanceR = distanceL;
    }
}
