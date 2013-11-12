all::  Turret.class Ship.class ShipMaker.class Asteroid.class Explosion.class SpritePilot.class LevelTest.class FactoryActor.class FactoryLevel.class LevelBase.class HumanControl.class PPgGame3d.class Weapon.class SpriteType.class Sprite.class Bonus.class SkyDomeStar.class


# JOGAMP_JAR=${JOGAMP_PATH}/jar/joal-natives-linux-i586.jar:${JOGAMP_PATH}/jar/gluegen-rt-natives-linux-i586.jar:${JOGAMP_PATH}/jar/jocl-natives-solaris-i586.jar:${JOGAMP_PATH}/jar/lwjgl-debug.jar:${JOGAMP_PATH}/jar/jogl-all-natives-linux-i586.jar

JOGAMP_JAR\
=${JOGAMP_PATH}/jar/joal.jar:${JOGAMP_PATH}/jar/joal-natives-linux-i586.jar:${JOGAMP_PATH}/jar/jocl.jar:${JOGAMP_PATH}/jar/jocl-natives-solaris-i586.jar\:${JOGAMP_PATH}/jar/jogl-all.jar:${JOGAMP_PATH}/jar/jogl-all-natives-linux-i586.jar:${JOGAMP_PATH}/jar/gluegen-rt.jar:${JOGAMP_PATH}/jar/gluegen-rt-natives-linux-i586.jar

jar::
	cd ../../../..; \
	rm  com/phipo/PPg/PPgGame3d/PPgGame3d.jar ; \
	jar cfmv com/phipo/PPg/PPgGame3d/PPgGame3d.jar com/phipo/PPg/PPgGame3d/manifest.mf; \
	jar uf  com/phipo/PPg/PPgGame3d/PPgGame3d.jar  com/phipo/PPg/PPgJ3d/*.class com/phipo/PPg/PPgGame3d/*.class  com/phipo/PPg/PPgUtils/*.class  com/phipo/PPg/PPgMath/*.class;




zip::
	rm  -rf  PPgGame3d;\
	mkdir PPgGame3d; \
	mkdir PPgGame3d/jogamp; \
	mkdir PPgGame3d/jogamp/jar;\
  cp com/phipo/PPg/PPgGame3d/PPgGame3d.jar PPgGame3d; \
  cp ${JOGAMP_PATH}/jar/*.jar PPgGame3d/jogamp/jar ;\
	cp PPgGame3d.jar *.ico *.png  go go.bat gojar *.lnk PPgGame3d; \
	zip -9 -r PPgGame3d PPgGame3d ; \


clean::
	rm *.class 

%.class: %.java
	javac   -classpath ../../../..:${JOGAMP_JAR}  -deprecation -g  $*.java

