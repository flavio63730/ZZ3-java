JAVACLASS = Toto
CPPCLASS = Tata
OUT = ""

make-java: 
	javac -d bin -sourcepath src src/*.java

run-java: make-java
	java -classpath bin Main $(JAVACLASS) $(CPPCLASS) $(OUT)

make-cpp: run-java
	g++ cpp/$(CPPCLASS).hpp -o cpp/$(CPPCLASS).o

clean: 
	rm -rf cpp/*
	rm -rf bin/*
