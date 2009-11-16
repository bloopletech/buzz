buzz.jar: com/buzz/*.java
	del /Q buzz.jar
	javac -source 1.2 -target 1.2 com/buzz/Buzz.java
	jar cmfv meta-inf/MANIFEST.MF buzz.jar com/buzz/*.class com/buzz/*.wav com/buzz/*.png

buzz-applet.jar:
	del /Q buzz-applet.jar
	javac -source 1.2 -target 1.2 com/buzz/BuzzApplet.java
	jar cmfv meta-inf/MANIFEST.MF buzz-applet.jar com/buzz/*.class com/buzz/*.wav com/buzz/*.png

all: buzz.jar buzz-applet.jar

