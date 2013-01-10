mvn install:install-file -DlocalRepositoryPath=maven-repo -DcreateChecksum=true -Dpackaging=jar -Dfile=lib/$1.jar -DgroupId=com.esotericsoftware -DartifactId=$1 -Dversion=$2
