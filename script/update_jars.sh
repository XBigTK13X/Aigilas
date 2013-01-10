ls script/updated-lib | sed -e 's/\.[a-zA-Z]*$//' | xargs -I % ./script/install_jar.sh % $1
