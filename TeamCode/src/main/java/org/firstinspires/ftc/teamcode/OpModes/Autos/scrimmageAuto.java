package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "scrimmageAuto", group = "Autos")
@Disabled
public class scrimmageAuto extends OpMode {

    private DcMotorEx slides;
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private final Pose BotPose0 = new Pose(0,0,Math.toRadians(0));
    private final Pose BotPose1 = new Pose(12,0,Math.toRadians(0));

    //(ಠ_ಠ)//
    private PathChain justGo;
    //private PathChain BotPath1;

    public void buildPaths() {
        justGo = follower.pathBuilder()
                .addPath(new BezierLine(new Pose(), new Pose()))
                .setLinearHeadingInterpolation(BotPose0.getHeading(),BotPose1.getHeading())
                .setBrakingStrength(6)
                .setTimeoutConstraint(250)
                .build();

    }
    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0: // Move from start to scoring position
                follower.followPath(justGo);
                setPathState(10);
                break;
        }

    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
    @Override
    public void init() {
        pathTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(BotPose0);
        buildPaths();
    }


    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("Position", follower.getPose().toString());
        telemetry.update();
    }

}
