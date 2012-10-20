#! /bin/sh
#
cd aigilas
mvn clean
mvn package
cd target
git log --max-count=1 > version.txt
ln ../../dist/README.txt ./README.txt
cp -r ../assets .
rm aigilas.jar.zip
zip -r aigilas.jar.zip aigilas.jar assets version.txt README.txt
cd ../..
