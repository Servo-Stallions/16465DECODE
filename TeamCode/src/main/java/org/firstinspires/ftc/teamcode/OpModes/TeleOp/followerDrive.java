package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrent;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrentAndHistory;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.PoseHistory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Configurable
@TeleOp

public class followerDrive extends OpMode{
    public static Follower follower;
    public static Pose startingPose;
    private boolean automatedDrive;
    private final Pose startpose = new Pose(0,0,0);

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor ShootyMcShooterPants = null;
    private CRServo lefty = null;
    private CRServo righty = null;


    //public static TelemetryManager telemetryM;
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        lefty = hardwareMap.get(CRServo.class, "Servo1");
        righty = hardwareMap.get(CRServo.class, "Servo2");
        ShootyMcShooterPants.setDirection(DcMotor.Direction.FORWARD );


    }

    public void init_loop() {

    }

    public void start() {

        follower.startTeleopDrive();
    }

    public void loop() {
        follower.update();
        if (!automatedDrive) {
            //Make the last parameter false for field-centric
            //In case the drivers want to use a "slowMode" you can scale the vectors
            //This is the normal version to use in the TeleOp
            follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    true // Robot Centric
            );
        }

        if (gamepad1.a) {
            ShootyMcShooterPants.setPower(0.7);
        } else if (gamepad1.y) {
            ShootyMcShooterPants.setPower(1.0);
            telemetry.addData("Status", "y pressed");
            telemetry.update();
        } else if (gamepad1.x) {
            ShootyMcShooterPants.setPower(-1.0);
        }
        else {
            ShootyMcShooterPants.setPower(0.6);
            telemetry.addData("Status", "nothing");
            telemetry.update();
        }

        if (gamepad1.b) {
            lefty.setPower(-1);
            righty.setPower(1);

            gamepad1.rumble(1000);

        }
        else if (gamepad1.dpad_down) {
            lefty.setPower(1);
            righty.setPower(-1);
        }else {
            lefty.setPower(0);
            righty.setPower(0);
        }


    }
}