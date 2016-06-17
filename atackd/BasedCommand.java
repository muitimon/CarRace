package simplerace.d;
import simplerace.*;
import java.lang.Math;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BasedCommand{

public final static int backwardleft = 0;
public final static int backward = 1;
public final static int backwardright = 2;
public final static int left = 3;
public final static int neutral = 4;
public final static int right = 5;
public final static int forwardleft = 6;
public final static int forward = 7;
public final static int forwardright = 8;
  public int getCommand(SensorModel inputs){
	int command = neutral;
      double speed = inputs.getSpeed();
	double nextwayangle = inputs.getAngleToNextWaypoint();
	double nextwaydistance = inputs.getDistanceToNextWaypoint();
	double otherAngle = inputs.getAngleToOtherVehicle();
	
	if(otherAngle < 0){
	  command = forwardright;
	}
	else{
	  command = forwardleft;
	}

  	return command;
  }
}
