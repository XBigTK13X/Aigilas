#! /bin/sh
#
cd aigilas
mvn -q clean
mvn -q package
cd target
git log --max-count=1 > version.txt
ln ../../dist/README.txt ./README.txt
cp -r ../assets .
cp ../../dist/Start\ Aigilas\ \(Linux\).sh .
cp ../../dist/Start\ Aigilas\ \(Windows\).bat .
cp ../../dist/Start\ Aigilas\ \(Mac\ OS\ X\).command .
zip -r aigilas.zip aigilas.jar assets version.txt README.txt Start\ Aigilas\ \(Linux\).sh Start\ Aigilas\ \(Windows\).bat Start\ Aigilas\ \(Mac\ OS\ X\).command
cd ../..
