package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Tel", group = "Sensor")
public class TeleOpDrive extends LinearOpMode{

    HardwarePushBot robot = new
    HardwarePushBot();


    @Override
    public void runOpMode() throws InterruptedException {

        // Intialize the robot's hardware from HardwareMap amd allows you to run all this code within TeleOp
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Hello Driver", "Press Play Button");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Drive variables
            double rightPower = -gamepad1.right_stick_y * 0.8;  //what's the 0.8
            double leftPower = -gamepad1.left_stick_y * 0.8;
            double liftPower = -gamepad2.right_stick_y * 0.8;
            double gripPower = -gamepad2.left_stick_y * 0.8;

            double reverse_Thres = -0.3;
            double forward_Thres = 0.3;

            double up_Thres = 0.3;
            double down_Thres = -0.3;

            double drive_Speed = 0.9;
            double turn_Speed = 0.5;

            double intake_Speed = 0.5;         // mentions grip lift and intake
            double lift_Speed = 0.5;

            double grip_speed = 0.5;

            // Send calculated power to wheels
            // Drive system
            if (rightPower < forward_Thres && rightPower > reverse_Thres && leftPower < forward_Thres && leftPower > reverse_Thres) {
                DriveMethods.stopRightMotors();
                DriveMethods.stopLeftMotors();
            } else if (rightPower != 0.0 && leftPower != 0.0) {
                DriveMethods.driveRight(rightPower * drive_Speed);
                DriveMethods.driveLeft(leftPower * drive_Speed);
            } else if (rightPower != 0.0 && leftPower == 0.0) {
                DriveMethods.driveRight(rightPower * drive_Speed);
            } else if (leftPower != 0.0 && rightPower == 0.0) {
                DriveMethods.driveLeft(leftPower * drive_Speed);
            } else if (rightPower < reverse_Thres && leftPower > forward_Thres) {
                DriveMethods.driveLeft(leftPower * turn_Speed);
                DriveMethods.driveRight(rightPower * turn_Speed);
            } else if (rightPower > forward_Thres && leftPower < reverse_Thres) {
                DriveMethods.driveLeft(leftPower * turn_Speed);
                DriveMethods.driveRight(rightPower * turn_Speed);
            }

            if(gamepad2.y) {
                DriveMethods.startIntake(intake_Speed);
            }
            if(gamepad2.x){
                DriveMethods.stopIntake();
            }
            if(gamepad2.a){
                DriveMethods.startIntake(intake_Speed * -0.5);
            }

            if (liftPower > up_Thres)
                DriveMethods.liftUp(lift_Speed * 0.5);

            if (liftPower < down_Thres)
                DriveMethods.liftDown(lift_Speed * -0.5);

            if (gripPower > up_Thres)
                DriveMethods.gripUp(grip_speed * 0.5);

            if (gripPower < down_Thres)
                DriveMethods.gripDown(grip_speed * -0.5);
                 // mentions grip power

        }

        }
    }
