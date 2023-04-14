#!/bin/bash

# get the dir
dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
clean=$1

# build the user-service
gum format --theme=pink "# Building the user-service"
cd $dir/../../../user-service

if [ ! -d "./target" ] || $clean; then
    mvn clean package
else
    gum format --theme=pink "## The user-service is already built"
fi

# build the post-service
gum format --theme=pink "# Building the post-service"
cd $dir/../../../post-service
if [ ! -d "./target" ] || $clean; then
    mvn clean package
else
    gum format --theme=pink "## The post-service is already built"
fi


# build the feed-service
gum format --theme=pink "# Building the feed-service"
cd $dir/../../../feed-service
if [ ! -d "./target" ] || $clean; then
    mvn clean package
else
    gum format --theme=pink "## The feed-service is already built"
fi