package org.firstinspires.ftc.teamcode.OpModes.Depriciated;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;

@Autonomous
@Disabled

public class visionTest extends LinearOpMode {

    private Limelight3A limelight;
    //private DcMotor ShootyMcShooterPants;

    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight3A");
        //ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0);
        limelight.start();
        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            double id = AprilTagLibrary.class.getModifiers();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("ta", result.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.update();
                }}
            if (result == null) {
                Pose3D botpose = result.getBotpose();
                telemetry.addData("help", "Its broken");
                telemetry.update();
            }
            if (AprilTagLibrary.class.getModifiers() == 21) {
                //ShootyMcShooterPants.setPower(1);
                telemetry.addData("tag", "Tag 21");
                telemetry.update();
            }
            if (AprilTagLibrary.class.getModifiers() == 22) {
                //ShootyMcShooterPants.setPower(1);
                telemetry.addData("tag", "Tag 22");
                telemetry.update();
            }
            if (AprilTagLibrary.class.getModifiers() == 23) {
                //ShootyMcShooterPants.setPower(1);
                telemetry.addData("tag", "Tag 23");
                telemetry.update();
            }
        }
    }
}