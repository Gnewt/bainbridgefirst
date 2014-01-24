/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ExampleCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;
    RobotDrive myDrive;
    Joystick driveStick;
    Gyro gyro;
    Victor frontLeft, frontRight, rearLeft, rearRight;
    static final double PROPORTIONAL_GAIN = 0.03; // tuning parameter (Kp) for PID loops
    /*
     * Increasing Kp will cause the robot to correct more quickly 
     * (but too high and it will oscillate). Decreasing the value will cause 
     * the robot correct more slowly (possibly never reaching the desired heading)
     */
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
        
        frontLeft = new Victor(1); // Creating Victor motors
        frontRight = new Victor(2);
        rearLeft = new Victor(3);
        rearRight = new Victor(4);
        
        myDrive = new RobotDrive(frontLeft, frontRight);
        // myDrive = new RobotDrive(frontLeft,frontRight,rearLeft,rearRight);
        
        driveStick = new Joystick(1);
        
        gyro = new Gyro(1);

        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
        // schedule the autonomous command
        autonomousCommand.start();
        while(isAutonomous() && isEnabled()){
            double angle = gyro.getAngle(); // get heading
            myDrive.arcadeDrive(-1.0, -angle * PROPORTIONAL_GAIN); // drive to heading
            Timer.delay(0.01);
        }
        myDrive.drive(0.0, 0.0); // stop robot
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        while (isOperatorControl() && isEnabled()){
            myDrive.arcadeDrive(driveStick);
            Timer.delay(0.01);
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        System.out.println("Running Test Periodic");
        System.out.println("Hello Again");
    }
}