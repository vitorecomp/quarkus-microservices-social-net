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


######################################

gum format --theme=pink "## Start the database installation process"
#test if the project social-database exists
if oc get project social-database &> /dev/null;
then
    gum format --theme=pink "### social-database project already exist. Finishing the installation process."
    gum format --theme=pink "### If you want to reinstall postgres, please delete the project social-database and run this script again."
    gum format --theme=pink "### oc delete project social-database"
    if $clean; then
        exit
    fi
else 
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

######################################
gum format --theme=pink "## Start the application installation process"
# test if the project social-application exists
if oc get project social-application &> /dev/null
then
    gum format --theme=pink "### social-application project already exist. Finishing the installation process."
    gum format --theme=pink "### If you want to reinstall the application, please delete the project social-application and run this script again."
    gum format --theme=pink "### oc delete project social-application"
    if $clean; then
        exit
    fi
else

    oc new-project social-application

    source $dir/deploy-app.sh

    # build dir
    build_dir=$dir/../../..

    deploy_postgres_secret
    deploy_quarkus_feed_service $build_dir
    deploy_quarkus_user_service $build_dir
    deploy_quarkus_post_service $build_dir
fi


######################################
gum format --theme=pink "## Start the k6 load test installation process"
if oc get project k6-load &> /dev/null
then
    gum format --theme=pink "### k6-load project already exist. Finishing the installation process."
    gum format --theme=pink "### If you want to reinstall the application, please delete the project k6-load and run this script again."
    gum format --theme=pink "### oc delete project k6-load"
    if $clean; then
        exit
    fi
else
    oc new-project k6-load
    source $dir/deploy-k6-load.sh
fi

######################################
gum format --theme=pink "## Start the monitoring installation process"
if oc get project monitoring &> /dev/null
then
    gum format --theme=pink "### monitoring project already exist. Finishing the installation process."
    gum format --theme=pink "### If you want to reinstall the application, please delete the project monitoring and run this script again."
    gum format --theme=pink "### oc delete project monitoring"
    if $clean; then
        exit
    fi
else

    oc new-project monitoring

    source $dir/deploy-monitoring.sh 
    deploy_monitoring $dir/template-files $dir/../deploy_files
fi

######################################
gum format --theme=pink "## Start the data science installation process"
if oc get project data-science &> /dev/null
then
    gum format --theme=pink "### data-science project already exist. Finishing the installation process."
    gum format --theme=pink "### If you want to reinstall the application, please delete the project data-science and run this script again."
    gum format --theme=pink "### oc delete project data-science"
    if $clean; then
        exit
    fi
else

    oc new-project data-science
    source $dir/deploy-datascience.sh
fi

######################################
gum format --theme=pink "# Apply all the final changes"

######################################
gum format --theme=pink "# All the installation process is done!"

