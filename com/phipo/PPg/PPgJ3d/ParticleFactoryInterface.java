package com.phipo.PPg.PPgJ3d;



import com.phipo.PPg.PPgGame.*;
import com.phipo.PPg.PPgMath.PPgRandom;


import javax.media.opengl.GL2;
import javax.media.opengl.*;

//*************************************************

public interface ParticleFactoryInterface {

	public 	ParticleInterface newInstance( ParticleEngine pMyEngine, PPgRandom pRand, int lNum );

};

//*************************************************
