package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Gate;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        DriveTrain driveTrain = new DriveTrain();
        driveTrain.initiate(hardwareMap);
        Intake intake = new Intake();
        intake.initiate(hardwareMap);
        Gate gate = new Gate();
        gate.initiate(hardwareMap);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            driveTrain.run(x, y, rx);
            intake.update();
            gate.update();

            if (gamepad1.aWasPressed()){
                if (intake.getState() == Intake.States.STOP){
                    intake.setState(Intake.States.IN);
                }else{
                    intake.setState(Intake.States.STOP);
                }
            }

            if (gamepad1.bWasPressed()){
                if (intake.getState() == Intake.States.STOP){
                    intake.setState(Intake.States.OUT);
                }else{
                    intake.setState(Intake.States.STOP);
                }
            }
            if (gamepad1.xWasPressed()) {
                if (gate.getState() == Gate.States.CLOSE){
                    gate.setState(Gate.States.OPEN);
                }
                else{
                    gate.setState(Gate.States.CLOSE);
                }
            }
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]



        }
    }


}