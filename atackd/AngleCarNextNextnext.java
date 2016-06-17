package simplerace.d;
import simplerace.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AngleCarNextNextnext{

  public double getAngle(SensorModel inputs){
	double angle = 0;
	Vector2d car = inputs.getPosition();
	Vector2d next = inputs.getNextWaypointPosition();
	Vector2d nextNext = inputs.getNextNextWaypointPosition();

	double alphax = car.x - next.x;
	double alphay = car.y - next.y;
	double betax  = nextNext.x - next.x;
	double betay  = nextNext.y - next.y;
	
	double cos;
	cos = (alphax*betax + alphay*betay) /
	  Math.sqrt((alphax*alphax + alphay*alphay)*(betax*betax + betay*betay));

	angle = Math.acos(cos);
	
    return angle;
  }
}
