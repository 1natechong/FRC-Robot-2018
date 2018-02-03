package org.ilite.frc.robot.commands;

import org.ilite.frc.robot.modules.Intake;

public class OuttakeCube implements ICommand {
	private final Intake intake;
	
	public OuttakeCube(Intake intake) {
		this.intake = intake;
	
	
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update() {
		
		return false;
	}
	
	
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
