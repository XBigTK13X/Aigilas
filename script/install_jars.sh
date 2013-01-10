ls lib | sed -e 's/\.[a-zA-Z]*$//' | xargs -I % ./install_jar.sh % $1
