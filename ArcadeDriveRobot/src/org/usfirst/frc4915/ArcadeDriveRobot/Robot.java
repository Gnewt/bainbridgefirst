// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4915.ArcadeDriveRobot.commands.*;
import org.usfirst.frc4915.ArcadeDriveRobot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    SendableChooser autonomousChooser = new SendableChooser();
    
    Preferences prefs;
    public static double timeIntervalMovingForwardDuringAuto;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Harvester harvester;
    public static AirCompressor airCompressor;
    public static Launcher launcher;
    public static Gyroscope gyroscope;
    public static RangeFinder rangeFinder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //UPDATE VERSION NUMBER IN Version.java FROM NOW ON
    public static double batteryVoltage;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    
    
    public void robotInit() {
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        harvester = new Harvester();
        airCompressor = new AirCompressor();
        launcher = new Launcher();
        gyroscope = new Gyroscope();
        rangeFinder = new RangeFinder();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommandGroup();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        prefs = Preferences.getInstance();
        timeIntervalMovingForwardDuringAuto = prefs.getDouble("Autonomous Movement Duration", 2.4);
        
        autonomousChooser.addDefault("Launch Ball", new AutonomousCommandGroup());
        autonomousChooser.addObject("Don't Launch Ball", new AutonomousCommandGroupWithoutLaunch());
        SmartDashboard.putData("Autonomous Mode", autonomousChooser);
        
        System.out.println("Starting Spartronics Robot: " + Version.getVersion());
        SendUserMessages.display(1, Version.getVersion()); //SendUserMessages Limit to 20 characters
        SendUserMessages.display(2, "Check joystick order!!!!!");
        if (gyroscope != null) {
            gyroscope.reset();
        }
        else {
            SendUserMessages.display(3, "Gyroscope absent!!!");
        }
        if (airCompressor != null) {
            airCompressor.start();
        }
        //If the intake is up, we assume the ball is not launched
        if (harvester != null /*&& launcher.getLimitSwitchForLauncherDownValue()*/) {
            if (harvester.isHarvesterUp()) {
                harvester.retractPneumatics(); //keep the state
                launcher.setLaunchedBall(false);
            } else {
                harvester.extendPneumatics(); //keep the state
                launcher.setLaunchedBall(true);
            }
            harvester.stopWheels();
        }
        if (launcher != null) {
            launcher.pneumaticsReverse();
        }
        driveTrain.joystickThrottle = driveTrain.modifyThrottle();
    }
    
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousChooser != null) {
            autonomousCommand = (Command) autonomousChooser.getSelected();
            autonomousCommand.start();
        }
        else if (autonomousCommand != null) {
            autonomousCommand.start();
        }
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
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Gyroscope", gyroscope.getAngle());
        SmartDashboard.putBoolean("Launcher Limit Switch", launcher.getLimitSwitchForLauncherDownValue());
        SmartDashboard.putData(Scheduler.getInstance());
        batteryVoltage = DriverStation.getInstance().getBatteryVoltage();
        SmartDashboard.putNumber("BatteryVoltage: ", batteryVoltage);
        if (rangeFinder != null) {
            SmartDashboard.putNumber("Range (in mm): ", rangeFinder.getRange()); // assuming 1/1024 Volts/mm
            SmartDashboard.putNumber("Range (raw value)", rangeFinder.getVoltage());
        }
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
