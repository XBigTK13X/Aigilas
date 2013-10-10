#! /bin/bash
echo $1 > assets/data/version.dat
git commit -am "Bump version to the latest release: $1"
git tag -a release/$1 -m '$1 release'
git push
git push origin release/$1
