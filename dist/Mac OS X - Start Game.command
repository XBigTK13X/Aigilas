#! /bin/sh
#
cd "$(dirname "$0")"
echo "Launching Aigilas - Check aigilas.log and launcher.log for details."
java -jar launcher.jar > aigilas.log 2>&1
