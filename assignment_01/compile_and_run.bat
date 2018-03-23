@REM change working directory
@CD src

@REM compile
@CLS
@ECHO compiling objective (.o) files...
@g++ -std=c++11 -O2 *.cpp -c
@ECHO compiling main program...
@g++ -std=c++11 -O2 *.o -o ../bin/main
@ECHO done!

@REM run
@CD ../bin
@main
@PAUSE

@REM cleanup
@CD ../src
@DEL *.o
@CD ..
