package com.phipo.PPg.PPgSFX;


import java.awt.Color;

import com.phipo.PPg.PPgGame.*;

//*************************************************

public interface SwarmBoidFactory{

	public 	SwarmBoid newInstance( Swarm pMySwarm, double pX, double pY, Color pColor, int pUser1, double pUser2);

};

//*************************************************
