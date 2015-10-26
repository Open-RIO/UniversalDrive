package jaci.openrio.module.unidrive.drive;

import edu.wpi.first.wpilibj.Joystick;
import jaci.openrio.module.unidrive.UDPreference;

/**
 * The Arcade DriveManager. This is designed for robots using 2-4 wheel configurations with normal wheels or treads. This is configured in the Configuration file.
 *
 * @author floogulinc
 */
public class DriveArcade implements DriveManager {
    @Override
    public String type() {
        return "ARCADE";
    }

    @Override
    public void init() { }

    @Override
    public void tick() {
        Joystick joy = UDPreference.joystick;
        if (UDPreference.JOYSTICK_LAYOUT.toLowerCase().equals("xbox_stick")) {
            UDPreference.drive.arcadeDrive(joy, UDPreference.DRIVE_SQUAREDINPUTS);
        }
    }
}
