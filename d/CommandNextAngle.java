package simplerace.d;
import simplerace.*;
import java.lang.Math;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CommandNextAngle{

  public final static int backwardleft = 0;
  public final static int backward = 1;
  public final static int backwardright = 2;
  public final static int left = 3;
  public final static int neutral = 4;
  public final static int right = 5;
  public final static int forwardleft = 6;
  public final static int forward = 7;
  public final static int forwardright = 8;
  public int getCommand(int beforeCommand, SensorModel inputs, int turn){
	int command = beforeCommand;
	double speed = inputs.getSpeed();
	double nextwayangle = inputs.getAngleToNextWaypoint();
	double nextwaydistance = inputs.getDistanceToNextWaypoint();
	//System.out.println(nextwayangle);
	if(speed > 0.8){
	  //            -60 < theta < -45
	  if((-1*Math.PI/3) < nextwayangle && nextwayangle < (-1*Math.PI*50/360)){
		command = right;
	  }
	  //            45 <= theta < 60
	  else if(Math.PI*50/360 < nextwayangle && nextwayangle < Math.PI/3){
		command = left;
	  }
	  else{
		turn = 0;
	  }
	}
	// else{
	//   //            120 <= theta < 135
	//   if(Math.PI*2/3 < nextwayangle && nextwayangle < Math.PI*3/4){
	// 	command = forwardright;
	//   }
	//   //            -135 <= theta < -120
	//   else if(-1*Math.PI*3/4 < nextwayangle && nextwayangle < -1*Math.PI*2/3){
	// 	command = forwardleft;
	//   }
	//   else{
	// 	turn = 0;
	//   }
	// }
  	return command;
  }
}
