#! /bin/bash

script/prepare_package.sh
cd $JENKINS_HOME/tools/conyay
/bin/bash -c "./power.sh Aigilas $WORKSPACE/pkg"
rm -rf $WORKSPACE/artifacts || true
mv ./rap/Aigilas/packages $WORKSPACE/artifacts

push(){
	ZIP=$1
	CHANNEL=$2
	butler push $WORKSPACE/artifacts/$ZIP simplepathstudios/aigilas:$CHANNEL --userversion-file $WORKSPACE/assets/data/version.dat
}

push Aigilas-lin-64.zip linux
push Aigilas-lin-32.zip linux-thirytwobit
push Aigilas-mac.zip osx
push Aigilas-win-32.zip windows