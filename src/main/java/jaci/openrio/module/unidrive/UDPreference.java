package jaci.openrio.module.unidrive;

import edu.wpi.first.wpilibj.*;
import jaci.openrio.toast.core.Toast;
import jaci.openrio.toast.core.loader.groovy.GroovyPreferences;

import java.util.ArrayList;

/**
 * The Preferences configuration for Universal Drive. This is where all config parsing is done
 *
 * @author Jaci
 */
public class UDPreference {

    static GroovyPreferences pref;
    public static String DRIVE_TYPE;
    public static String MOTOR_INTERFACE;
    public static String MOTOR_TYPE;
    public static int MOTOR_COUNT;
    public static ArrayList<Integer> MOTOR_IDS;
    public static SpeedController[] controllers;
    public static int JOYSTICK_PORT;
    public static String JOYSTICK_LAYOUT;
    public static RobotDrive drive;

    public static boolean valid = true;

    public static void load() {
        pref = new GroovyPreferences("UniversalDrive");
        DRIVE_TYPE = pref.getString("drive.type", "TANK", "The type of Drive to use. Possible options are: [TANK, MECANUM]");
        MOTOR_INTERFACE = pref.getString("drive.interface", "PWM", "The interface to use for the Motor Controllers. Possible values are: [PWM, CAN]");
        MOTOR_TYPE = pref.getString("drive.motor", "Talon", "The type of motor controller to use. Possible values are: [Talon, Jaguar, Victor, Victor SP]");
        MOTOR_IDS = (ArrayList<Integer>) pref.getObject("drive.ports", new Integer[] {0, 1}, "The motor ports to use. This is the PWM/CAN interface ID the motor controller is attached to.", "Pattern: [left, right] OR [front left, back left, front right, back right]");
        MOTOR_COUNT = MOTOR_IDS.size();

        try {
            switch (MOTOR_INTERFACE.toLowerCase()) {
                case "pwm":
                    switch (MOTOR_TYPE.toLowerCase()) {
                        case "talon":
                            create(Talon.class);
                            break;
                        case "jaguar":
                            create(Jaguar.class);
                            break;
                        case "victor":
                            create(Victor.class);
                            break;
                        case "victor sp":
                            create(VictorSP.class);
                            break;
                        default:
                            throw new IllegalArgumentException("Motor Type not supported! " + MOTOR_TYPE);
                    }
                    break;
                case "can":
                    switch (MOTOR_TYPE.toLowerCase()) {
                        case "talon":
                            create(CANTalon.class);
                            break;
                        case "jaguar":
                            create(CANJaguar.class);
                            break;
                        default:
                            throw new IllegalArgumentException("Motor Type not supported! " + MOTOR_TYPE);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Motor Interface not supported! " + MOTOR_INTERFACE);
            }
        } catch (Exception e) {
            valid = false;
            Toast.log().exception(e);
        }

        JOYSTICK_PORT = pref.getInt("joy.port", 0, "Get the JoyStick port to use for the Drive");
        JOYSTICK_LAYOUT = pref.getString("joy.layout", "XBOX_STICK", "Defines the Control Layout of the Joystick. This is different per Drive Type.",
                "TANK: XBOX_STICK (analog L/R stick drive)",
                "MECANUM: XBOX_STICK (L analog stick for X/Y, R analog stick for rotation)");
        joystick = new Joystick(JOYSTICK_PORT);
    }

    public static Joystick joystick;

    public static void create(Class<? extends SpeedController> type) {
        controllers = new SpeedController[MOTOR_COUNT];
        for (int i = 0; i < controllers.length; i++) {
            try {
                controllers[i] = type.getDeclaredConstructor(int.class).newInstance(MOTOR_IDS.get(i));
            } catch (Exception e) {
                Toast.log().exception(e);
            }
        }

        if (MOTOR_COUNT != 4 && DRIVE_TYPE.toLowerCase().equals("mecanum"))
            throw new IllegalArgumentException("Mecanum requires 4 motors!");

        if (MOTOR_COUNT == 2) {
            drive = new RobotDrive(controllers[0], controllers[1]);
        } else if (MOTOR_COUNT == 4) {
            drive = new RobotDrive(controllers[0], controllers[1], controllers[2], controllers[3]);
        } else
            throw new IllegalArgumentException("Invalid Motor Count: " + MOTOR_COUNT);
    }

}
