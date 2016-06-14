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
	int command = 0;
      double speed = inputs.getSpeed();
	double nextwayangle = inputs.getAngleToNextWaypoint();
	double nextwaydistance = inputs.getDistanceToNextWaypoint();
	
	if(-6.0 < speed && speed < 6.0){
   
	  if(nextwayangle == Math.PI || nextwayangle == -1*Math.PI){
		command = backward;
	  }
	  //            -180 < theta < -90
	  if((-1*Math.PI) < nextwayangle && nextwayangle < (-1*Math.PI/2)){
		command = backwardright;
	  }
	  //            -90 <= theta < 0
	  else if((-1*Math.PI/2) <= nextwayangle && nextwayangle < 0){
		command = forwardright;
	  }
	  //            theta = 0
	  else if(nextwayangle == 0){
		command = forward;
	  }
	  //            0 < theta <= 90
	  else if(0 < nextwayangle && nextwayangle <= (Math.PI/2)) {
		command = forwardleft;
	  }
	  //            90 < theta < -180
	  else {
		command = backwardleft;
	  }
	}else if(6 < speed) {
	  //            theta = 180,-180
	  // if(nextwayangle == Math.PI || nextwayangle == -1*Math.PI){
	  //   command = backward;
	  // }
	  //            -180 < theta <= -120
	  if((-1*Math.PI) <= nextwayangle && nextwayangle <= (-1*Math.PI*(1/4))){
		command = backwardright;
	  }
	  //            -120 < theta < -60
	  // else if((-1*Math.PI*(3/4)) <= nextwayangle && nextwayangle < (-1*Math.PI/4)){
	  //   command = right;
	  // }
	  //            -60 <= theta <0
	  else if((-1*Math.PI/4) <= nextwayangle && nextwayangle < 0){
		command = forwardright;
	  }
	  //            theta =  0
	  else if(nextwayangle == 0){
		command = forward;
	  }
	  //            0 < theta <= 60
	  else if(0 < nextwayangle && nextwayangle <= (Math.PI/4)) {
		command = forwardleft;
	  }
	  //            60 < theta < 120
	  // else if((Math.PI/4) < nextwayangle && nextwayangle < (Math.PI*(3/4))) {
	  //   command = left;
	  // }
	  //            120 <= theta < 180
	  else {
		command = backwardleft;
	  }
	}
	else if(speed < -6) {
	  //            theta = 180,-180
	  if(nextwayangle == Math.PI || nextwayangle == -1*Math.PI){
		command = backward;
	  }
	  //            -180 < theta <= -120
	  else if((-1*Math.PI) <= nextwayangle && nextwayangle <= (-1*Math.PI*(3/4))){
		command = backwardright;
	  }
	  //            -120 < theta < -60
	  // else if((-1*Math.PI*(3/4)) <= nextwayangle && nextwayangle < (-1*Math.PI/4)){
	  //   command = right;
	  // }
	  //            -60 <= theta <0
	  else if((-1*Math.PI*(3/4)) <= nextwayangle && nextwayangle < 0){
		command = forwardright;
	  }
	  //            theta =  0
	  else if(nextwayangle == 0){
		command = forward;
	  }
	  //            0 < theta <= 60
	  else if(0 < nextwayangle && nextwayangle <= (Math.PI*(3/4))) {
		command = forwardleft;
	  }
	  //            60 < theta < 120
	  // else if((Math.PI/4) < nextwayangle && nextwayangle < (Math.PI*(3/4))) {
	  //   command = left;
	  // }
	  //            120 <= theta < 180
	  else {
		command = backwardleft;
	  }
	}
    
  	return command;
  }
}
