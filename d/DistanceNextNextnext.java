package simplerace.d;
import simplerace.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

public class DistanceNextNextnext{

  public double getDistance(SensorModel inputs){
	double distance = 0;
	Vector2d next = inputs.getNextWaypointPosition();
	Point point = new Point(0,0);
	point.setLocation(next.x,next.y);
	Vector2d nextNext = inputs.getNextNextWaypointPosition();
	distance = point.distance(nextNext.x,nextNext.y);

    return distance;
  }
}
