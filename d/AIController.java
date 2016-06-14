package simplerace.d;
import simplerace.*;
import java.lang.Math;

import java.awt.*;
public class AIController implements Controller, Constants {

  public void reset() {}

  public int control (SensorModel inputs) {
	int command = neutral;
	command = AIController.NormalDrive(inputs);
	return command;
  }
  static DistanceNextNextnext distanceNextNextnext = new DistanceNextNextnext();
  static AngleCarNextNextnext angleCarNextNextnext = new AngleCarNextNextnext();
  static BasedCommand basedCommand = new BasedCommand();
  static private int turn = 3; //nextflagを取った場合の例外処理数
  private static Vector2d next;
  private static Vector2d nextnext;
  static double beforeDistance = 0;
   
  public static int NormalDrive(SensorModel inputs){
	int command = neutral;
	double distance = distanceNextNextnext.getDistance(inputs);
	double angle = angleCarNextNextnext.getAngle(inputs);
	
	if(next == null){
	  next = inputs.getNextWaypointPosition();
	  nextnext = inputs.getNextNextWaypointPosition();
	}
	next = inputs.getNextWaypointPosition();
	if(turn == 0){
	  if(next.x == nextnext.x && next.y == nextnext.y){
		turn = 3;
		nextnext = inputs.getNextNextWaypointPosition();
	  }
	}
	double theta = Math.PI;
    command = basedCommand.getCommand(inputs);
	command = getCommandNextDistance(command,inputs,false);
	if(beforeDistance < 140.0 || distance < 140.0){
	  if(turn > 0){
		command = getCommandNextDistance(command,inputs,true);
		turn--;
	  }
	}
	return command;
  }

  static public int getCommandNextDistance(int basedCommand,SensorModel inputs, boolean except){
	int command = basedCommand;
	int wayLevel = 0;
	double mySpeed = inputs.getSpeed();
	double nextwayangle = inputs.getAngleToNextWaypoint();
	double speed = 0;
	double nextwaydistance = inputs.getDistanceToNextWaypoint();
	int dev = 1;
	double angle = angleCarNextNextnext.getAngle(inputs);
	if(except){
	  if(0.45 < nextwaydistance && nextwaydistance < 0.7){
		wayLevel = 7;
		speed = 8;
		dev = 1;
	  }else if(0.3 < nextwaydistance && nextwaydistance < 0.45){
		wayLevel = 6;
		speed = 7;
		dev = 1;
	  }else if(0.2 < nextwaydistance && nextwaydistance < 0.3){
		wayLevel = 5;
		speed = 6;
		dev = 1;
	  }else if(0.15 < nextwaydistance && nextwaydistance < 0.2){
		wayLevel = 4;
		speed = 5;
		dev = 1;
	  }else if(0.1 < nextwaydistance && nextwaydistance < 0.15){
		wayLevel = 3;
		speed = 4;
		dev = 1;
	  }else if(0.05 < nextwaydistance && nextwaydistance <= 0.1){
		wayLevel = 2;
		speed = 2;
		dev = 1;
	  }else if(nextwaydistance < 0.05){
		wayLevel = 1;
		speed = 1;
		dev = 3;
	  }
	}else{
	  if(0.45 < nextwaydistance && nextwaydistance < 0.7){
		wayLevel = 7;
		speed = 8;
		dev = 1;
	  }else if(0.3 < nextwaydistance && nextwaydistance < 0.45){
		wayLevel = 6;
		speed = 7;
		dev = 1;
	  }else if(0.2 < nextwaydistance && nextwaydistance < 0.3){
		wayLevel = 5;
		speed = 6;
		dev = 1;
	  }else if(0.15 < nextwaydistance && nextwaydistance < 0.2){
		wayLevel = 4;
		speed = 5;
		dev = 1;
	  }else if(0.1 < nextwaydistance && nextwaydistance < 0.15){
		wayLevel = 3;
		speed = 3;
		dev = 1;
	  }else if(0.05 < nextwaydistance && nextwaydistance <= 0.1){
		wayLevel = 2;
		speed = 2;
		dev = 1;
	  }else if(nextwaydistance < 0.05){
		wayLevel = 1;
		speed = 1;
		dev = 3;
	  }
	}
	System.out.print(wayLevel);
	if(-Math.PI / dev < angle && angle < Math.PI / dev){
	  if(speed < mySpeed){
		//                command = backward;
		if(0 < nextwayangle){
		  command = backwardleft;
		}
		else{
		  command = backwardright;
		}
	  }
	  else if(mySpeed < -1*speed){
		//                command = forward;
		if(0 < nextwayangle){
		  command = forwardleft;
		}
		else{
		  command = forwardright;
		}
	  }
	}
	return command;
  }
}
