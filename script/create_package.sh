#! /bin/sh
#
cd aigilas
mvn -q clean
mvn -q package
cd target
git log --max-count=1 > version.txt
ln ../../dist/README.txt ./README.txt
cp -r ../assets .
cp "../../dist/linux-start-game.sh" .
cp "../../dist/Windows - Start Game.bat" .
cp "../../dist/Mac OS X - Start Game.command" .

cd ../../launcher
mvn -q clean
mvn -q package
cd ../aigilas/target
cp ../../launcher/target/aigilas-launcher.jar .

zip -r aigilas.zip aigilas-launcher.jar aigilas.jar assets version.txt README.txt linux-start-game.sh "Windows - Start Game.bat" "Mac OS X - Start Game.command"
cd ../..
