package org.usfirst.frc4915.ArcadeDriveRobot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 * v.1.0.0
 */

import edu.wpi.first.wpilibj.DriverStationLCD;

/* This is a simple class to make sending messages to the Driver Station easier*/

public class SendUserMessages {

    public static void main(String[] args) {
        
    }
    
    /**
     *
     * @param line which line to print the message on
     * @param msg the message to be sent
     */
    public static void display(int line, String msg) {
        DriverStationLCD.Line l;
        switch(line) {
            case 1:
                l = DriverStationLCD.Line.kUser2;
                break;
            case 2:
                l = DriverStationLCD.Line.kUser3;
                break;
            case 3:
                l = DriverStationLCD.Line.kUser4;
                break;
            case 4:
                l = DriverStationLCD.Line.kUser5;
                break;
            case 5:
                l = DriverStationLCD.Line.kUser6;
                break;
            case 6:
                l = DriverStationLCD.Line.kMain6;
                break;
            default:
                l = DriverStationLCD.Line.kUser2;
                break;
        }

        DriverStationLCD.getInstance().println(l, 1, msg);
        DriverStationLCD.getInstance().updateLCD();
    }
}
