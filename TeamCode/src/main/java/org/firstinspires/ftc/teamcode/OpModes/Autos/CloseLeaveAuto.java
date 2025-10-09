package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class CloseLeaveAuto extends LinearOpMode {

    private DcMotor ShootyMcShooterPants = null;
    private CRServo lefty = null;
    private CRServo righty = null;

    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightRear");
        ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        lefty = hardwareMap.get(CRServo.class, "Servo1");
        righty = hardwareMap.get(CRServo.class, "Servo2");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        leftBack.setPower(-.5);
        rightBack.setPower(-.5);
        leftFront.setPower(-.5);
        rightFront.setPower(-.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);


        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        ShootyMcShooterPants.setPower(1);
        sleep(99999);
    }


}