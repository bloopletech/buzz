@del /Q com\buzz\*.class
javac -source 1.2 -target 1.2 com/buzz/Buzz.java
@del /Q buzz.jar
jar cmfv meta-inf/MANIFEST.MF buzz.jar com/buzz/*.class com/buzz/doc/* com/buzz/*.wav com/buzz/*.png
@pause