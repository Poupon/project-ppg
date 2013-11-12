all::  Game3d.class MyWindows.class

LWGL_JAR=${LWJGL_PATH}/jar/lwjgl.jar:${LWJGL_PATH}/jar/lwjgl_util.jar:${LWJGL_PATH}/jar/jinput.jar:${LWJGL_PATH}/jar/lwjgl-debug.jar:${LWJGL_PATH}/jar/TWL.jar

EXT_PATH=/home/team02/phipo/DEV/JavaUtils/JTest/src/NNew/com/phipo/PPg/external-libs

EXT_JAR=${EXT_PATH}/xpp3-1.1.4c.jar:${EXT_PATH}/nifty-lwjgl-renderer-1.4.0-20120121.185346-1.jar:${EXT_PATH}/nifty-1.4.0-20120121.184741-1.jar


clean::
	rm *.class 

%.class: %.java
	javac   -classpath .:../../../..:${LWGL_JAR}:${EXT_JAR}  -deprecation -g  $*.java

