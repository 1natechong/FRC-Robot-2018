package org.ilite.frc.robot.commands;

import org.ilite.frc.robot.modules.Intake;

public class IntakeCube implements ICommand {
	private final Intake intake;
    public IntakeCube(Intake intake) {
    	this.intake = intake;
    	
    }
    
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update() {
		if(!intake.limitSwitch()) { // if it is switched
			intake.intakeIn(1);
			return false;
		}
		intake.intakeIn(0);
		return true;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
