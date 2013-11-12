all:: PPgRef.class PPgUtils.class PPgToken.class PPgIniFile.class PPgParam.class PPgXml.class PPgInterfaceXmlNodeHandler.class PPgXmlTag.class PPgLog.class

clean::
	rm *.class 

%.class: %.java
	javac   -classpath ../../../.. -deprecation -g  $*.java

