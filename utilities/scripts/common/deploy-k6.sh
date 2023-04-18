#!/bin/sh

gum format --theme=pink "## Installing the k6 operator"

#validate if the controller-gen is installed
if ! command -v controller-gen &> /dev/null
then
    echo "controller-gen could not be found. Please install it before running this script"
    echo "run go install github.com/llparse/controller-gen@latest"
    exit
fi

#validate if the make is installed
if ! command -v make &> /dev/null
then
    echo "make could not be found. Please install it before running this script"
    echo "run sudo dnf install make"
    exit
fi

#get the current directory
dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

# moving to the deploy directory
cd $dir/../deploy_files

# clone the https://github.com/grafana/k6-operator repo if it doesn't exist
if [ ! -d k6-operator ]; then
  git clone https://github.com/grafana/k6-operator
fi


# moving to the k6-operator directory
cd k6-operator

# install the k6-operator
make deploy

gum format --theme=pink "## deploying the k6 crds"

gum format --theme=pink "### creating the config maps"
#oc create configmap crocodile-stress-test --from-file integration-run.js

gum format --theme=pink "### creating the sa"
oc create sa k6-runner
oc add-scc-to-user anyuid -z k6-runner

cd $dir

cp ./template-deploy/k6-crd-template.yaml $dir/../deploy_files/k6-crd.yaml

cd $dir/../deploy_files

#oc apply -f k6-crd.yaml

gum format --theme=pink "## deploy my custom grafana dashboard"