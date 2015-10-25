# Universal Drive
The All in One RobotDrive framework for those who want an easy solution

## What is this
Universal Drive is a Toast Module that acts as a framework for the RobotDrive in WPILib. This module allows users to configure their driving preferences through Toast's Configuration Files, as seen below:
```groovy
// The type of Drive to use. Possible options are: [TANK, MECANUM, ARCADE]
drive.type = "MECANUM"

// The interface to use for the Motor Controllers. Possible values are: [PWM, CAN]
drive.interface = "PWM"

// The type of motor controller to use. Possible values are: [Talon, Jaguar, Victor, Victor SP]
drive.motor = "Talon"

// The motor ports to use. This is the PWM/CAN interface ID the motor controller is attached to.
// Pattern: [left, right] OR [front left, back left, front right, back right]
drive.ports = [0, 1, 2, 3]

// Get the JoyStick port to use for the Drive
joy.port = 0

// Defines the Control Layout of the Joystick. This is different per Drive Type.
// TANK: XBOX_STICK (analog L/R stick drive)
// MECANUM: XBOX_STICK (L analog stick for X/Y, R analog stick for rotation)
// ARCADE: XBOX_STICK (analog L stick drive)
joy.layout = "XBOX_STICK"
```

This configuration file allows for users to quickly and easily setup their driving configuration for their robot without any coding required. This can be extremely useful for spinning up a Drive Base really quickly, or for teams who don't have much coding knowledge and want to get the Robot Driving. Additionally, the project is open source, meaning teams can contribute their expertise in fine-tuning drive systems so the API is always at it's most stable.
