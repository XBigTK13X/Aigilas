#! /bin/sh
#
cd aigilas
mvn -q clean
mvn -q package
cd target
git log --max-count=1 > version.txt
ln ../../dist/README.txt ./README.txt
cp -r ../assets .
cp ../../script/start_game.sh .
zip -r aigilas.zip aigilas.jar assets version.txt README.txt start_game.sh
cd ../..
