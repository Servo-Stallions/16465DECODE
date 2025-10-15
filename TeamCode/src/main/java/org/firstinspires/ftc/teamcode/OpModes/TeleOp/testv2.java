package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class testv2 extends OpMode{
    private DcMotor intake = null;



    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD );


    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            intake.setPower(1);
        } else if (gamepad1.y) {
            intake.setPower(1.0);
            telemetry.addData("Shooter Status", "right bumper pressed");
            telemetry.update();
        } else if (gamepad1.x) {
            intake.setPower(-1.0);
            telemetry.addData("Shooter Status", "x pressed");
            telemetry.update();
    }
}
