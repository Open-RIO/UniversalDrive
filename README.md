# Universal Drive
The All in One RobotDrive framework for those who want an easy solution

## What is this
Universal Drive is a Toast Module that acts as a framework for the RobotDrive in WPILib. This module allows users to configure their driving preferences through its configuration file (`toast/config/UniversalDrive.conf`), as seen below:
```json
{
	"joy":{
		"layout":"XBOX_STICK",
		"port":0
	},
	"drive":{
		"motor":"Talon",
		"type":"TANK",
		"ports":[0,1],
		"interface":"PWM",
		"squaredinputs":false
	}
}
```

Name | Description
--- | --- 
**`joy`** | **Joystick settings**
`layout` | Defines the Control Layout of the Joystick. This is different per Drive Type. <br> `TANK`: `XBOX_STICK` (analog L/R stick drive) <br> `MECANUM`: `XBOX_STICK` (L analog stick for X/Y, R analog stick for rotation) <br> `ARCADE`: `XBOX_STICK` (analog L stick drive)
`port` | Get the JoyStick port to use for the Drive
**`drive`** | **Drive settings**
`motor` | The type of motor controller to use. Possible values are: `"Talon"`, `"Jaguar"`, `"Victor"`, `"Victor SP"`
`type` | The type of Drive to use. Possible options are: `"TANK"`, `"MECANUM"`, `"ARCADE"`
`ports` | The motor ports to use. This is the PWM/CAN interface ID the motor controller is attached to. <br> Pattern: `[left, right]` or `[front left, back left, front right, back right]`
`interface` | The interface to use for the Motor Controllers. Possible values are: `"PWM"` or `"CAN"`
`squaredinputs` | Whether or not to use squared inputs on tank and arcade drive. Possible values are: `true` or `false`



This configuration file allows for users to quickly and easily setup their driving configuration for their robot without any coding required. This can be extremely useful for spinning up a Drive Base really quickly, or for teams who don't have much coding knowledge and want to get the Robot Driving. Additionally, the project is open source, meaning teams can contribute their expertise in fine-tuning drive systems so the API is always at it's most stable.
