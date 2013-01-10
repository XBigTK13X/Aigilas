#! /bin/sh

echo "== Preparing a working directory for the new package: pkg"
rm -rf pkg
mkdir pkg

echo "== Compiling the game and the launcher"
mvn -q clean package

echo "== Copying resources"
git log --max-count=1 > pkg/version.txt
cp -r aigilas/assets ./pkg/assets
cp -r dist/* ./pkg
cp aigilas/target/aigilas-jar-with-dependencies.jar pkg/aigilas.jar
cp launcher/target/aigilas-launcher-jar-with-dependencies.jar pkg/launcher.jar

echo "== Creating the archive: pkg/aigilas.zip"
cd pkg
zip -rq aigilas.zip ./*
cd ..
