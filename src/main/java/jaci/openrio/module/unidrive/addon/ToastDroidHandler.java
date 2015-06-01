package jaci.openrio.module.unidrive.addon;

import jaci.openrio.module.android.tile.Tile;
import jaci.openrio.module.android.tile.TileRegistry;
import jaci.openrio.module.unidrive.UDPreference;

/**
 * Hooks for the ToastDroid module. This allows for Android devices to view data about the Universal Drive
 * system easily. This class is only loaded if ToastDroid is in the ClassPath.
 *
 * @author Jaci
 */
public class ToastDroidHandler {

    static Tile tile;

    public static void toast_droid() {
        final String drive_type = capitalize(UDPreference.DRIVE_TYPE);
        final String motor_controller = capitalize(UDPreference.MOTOR_TYPE);
        final String motor_interface = UDPreference.MOTOR_INTERFACE.toUpperCase();
        final String motor_ids = motor_ids();

        tile = new Tile("universaldrive_module", "Universal Drive") {
            public String[] getSubtitles() {
                String[] str = new String[4];
                str[0] = "Drive Type: " + drive_type;
                str[1] = "Controller: " + motor_controller;
                str[2] = "Interfaces: " + motor_interface;
                str[3] = "Motor IDs: " + motor_ids;
                return str;
            }
        };
        TileRegistry.register(tile);
    }

    private static String motor_ids() {
        String[] str = new String[UDPreference.MOTOR_COUNT];
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(UDPreference.MOTOR_IDS.get(i));
        }
        return String.join(", ", str);
    }

    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.toLowerCase().substring(1);
    }

}
