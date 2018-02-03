package org.ilite.frc.robot.modules;


import org.ilite.frc.common.config.SystemSettings;
import org.ilite.frc.common.input.EInputScale;
import org.ilite.frc.common.types.ELogitech310;
import org.ilite.frc.robot.Data;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;

public class DriverControlSplitArcade implements IModule{

	
	private Joystick mGamepad;
	private Data mData;
	private Intake mIntake;
	private Elevator mElevator;
	
	private Object desiredValueLock = new Object();
	private double desiredLeftOutput, desiredRightOutput;
	private NeutralMode desiredNeutralMode;
	private ControlMode desiredControlMode; 
	private EInputScale inputScale = EInputScale.EXPONENTIAL;
	
	public DriverControlSplitArcade(Data pData, Intake pIntake, Elevator pElevator)
	{
		this.mGamepad = new Joystick(SystemSettings.kCONTROLLER_ID);
		this.mData = pData;
		this.mIntake = pIntake;
		this.mElevator = pElevator;
		this.desiredNeutralMode = NeutralMode.Brake;
		this.desiredControlMode = ControlMode.PercentOutput;
	}
	
	@Override
	public void initialize(double pNow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(double pNow) {
		updateDriveTrain();
		updateIntake();
		updateElevator();
		return false;
	}
	
	private void updateDriveTrain() {
	  mData.driverinput.set(ELogitech310.RUMBLE, 1.0);
	  
		double rotate = mData.driverinput.get(ELogitech310.LEFT_Y_AXIS);
		rotate = EInputScale.EXPONENTIAL.map(rotate, 2);
		
		double throttle1 = mData.driverinput.get(ELogitech310.LEFT_TRIGGER_AXIS);
		double throttle2 = mData.driverinput.get(ELogitech310.RIGHT_TRIGGER_AXIS);
		
		double throttle = (throttle1 + throttle2 ==2)? 1 : 0;
		desiredLeftOutput = throttle - rotate;
		desiredRightOutput = throttle + rotate;
		
		
		int leftScalar = desiredLeftOutput < 0 ? -1 : 1;
		int rightScalar = desiredRightOutput < 0 ? -1 : 1;
		desiredLeftOutput =  leftScalar * Math.min(Math.abs(desiredLeftOutput), 1);
		desiredRightOutput = rightScalar * Math.min(Math.abs(desiredRightOutput), 1);
		
		if(mData.driverinput.get(ELogitech310.RIGHT_TRIGGER_AXIS) > 0.5) {
		  desiredLeftOutput /= 3;
		  desiredRightOutput /= 3;
		}
	}
	
	private void updateIntake() {
		double intakeSpeed = mData.operator.get(ELogitech310.RIGHT_Y_AXIS);
		System.out.println("Intake Speed:" + intakeSpeed);
		if(mData.operator.get(ELogitech310.DPAD_UP) != null) {
			mIntake.extendIntake();
		} 
		else if(mData.operator.get(ELogitech310.DPAD_DOWN) != null) {
			mIntake.retractIntake();
		}
		if(intakeSpeed > 0) {
			mIntake.spinIn(intakeSpeed);
		} else {
			mIntake.spinOut(intakeSpeed);
		}
		
	}
	
	private void updateElevator() {
		
	}
	
	public void setDesiredLeftOutput(double desiredLeftOutput) {
    this.desiredLeftOutput = desiredLeftOutput;
  }

  public void setDesiredRightOutput(double desiredRightOutput) {
    this.desiredRightOutput = desiredRightOutput;
  }

  public void setDesiredNeutralMode(NeutralMode desiredNeutralMode) {
    this.desiredNeutralMode = desiredNeutralMode;
  }

  public void setDesiredControlMode(ControlMode desiredControlMode) {
    this.desiredControlMode = desiredControlMode;
  }

  public double getDesiredLeftOutput() {
		synchronized (desiredValueLock) {
			return desiredLeftOutput;
		}
	}
	
	public double getDesiredRightOutput() {
		synchronized(desiredValueLock) {
			return desiredRightOutput;
		}
	}
	
	public ControlMode getDesiredControlMode() {
		synchronized(desiredValueLock) {
			return desiredControlMode;
		}
	}
	
	public NeutralMode getDesiredNeutralMode() {
		synchronized(desiredValueLock) {
			return desiredNeutralMode;
		}
	}

	@Override
	public void shutdown(double pNow) {
		// TODO Auto-generated method stub
		
	}
	
	public void setInputScaling(EInputScale inputScale) {
		this.inputScale = inputScale;
	}
	
	public EInputScale getInputScaling() {
		return inputScale;
	}
}
