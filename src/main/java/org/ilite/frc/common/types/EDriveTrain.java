package org.ilite.frc.common.types;

import com.flybotix.hfr.codex.CodexOf;

public enum EDriveTrain implements CodexOf<Double> {
  TIME_SECONDS,
  DESIRED_LEFT_POWER,
  DESIRED_RIGHT_POWER,
  VOLTAGE_RAMP_RATE,
  LEFT_POSITION_ROT,
  RIGHT_POSITION_ROT,
  LEFT_VELOCITY_RPM,
  RIGHT_VELOCITY_RPM,
  IS_SHIFT,
  
  OPEN_LOOP_THROTTLE,
  OPEN_LOOP_TURN,
  OPEN_LOOP_CALC_LEFT_POWER,
  OPEN_LOOP_CALC_RIGHT_POWER,
  
  LEFT_TALON_MASTER_VOLTAGE,
  LEFT_TALON_FOLLOW_VOLTAGE,
  LEFT_TALON_MASTER_CURRENT,
  LEFT_TALON_FOLLOW_CURRENT,
  RIGHT_TALON_MASTER_VOLTAGE,
  RIGHT_TALON_FOLLOW_VOLTAGE,
  RIGHT_TALON_MASTER_CURRENT,
  RIGHT_TALON_FOLLOW_CURRENT,
  
  TALON_VBUS,
  TALON_MODE,
}
