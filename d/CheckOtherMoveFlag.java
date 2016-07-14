package simplerace.d;
import simplerace.*;
import java.lang.Math;

public class CheckOtherMoveFlag extends Object{
  
  public int Check(SensorModel inputs){
	int otherMoveFlag = 0;
	//自分,相手,次(以下1)の旗,次の次(以下2)の旗の場所;
	Vector2d myPosition           = new Vector2d(inputs.getPosition());
	Vector2d otherPosition        = new Vector2d(inputs.getOtherVehiclePosition());

	Vector2d nextFlagPosition     = new Vector2d(inputs.getNextWaypointPosition());
	Vector2d nextNextFlagPosition = new Vector2d(inputs.getNextNextWaypointPosition());
	
	//相手の速さ(絶対速度)
	Vector2d otherVelocity = new Vector2d(inputs.getOtherVehicleVelocity());

	//相手の位置と相手の速さから3点目の座標を取る
	Vector2d otherVelocityPosition = new Vector2d(otherPosition.x+otherVelocity.x,
												  otherPosition.y+otherVelocity.y);

	//1の旗,相手,速さの点の3点から余弦を求める
	double otherNextCarVelocityCos     = getCos( nextFlagPosition,
												 otherPosition,
												 otherVelocityPosition);
	//2の旗,相手,速さの点の3点から余弦を求める
	double otherNextnextCarVelocityCos = getCos( nextNextFlagPosition,
												 otherPosition,
												 otherVelocityPosition);

	double othertoNextDistance     = Math.hypot(otherPosition.x-nextFlagPosition.x,
												otherPosition.y-nextFlagPosition.y);
	double othertoNextnextDistance = Math.hypot(otherPosition.x-nextNextFlagPosition.x,
												otherPosition.y-nextNextFlagPosition.y);


	//2の旗と自分の距離,2の旗と相手の距離
	double myNextnextDistance    = Math.hypot( myPosition.x-nextFlagPosition.x,
											   myPosition.y-nextFlagPosition.y);
	  
	// System.out.println("1の旗へのCos " + otherNextCarVelocityCos);
	// System.out.println("2の旗へのCos " + otherNextnextCarVelocityCos);

	//1の旗へのベクトルの比重が大きい
	if(otherNextCarVelocityCos > otherNextnextCarVelocityCos){
	  otherMoveFlag = 1;
	}//2の旗へのベクトルの比重が大きい
	else{
	  otherMoveFlag = 2;
	}
	//	System.out.println("otherNextnextDistance " + othertoNextnextDistance);
	//相手の方が2の旗に近く,とても2の旗と相手の距離が近い
	if(myNextnextDistance>othertoNextnextDistance && othertoNextnextDistance < 30){
	  otherMoveFlag = 2;
	}
	
	return otherMoveFlag;
  }

  
  //入力された3点の余弦(∠ABCの余弦)を出す
  private double getCos(Vector2d A, Vector2d B, Vector2d C){
	double cos;
	double AB = Math.hypot((A.x-B.x),(A.y-B.y));
	double BC = Math.hypot((B.x-C.x),(B.y-C.y));
	double CA = Math.hypot((C.x-A.x),(C.y-A.y));
	cos = (AB*AB+BC*BC-CA*CA)/(2*AB*BC);
	
	return cos;
  }
  
}
