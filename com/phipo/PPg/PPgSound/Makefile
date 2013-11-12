all:: PPgWavePlayer.class PPgMidi.class
 
jar::
	cd ../../../..; \
	rm  com/caic/ors/OrsMonitor/OrsMonitor.jar; \
	jar cfm      com/caic/ors/OrsMonitor/OrsMonitor.jar  com/caic/ors/OrsMonitor/OrsMonitor.mf com/caic/ors/OrsMonitor/*.class ; \
	cd  com/caic/ors/OrsMonitor;
	jar -uf OrsMonitor.jar *.jpg\
	jar -uf OrsMonitor.jar icon \


clean::
	rm *.class 

%.class: %.java
	javac   -classpath ../../../.. -deprecation -g  $*.java
