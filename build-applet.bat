javac -source 1.2 -target 1.2 com/buzz/BuzzApplet.java
@del /Q buzz-applet.jar
jar cmfv meta-inf/MANIFEST.MF buzz-applet.jar com/buzz/*.class com/buzz/*.wav com/buzz/*.png
@del /Q com\buzz\*.class
@pause