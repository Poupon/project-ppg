package com.phipo.PPg.PPgSFX;



import java.awt.Color;

import com.phipo.PPg.PPgGame.*;
import com.phipo.PPg.PPgMath.PPgRandom;

//*************************************************

public interface ParticleFactoryInterface {

	public 	ParticleInterface newInstance( ParticleEngine pMyEngine, PPgRandom pRand, int lNum );

};

//*************************************************
