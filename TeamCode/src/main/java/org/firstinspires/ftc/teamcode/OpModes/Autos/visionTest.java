package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.hardware.limelightvision.LLFieldMap;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous
public class visionTest extends LinearOpMode {

    private Limelight3A limelight;
    private DcMotor ShootyMcShooterPants;


    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight3A");
        ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);


        limelight.start();
        waitForStart();

        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            double id = 0;
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("ta", result.getTa());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.update();

                }

            }
            if (result == null) {
                Pose3D botpose = result.getBotpose();
                telemetry.addData("no worky worky", "no worky worky");
                telemetry.update();

            }
            if (id == 21) {
                ShootyMcShooterPants.setPower(1);

                sleep(99999);
            }
        }

        }
    }
