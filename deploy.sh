#deploy script on server
#!/bin/bash

name = $1

if [ -z "$name" ]; then
    echo "Please provide the name of the environment to deploy to."
    exit 1
fi

# get this script path
dir = $( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# build the project
sh $dir/utilities/scripts/build/build.sh

# deploy to the selected environment

if [ eq "$name" "openshift" ]; then
    echo "Deploying to openshift"
    sh $dir/utilities/scripts/openshift-deploy/deploy.sh
    exit 0
fi

if [ eq "$name" "kubernetes" ]; then
    echo "Deploying to heroku"
    sh $dir/utilities/scripts/kubernetes-deploy/deploy.sh
    exit 0
fi