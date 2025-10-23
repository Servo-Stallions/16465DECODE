package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Configurable
@TeleOp

public class twoPlayerFollowerDriveV2 extends OpMode{
    public static Follower follower;
    public static Pose startingPose;
    private boolean automatedDrive;
    private final Pose startpose = new Pose(0,0,0);

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor intake = null;

    private DcMotor shooterL = null;

    private DcMotor shooterR = null;

    private CRServo servoL = null;

    private CRServo servoR = null;

    private Servo door = null;

    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD );
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterL = hardwareMap.get(DcMotor.class, "shooterL");
        shooterL.setDirection(DcMotor.Direction.FORWARD );
        shooterR = hardwareMap.get(DcMotor.class, "shooterR");
        shooterR.setDirection(DcMotor.Direction.REVERSE );
        servoL = hardwareMap.get(CRServo.class, "servoL");
        servoR = hardwareMap.get(CRServo.class, "servoR");
        door = hardwareMap.get(Servo.class, "door");
    }

    public void init_loop() {

    }

    public void start() {

        follower.startTeleopDrive();
    }

    public void loop() {
        follower.update();
        if (!automatedDrive) {
            follower.setTeleOpDrive(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    true // Robot Centric
            );
        }
        intake.setPower(gamepad2.left_stick_y);
        shooterR.setPower(gamepad2.right_stick_y);
        shooterL.setPower(gamepad2.right_stick_y);
        if (gamepad2.b) {
            intake.setPower(1);
        }
        else if (gamepad2.x) {
            intake.setPower(-1);
        }
        else {
            intake.setPower(0);
        }
        if (gamepad2.a) {
            shooterR.setPower(.67);
            shooterL.setPower(.67);

        }
        //else if (gamepad1.b) {
            //intake.setPower(1);      }

        else {
            shooterL.setPower(0);
            shooterR.setPower(0);

        }
        if (gamepad2.dpad_up) {
            servoR.setPower(1);

        }
        else if (gamepad2.dpad_down) {
            servoR.setPower(-1);
        }
        else {
            servoR.setPower(0);

        }
        if (gamepad2.dpad_left) {
            servoL.setPower(1);

        }
        else if (gamepad2.dpad_right) {
            servoL.setPower(-1);
        }
        else {
            servoL.setPower(0);

        }
        if (gamepad2.left_bumper){
            door.setPosition(.72);
        }
        else if (gamepad2.right_bumper){
            door.setPosition(.42);
        }
        else {
            door.setPosition(.57);
        }

    }
}