#! /bin/sh

echo "== Preparing a working directory for the new package: pkg"
rm -rf pkg
rm -rf assets/graphics/sprites/.directory
mkdir pkg

echo "== Compiling the game"
mvn -q clean package

echo "== Copying resources to pkg/"
cp -r assets pkg/assets
cp -r dist/* pkg
cp target/aigilas-jar-with-dependencies.jar pkg/game.jar

git log --max-count=1 > pkg/assets/data/git.txt

