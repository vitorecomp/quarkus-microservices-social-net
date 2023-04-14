#deploy script on server
#!/bin/bash

clean=false

if [ "$2" == "clean" ]; then
    clean=true
else
    clean=false
fi

# test if gum is installed
if ! command -v gum &> /dev/null
then
    echo "gum could not be found. Please install gum to continue."
    echo "go install github.com/charmbracelet/gum@latest"
    exit
fi

gum style \
	--foreground 212 --border-foreground 212 --border double \
	--align center --width 50 --margin "1 2" --padding "2 4" \
	'Social Net installer' "Hey let's install the net!"

#test if the environment name is provided

name=$1

if [ -z "$name" ]; then
    name=$(gum choose "kubernetes" "openshift")
fi

# get this script path
dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# build the project
sh $dir/utilities/scripts/build/build.sh $clean

# deploy to the selected environment

if [ "$name" == "openshift" ]; then
    gum format --theme=pink "# Deploying to openshift"
    # look if the flag clean is setted
    if $clean; then
        sh $dir/utilities/scripts/openshift-deploy/clean.sh
    fi

    sh $dir/utilities/scripts/openshift-deploy/deploy.sh $clean
    exit 0
fi

if [ "$name" == "kubernetes" ]; then
    gum format --theme=pink "# Deploying to kubernetes"
    sh $dir/utilities/scripts/kubernetes-deploy/deploy.sh $clean
    exit 0
fi

echo "Invalid environment name provided. Please provide either openshift or kubernetes"