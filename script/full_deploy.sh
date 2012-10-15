#! /bin/sh
#
cd aigilas
mvn clean
mvn package
cd target
git log --max-count=1 > version.txt
zip -r aigilas.jar.zip aigilas.jar assets version.txt
cd ../..
python script/deploy-package.py

