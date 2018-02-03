
package org.ilite.frc.robot.commands;

import org.ilite.frc.robot.modules.ElevatorModule;

public class ElevateToPosition implements ICommand{
	private ElevatorModule elevator;
	
	public ElevateToPosition (ElevatorModule pElevator) {
		elevator = pElevator;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
