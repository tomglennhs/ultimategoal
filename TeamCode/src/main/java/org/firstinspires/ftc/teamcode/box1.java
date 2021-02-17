package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "HDrive A (0 Rings)", group = "HDrive", preselectTeleOp="HDrive Teleop")
public class box1 extends PrimaryAuton {
    @Override
    public void initThings() {
        // 7 feet -> inches
        distanceL = 7.0*12.0;
        distanceR = distanceL;
    }
}
