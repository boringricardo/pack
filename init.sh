#!/bin/bash

echo Hello, let\'s get you started\!

echo What is your project called\?

read project_name

echo Okay, I will rename all instances of \"pack\" to \"$project_name\".

echo Does that work for you\? [yes/no]

read confirm

# delete this script once done
# rm $0;
