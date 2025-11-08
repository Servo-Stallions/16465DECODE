package org.firstinspires.ftc.teamcode.OpModes.Depriciated;

import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;

@Disabled
@Autonomous
public class visionTestCopy extends LinearOpMode {

    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    private Limelight3A limelight;
    //private DcMotor Shooter;
    private Pose currentPose;
    private Follower follower;

    @Override
    public void runOpMode() throws InterruptedException
    {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startPose);
        limelight = hardwareMap.get(Limelight3A.class, "Limelight3A");
        //ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0);
        limelight.start();
        waitForStart();

        while (opModeIsActive()) {
            currentPose = follower.getPose();
            LLResult result = limelight.getLatestResult();
            double id = AprilTagLibrary.class.getModifiers();
            if (result != null) {
                if (result.isValid()) {
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("ta", result.getTa());
                    telemetry.addData("currentPose", currentPose.toString());
                    telemetry.update();
                }}
            if (result == null) {
                telemetry.addData("currentPose", "null");
                telemetry.update();
            }
            if (AprilTagLibrary.class.getModifiers() == 21) {
                //Shooter.setPower(1);
            }
            if (AprilTagLibrary.class.getModifiers() == 22) {
                //Shooter.setPower(1);
            }
            if (AprilTagLibrary.class.getModifiers() == 23) {
                //Shooter.setPower(1);
            }
        }
    }
}

//TODO HUDSON AND JB THIS HAS NO ERRORS BUT DOESNT SHOW DATA ON NULL; IT DOES HOWEVER USE POSE INSTEAD OF POSE3D SO ITS BETTER