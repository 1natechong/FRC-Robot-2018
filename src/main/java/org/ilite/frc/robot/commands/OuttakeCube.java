package org.ilite.frc.robot.commands;

import org.ilite.frc.robot.modules.Intake;

public class OuttakeCube implements ICommand {
	private final Intake intake;
	private double duration;
	private double startTime;
	private boolean firstTime;
	
	public OuttakeCube(Intake intake, double duration) {
		this.intake = intake;
		this.duration = duration;
		firstTime = true;
	}
	@Override
	public void initialize() {
		

	}

	@Override
	public boolean update() {
		if(firstTime) {
	    firstTime = false;
		startTime = System.currentTimeMillis();
		}
		
		if(!(System.currentTimeMillis() - startTime > duration)) {
			intake.intakeOut(1);
			return false;
		}
		intake.intakeOut(0);
		return true;
	}
	
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
