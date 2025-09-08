package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SussyLittleImposterCode extends LinearOpMode{
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;

    private DcMotor ShootyMcShootyPants = null;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightRear");
        ShootyMcShootyPants = hardwareMap.get(DcMotor.class, "Shooter");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        ShootyMcShootyPants.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "You're good go go go");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            double left;
            double right;

            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            leftFront.setPower(left);
            rightFront.setPower(right*0.85);
            leftBack.setPower(left);
            rightBack.setPower(right);
            if (gamepad1.right_bumper){
                leftFront.setPower(-1.0) ;
                rightFront.setPower(1.0) ;
                leftBack.setPower(1.0) ;
                rightBack.setPower(-1.0) ;} //strafe right
            else if (gamepad1.left_bumper){
                leftFront.setPower(1.0) ;
                rightFront.setPower(-1.0) ;
                leftBack.setPower(-1.0) ;
                rightBack.setPower(1.0) ;}

            else if (gamepad1.a){
                ShootyMcShootyPants.setPower(1.0) ;}


        }
    }

}
