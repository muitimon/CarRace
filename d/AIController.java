package simplerace.d;
import simplerace.*;
import java.lang.Math;

public class AIController implements Controller, Constants {
    
    public void reset() {}
    
    public int control (SensorModel inputs) {
        int command = neutral;
        
        command = AIController.NormalDrive(inputs);
        
        //        System.out.print(command);
        return command;
    }
    
    public static int NormalDrive(SensorModel inputs){
        int command = neutral;
        double speed = inputs.getSpeed();
        double nextwayangle = inputs.getAngleToNextWaypoint();
        double nextwaydistance = inputs.getDistanceToNextWaypoint();
        
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
        else{
            //            theta = 180,-180
            if(nextwayangle == Math.PI || nextwayangle == -1*Math.PI){
                command = backward;
            }
            //            -180 < theta <= -120
            else if((-1*Math.PI) < nextwayangle && nextwayangle <= (-1*Math.PI*(2/3))){
                command = backwardright;
            }
            //            -120 < theta < -60
            else if((-1*Math.PI*(2/3)) <= nextwayangle && nextwayangle < (-1*Math.PI/3)){
                command = right;
            }
            //            -60 <= theta <0
            else if((-1*Math.PI/3) <= nextwayangle && nextwayangle < 0){
                command = forwardright;
            }
            //            theta =  0
            else if(nextwayangle == 0){
                command = forward;
            }
            //            0 < theta <= 60
            else if(0 < nextwayangle && nextwayangle <= (Math.PI/3)) {
                command = forwardleft;
            }
            //            60 < theta < 120
            else if((Math.PI/3) < nextwayangle && nextwayangle < (Math.PI*(2/3))) {
                command = left;
            }
            //            120 <= theta < 180
            else {
                command = backwardleft;
            }
        }
        
        if(0.1 < nextwaydistance && nextwaydistance < 0.15){
            //            System.out.println(nextwaydistance);
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
            //            System.out.println(nextwaydistance);
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
        }
        else if(nextwaydistance < 0.05){
            //            System.out.println(nextwaydistance);
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
        
        
        return command;
    }
    
}
