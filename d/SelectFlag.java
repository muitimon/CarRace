package simplerace.d;
import simplerace.*;
import java.lang.Math;
import java.awt.Point;

public class SelectFlag extends Object{
  public double[] Check(SensorModel inputs){
    double[] aim = new double[2];
    Vector2d myPosition = new Vector2d(inputs.getPosition());
    Vector2d otherPosition = new Vector2d(inputs.getOtherVehiclePosition());
    Vector2d nextFlagPosition = new Vector2d(inputs.getNextWaypointPosition());

    Vector2d myVelocity = inputs.getVelocity();
    Vector2d otherVelocity = inputs.getOtherVehicleVelocity();

    Vector2d myTekitoPosition = new Vector2d(myPosition.x+myVelocity.x, myPosition.y+myVelocity.y);
    Vector2d otherTekitoPosition = new Vector2d(otherPosition.x+otherVelocity.x, otherPosition.y+otherVelocity.y);

    double myNextToCarDistance, myCarToTekitoDistance, myTekitoToNextDistance;
    myNextToCarDistance = Math.hypot((nextFlagPosition.x-myPosition.x), (nextFlagPosition.y-myPosition.y));
    myCarToTekitoDistance = Math.hypot((myPosition.x-myTekitoPosition.x), (myPosition.y-myTekitoPosition.y));
    myTekitoToNextDistance = Math.hypot((myTekitoPosition.x-nextFlagPosition.x), (myTekitoPosition.y-nextFlagPosition.y));
    double nijou_myNextToCarDistance = myNextToCarDistance * myNextToCarDistance;
    double nijou_myCarToTekitoDistance = myCarToTekitoDistance * myCarToTekitoDistance;
    double nijou_myTekitoToNextDistance = myTekitoToNextDistance * myTekitoToNextDistance;

    double otherNextToCarDistance, otherCarToTekitoDistance, otherTekitoToNextDistance;
    otherNextToCarDistance = Math.hypot((nextFlagPosition.x-otherPosition.x), (nextFlagPosition.y-otherPosition.y));
    otherCarToTekitoDistance = Math.hypot((otherPosition.x-otherTekitoPosition.x), (otherPosition.y-otherTekitoPosition.y));
    otherTekitoToNextDistance = Math.hypot((otherTekitoPosition.x-nextFlagPosition.x), (otherTekitoPosition.y-nextFlagPosition.y));
    double nijou_otherNextToCarDistance = otherNextToCarDistance * otherNextToCarDistance;
    double nijou_otherCarToTekitoDistance = otherCarToTekitoDistance * otherCarToTekitoDistance;
    double nijou_otherTekitoToNextDistance = otherTekitoToNextDistance * otherTekitoToNextDistance;

    double myNeCaTeAngle = (nijou_myNextToCarDistance + nijou_myCarToTekitoDistance - nijou_myTekitoToNextDistance)/(2*myNextToCarDistance*myCarToTekitoDistance);
    double otherNeCaTeAngle = (nijou_otherNextToCarDistance + nijou_otherCarToTekitoDistance - nijou_otherTekitoToNextDistance)/(2*otherNextToCarDistance*otherCarToTekitoDistance);

    double myCheckVelocity = Math.hypot(myVelocity.x, myVelocity.y) * Math.cos(myNeCaTeAngle);
    double otherCheckVelocity = Math.hypot(otherVelocity.x, otherVelocity.y) * Math.cos(otherNeCaTeAngle);

    // 自分の方が旗に近い
    if (myNextToCarDistance < otherNextToCarDistance) {
      // 相手の方がそこまで早くない
      if (-10.0 < myCheckVelocity-otherCheckVelocity) {
        aim[0] = inputs.getAngleToNextWaypoint();
        aim[1] = inputs.getDistanceToNextWaypoint();
      }
      // 相手の方がかなり早い
      else {
        aim[0] = inputs.getAngleToNextNextWaypoint();
        aim[1] = inputs.getDistanceToNextNextWaypoint();
      }
    }
    // 相手の方が旗に近い
    else{
      // 相手よりかなり差をつけて早くなれない
      if (myCheckVelocity-otherCheckVelocity < 10.0) {
        aim[0] = inputs.getAngleToNextNextWaypoint();
        aim[1] = inputs.getDistanceToNextNextWaypoint();
      }
      // 自分の方がかなり早い
      else {
        aim[0] = inputs.getAngleToNextWaypoint();
        aim[1] = inputs.getDistanceToNextWaypoint();
      }
    }

    return aim;
  }
}
