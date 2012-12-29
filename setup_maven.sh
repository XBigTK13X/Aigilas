cp ../sps-gamelib/target/sps-gamelib.jar ./aigilas/lib/sps-gamelib.jar
mvn install:install-file -Dfile=aigilas/lib/sps-gamelib.jar -DgroupId=com.simplepathstudios -DartifactId=sps-gamelib -Dversion=0.0.1 -Dpackaging=jar 
