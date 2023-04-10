#!/bin/sh

#test if oc is installed
if ! command -v oc &> /dev/null
then
    echo "oc could not be found. Please install oc to continue."
    echo "https://docs.openshift.com/container-platform/4.7/cli_reference/openshift_cli/getting-started-cli.html"
    exit
fi

#test if is logged in
if ! oc whoami &> /dev/null
then
    echo "oc is not logged in. Please login to continue."
    echo "oc login"
    exit
fi

gum format --theme=pink "# Start the database installation process"

#test if the project social-database exists
if oc get project social-database &> /dev/null
then
    echo "social-database project already exist. Skypping postgres installation."
    echo "If you want to reinstall postgres, please delete the project social-database and run this script again."
    echo "oc delete project social-database"
    exit
fi

# get this script path
dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

source $dir/deploy-postgres.sh
mkdir -p $dir/../deploy_files

# generate the postgres operator yaml
gum format --theme=pink "## Generating the postgres operator yaml"
generate_operator_yaml $dir/template-files $dir/../deploy_files

# generate the postgres database yaml
gum format --theme=pink "## Generating the psotgres database yaml"
generate_database_yaml $dir/template-files $dir/../deploy_files



oc new-project social-database
oc apply -f $dir/../deploy_files/postgres-operator.yaml
oc apply -f $dir/../deploy_files/postgres-database.yaml
