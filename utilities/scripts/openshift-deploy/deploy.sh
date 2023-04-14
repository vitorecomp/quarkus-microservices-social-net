#!/bin/sh

clean=$1

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

# get this script path
dir=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
mkdir -p $dir/../deploy_files



#test if the project social-database exists
if oc get project social-database &> /dev/null;
then
    echo "social-database project already exist. Finishing the installation process."
    echo "If you want to reinstall postgres, please delete the project social-database and run this script again."
    echo "oc delete project social-database"
    if $clean; then
        exit
    fi
else 

    gum format --theme=pink "## Start the database installation process"

    source $dir/deploy-postgres.sh

    # generate the postgres operator yaml
    gum format --theme=pink "## Generating the postgres operator yaml"
    generate_operator_yaml $dir/template-files $dir/../deploy_files

    # generate the postgres database yaml
    gum format --theme=pink "## Generating the postgres database yaml"
    generate_database_yaml $dir/template-files $dir/../deploy_files

    # create the project
    deploy_database $dir/../deploy_files
fi

# test if the project social-application exists
if oc get project social-application &> /dev/null
then
    echo "social-application project already exist. Finishing the installation process."
    echo "If you want to reinstall the application, please delete the project social-application and run this script again."
    echo "oc delete project social-application"
    if $clean; then
        exit
    fi
else
    gum format --theme=pink "## Start the application installation process"

    oc new-project social-application

    source $dir/deploy-app.sh

    # build dir
    build_dir=$dir/../../..

    deploy_postgres_secret
    deploy_quarkus_feed_service $build_dir
    deploy_quarkus_user_service $build_dir
    deploy_quarkus_post_service $build_dir
fi



gum format --theme=pink "# Start the artillery installation process"
source $dir/deploy-artillery.sh

gum format --theme=pink "# Start the data science installation process"
source $dir/deploy-datascience.sh

gum format --theme=pink "# Apply all the final changes"

echo " "
gum format --theme=pink "# All the installation process is done!"

