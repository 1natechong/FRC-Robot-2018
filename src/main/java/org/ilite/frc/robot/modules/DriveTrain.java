package org.ilite.frc.robot.modules;

import java.util.ArrayList;

import org.ilite.frc.common.config.SystemSettings;
import org.ilite.frc.common.types.EDriveTrain;
import org.ilite.frc.common.types.ELogitech310;
import org.ilite.frc.robot.Data;
//import org.usfirst.frc.team1885.robot.SystemSettings;
import org.ilite.frc.robot.controlloop.IControlLoop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.flybotix.hfr.util.log.ILog;
import com.flybotix.hfr.util.log.Logger;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 * Class for running all drive train control operations from both autonomous and
 * driver-control
 */
public class DriveTrain implements IControlLoop {
	//private final ILog mLog = Logger.createLog(DriveTrain.class);

	//private Solenoid gearShifter;
	private final TalonSRX mLeftMaster, mRightMaster, mLeftFollower, mRightFollower; /*leftFollower2, rightFollower2;*/
	private ControlMode mControlMode;
	private double mDesiredLeft, mDesiredRight;
	
	public DriveTrain(DriverControl driverControl)
	{
		this.driverControl = driverControl;
		//leftMaster = new TalonSRX(SystemSettings.kDRIVETRAIN_TALONID_LEFT1);
		mLeftMaster = TalonFactory.createDefault(SystemSettings.kDRIVETRAIN_TALONID_LEFT1);
		mRightMaster = TalonFactory.createDefault(SystemSettings.kDRIVETRAIN_TALONID_RIGHT1);
		mLeftFollower = TalonFactory.createDefault(SystemSettings.kDRIVETRAIN_TALONID_LEFT2);
		mRightFollower = TalonFactory.createDefault(SystemSettings.kDRIVETRAIN_TALONID_RIGHT2);
		//leftFollower2 = new TalonSRX(SystemSettings.DRIVETRAIN_TALONID_LEFT3);
		//rightFollower2 = new TalonSRX(SystemSettings.DRIVETRAIN_TALONID_RIGHT3);
		mRightFollower.follow(mRightMaster);
		//rightFollower2.follow(rightMaster);
		//leftFollower2.follow(leftMaster);
		mLeftFollower.follow(mLeftMaster);
		mControlMode = ControlMode.PercentOutput;


		}
	@Override
	public void initialize(double pNow) {
		mLeftMaster.set(mControlMode, mDesiredLeft);
		mRightMaster.set(mControlMode, mDesiredRight);
		
	}

	@Override
	public boolean update(double pNow) {
		//updateSpeed(desiredLeft, desiredRight);
		leftMaster.setNeutralMode(driverControl.getDesiredNeutralMode());
		rightMaster.setNeutralMode(driverControl.getDesiredNeutralMode());
		leftMaster.set(driverControl.getDesiredControlMode(), driverControl.getDesiredLeftOutput());
		rightMaster.set(driverControl.getDesiredControlMode(), driverControl.getDesiredRightOutput());
		System.out.printf("Left: %s Right: %s\n", desiredLeft, desiredRight);
		return false;
	}	
	
	/*private void updateSpeed(double l, double r)
	{
	
	}*/
	
	public void set(ControlMode pMode, double p_l, double p_r)
	{
		mDesiredLeft = p_l;
		mDesiredRight = p_r;
	}
	
	public void setPower(double p_l, double p_r) {
		set(ControlMode.PercentOutput, p_l, p_r);
	}
	
	@Override
	public void shutdown(double pNow) {
		mLeftMaster.neutralOutput();
		mRightMaster.neutralOutput();
		
	}
	
	public void changeModes(ControlMode mControlMode)
	{
		switch(mControlMode)
		{
		case Velocity:
			break;
		case PercentOutput:
			mDesiredLeft = 0;
			mDesiredRight = 0;
			break;
		case Current:	
			break;
		case Disabled:
			break;
		case Follower:
			break;
		case MotionMagic:
			break;
		case MotionMagicArc:
			break;
		case MotionProfile:
			break;
		case MotionProfileArc:
			break;
		case Position:
			break;
		default:
			break;
		}
	}
	@Override
	public void loop(double pNow) {
		leftMaster.setNeutralMode(driverControl.getDesiredNeutralMode());
		rightMaster.setNeutralMode(driverControl.getDesiredNeutralMode());
		leftMaster.set(driverControl.getDesiredControlMode(), driverControl.getDesiredLeftOutput());
		rightMaster.set(driverControl.getDesiredControlMode(), driverControl.getDesiredRightOutput());
	}
	
	public TalonSRX getMasterTalon(String mMaster)
	{
		switch(mMaster)
		{
		case "leftMaster":
			return mLeftMaster;
		case "rightMaster":
			return mRightMaster;
		default:
			return null;
		}
	}
	
	public TalonSRX getRightMaster()
	{return mRightMaster;}
	
	public TalonSRX getLeftMaster()
	{return mLeftMaster;}
	
	public void printDesiredSpeeds()
	{
		System.out.println("Desired Left Speed: " + getDesiredLeft() +"\nDesired Right Speed: " + getDesiredRight());
	}
	public double getDesiredLeft()
	{return mDesiredLeft;}
	
	public double getDesiredRight()
	{return mDesiredRight;}
}
	
	



	



  

	