#! /bin/bash

script/prepare_package.sh
cd $JENKINS_HOME/tools/conyay
/bin/bash -c "./power.sh Aigilas $WORKSPACE/pkg"
mv ./rap/Aigilas/packages $WORKSPACE/artifacts