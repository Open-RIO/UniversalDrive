package jaci.openrio.module.unidrive.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import jaci.openrio.module.unidrive.UDPreference;

/**
 * The Tank DriveManager. This is designed for robots using 2-4 wheel configurations with normal wheels or treads. This is
 * the most common drive type. This is configured in the Configuration file.
 *
 * @author Jaci
 */
public class DriveTank implements DriveManager {
    @Override
    public String type() {
        return "TANK";
    }

    @Override
    public void init() { }

    @Override
    public void tick() {
        Joystick joy = UDPreference.joystick;
        if (UDPreference.JOYSTICK_LAYOUT.toLowerCase().equals("xbox_stick")) {
            UDPreference.drive.tankDrive(joy.getY(GenericHID.Hand.kLeft), joy.getY(GenericHID.Hand.kRight), UDPreference.DRIVE_SQUAREDINPUTS);
        }
    }
}
