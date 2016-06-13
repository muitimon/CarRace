package simplerace.d;
import simplerace.*;
import java.lang.Math;

import java.awt.*;
public class AIController implements Controller, Constants {

  public void reset() {}

    public int control (SensorModel inputs) {
      int command = neutral;

      command = AIController.NormalDrive(inputs);

      //        System.out.print(command);
      return command;
    }
  static double beforeDistance = 0;
  static DistanceNextNextnext distanceNextNextnext = new DistanceNextNextnext();
  static AngleCarNextNextnext angleCarNextNextnext = new AngleCarNextNextnext();
    static private int turn = 3; //nextflagを取った場合の例外処理数
    private static Vector2d next;
    private static Vector2d nextnext;
    
    public static int NormalDrive(SensorModel inputs){
      int command = neutral;
      double speed = inputs.getSpeed();
      double nextwayangle = inputs.getAngleToNextWaypoint();
      double nextwaydistance = inputs.getDistanceToNextWaypoint();
	  double distance = 0.0;
	  distance = distanceNextNextnext.getDistance(inputs);
        
        if(next == null){
                //System.out.println("next = null");
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
        
        
	  System.out.println(distance);
	  double angle = angleCarNextNextnext.getAngle(inputs);

      if(-6.0 < speed && speed < 6.0){
        //            theta = 180,-180
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
      }
      else if(6 < speed) {
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



      // double range = 2;
      // double nextwaydistanceRange = 0.15 + Math.pow(1.5, range);
      // double nextwaydistanceRangeNext= 0.15 + Math.pow(1.5, range+1);
      if(0.45 < nextwaydistance && nextwaydistance < 0.7){
        System.out.print(7);
        if(8< speed){
          //                command = backward;
          if(0 < nextwayangle){
            command = backwardleft;
          }
          else{
            command = backwardright;
          }
        }
        else if(speed < -8){
          //                command = forward;
          if(0 < nextwayangle){
            command = forwardleft;
          }
          else{
            command = forwardright;
          }
        }
      }
      else if(0.3 < nextwaydistance && nextwaydistance < 0.45){
        System.out.print(6);
        if(7 < speed){
          //                command = backward;
          if(0 < nextwayangle){
            command = backwardleft;
          }
          else{
            command = backwardright;
          }
        }
        else if(speed < -7){
          //                command = forward;
          if(0 < nextwayangle){
            command = forwardleft;
          }
          else{
            command = forwardright;
          }
        }
      }
      else if(0.2 < nextwaydistance && nextwaydistance < 0.3){
        System.out.print(5);
        if(6 < speed){
          //                command = backward;
          if(0 < nextwayangle){
            command = backwardleft;
          }
          else{
            command = backwardright;
          }
        }
        else if(speed < -6){
          //                command = forward;
          if(0 < nextwayangle){
            command = forwardleft;
          }
          else{
            command = forwardright;
          }
        }
      }
      // range--;
      // nextwaydistanceRange = 0.15 + Math.pow(1.5, range);
      // nextwaydistanceRangeNext= 0.15 + Math.pow(1.5, range+1);
      else if(0.15 < nextwaydistance && nextwaydistance < 0.2){
        System.out.print(4);
        if(5 < speed){
          //                command = backward;
          if(0 < nextwayangle){
            command = backwardleft;
          }
          else{
            command = backwardright;
          }
        }
        else if(speed < -5){
          //                command = forward;
          if(0 < nextwayangle){
            command = forwardleft;
          }
          else{
            command = forwardright;
          }
        }
      }
      //--------------------------------------------------------------------------------------

      else if(0.1 < nextwaydistance && nextwaydistance < 0.15){
        System.out.print(3);
        if(4 < speed){
          //                command = backward;
          if(0 < nextwayangle){
            command = backwardleft;
          }
          else{
            command = backwardright;
          }
        }
        else if(speed < -4){
          //                command = forward;
          if(0 < nextwayangle){
            command = forwardleft;
          }
          else{
            command = forwardright;
          }
        }
      }
      else if(0.05 < nextwaydistance && nextwaydistance <= 0.1){
        System.out.print(2);
        // if(-Math.PI / 3 < kanegaengle.getAngle(inputs) && kanegaengle.getAngle(inputs) < Math.PI / 3){
          if(2 < speed){
            //                command = backward;
            if(0 < nextwayangle){
              command = backwardleft;
            }
            else{
              command = backwardright;
            }
          }
          else if(speed < -2){
            //                command = forward;
            if(0 < nextwayangle){
              command = forwardleft;
            }
            else{
              command = forwardright;
            }
          }
        // }
      }
      else if(nextwaydistance < 0.05){
        System.out.print(1);
        if(-Math.PI / 3 < angle && angle < Math.PI / 3){
          if(1 < speed){
            //                command = backward;
            if(0 < nextwayangle){
              command = backwardleft;
            }
            else{
              command = backwardright;
            }
          }
          else if(speed < -1){
            //                command = forward;
            if(0 < nextwayangle){
              command = forwardleft;
            }
            else{
              command = forwardright;
            }
          }
        }
		
		beforeDistance = distance;
      }
	  //例外処理
	  if(beforeDistance < 140.0 || distance < 140.0){
		if(0.1 < nextwaydistance && nextwaydistance < 0.15){
            if(turn > 0){
                if(-Math.PI / 3 < angle && angle < Math.PI / 3){
                    if(3 < speed){
                            //                command = backward;
                        if(0 < nextwayangle){
                            command = backwardleft;
                        }
                        else{
                            command = backwardright;
                        }
                    }
                    else if(speed < -1){
                            //                command = forward;
                        if(-3 < nextwayangle){
                            command = forwardleft;
                        }
                        else{
                            command = forwardright;
                        }
                    }
                }
                turn--;
                    //System.out.println("turn--");
            }
		}
	  }
	  System.out.println(" " + distance + " ");
      return command;
  }


}
