#change working directory
cd src

#compile
clear
echo "compiling objective (.o) files..."
g++ -std=c++11 -O2 *.cpp -c
echo "compiling main program..."
g++ -std=c++11 -O2 *.o -o ../bin/main
echo "done!"

#run
cd ..
./bin/main

#cleanup
rm src/*.o
