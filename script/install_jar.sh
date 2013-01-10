mvn install:install-file -DlocalRepositoryPath=lib -DcreateChecksum=true -Dpackaging=jar -Dfile=script/updated-lib/$1.jar -DgroupId=com.badlogic -DartifactId=$1 -Dversion=$2
