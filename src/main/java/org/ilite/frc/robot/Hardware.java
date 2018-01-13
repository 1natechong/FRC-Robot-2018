package org.ilite.frc.robot;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import com.ctre.CANTalon;
import com.flybotix.hfr.util.log.ILog;
import com.flybotix.hfr.util.log.Logger;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Hardware {
  private ILog mLog = Logger.createLog(Hardware.class);

  private Joystick mDriverJoystick;
  private Joystick mOperatorJoystick;
  private PowerDistributionPanel mPDP;
  private AHRS mAHRS;
  public final AtomicBoolean mNavxReady = new AtomicBoolean(false);
  private CANTalon[] mTalons;
  
  Hardware() {
    
  }
  
  void init(
      Executor pInitializationPool,
      Joystick pDriverJoystick,
      Joystick pOperatorJoystick,
      PowerDistributionPanel pPDP,
      AHRS pAHRS,
      CANTalon... pTalons
  ) {
    mDriverJoystick = pDriverJoystick;
    mOperatorJoystick = pOperatorJoystick;
    mPDP = pPDP;
    mAHRS = pAHRS;
    mTalons = pTalons;

    pInitializationPool.execute(() -> {
      while(mAHRS.isCalibrating()) {
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      mNavxReady.set(true);
      mLog.info(System.currentTimeMillis() + " NAVX Calibrated");
    });
  }
  
  public Joystick getDriverJoystick() { 
    return mDriverJoystick;
  }
  
  public Joystick getOperatorJoystick() {
    return mOperatorJoystick;
  }
  
  public PowerDistributionPanel getPDP() {
    return mPDP;
  }
  
  public boolean isNavXReady() {
    return mNavxReady.get();
  }
  
  public AHRS getNavX() {
    return mAHRS;
  }
  
  public CANTalon[] getTalons() {
    return mTalons;
  }
  
  /**
   * Returns a talon by it's (presumably address).
   * @param pAddress
   * @return
   */
  public CANTalon getTalon(int pAddress) {
    return mTalons[pAddress];
  }
}