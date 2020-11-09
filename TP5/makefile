JAVACLASS = Toto
CPPCLASS = Tata

display:
	echo @1

make-java: 
	javac -d bin -sourcepath src src/*.java 2> /tmp/TP5.txt

run-java: make-java
	java -classpath bin Main $(JAVACLASS) $(CPPCLASS) 2> /tmp/TP5.txt

make-cpp: run-java
	g++ cpp/$(CPPCLASS).hpp -o cpp/$(CPPCLASS).o

clean: 
	rm -rf cpp/*
	rm -rf bin/*
