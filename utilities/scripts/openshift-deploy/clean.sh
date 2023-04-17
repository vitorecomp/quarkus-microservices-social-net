#!bin/bash

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

gum format --theme=pink "## Start the cleaning process"

#confirm with gum if the user wants to delete the project
if ! gum confirm "Do you want to delete the follwing projects (social-database)?"; then
    exit
fi

gum format --theme=pink "### Deleting the project social-database"


#delete the social-database project if exists
if oc get project social-database &> /dev/null
then
    if oc get PostgresCluster social-database-postgres -n social-database &> /dev/null
    then
        oc delete PostgresCluster social-database-postgres -n social-database
        gum spin --title "Deleting PostgresCluster social-database-postgres" -- oc wait --for=delete --timeout=600s PostgresCluster/social-database-postgres -n social-database
    fi

    oc delete project social-database 
    gum spin --title "Deleting project social-database" -- oc wait --for=delete --timeout=600s namespace/social-database
fi


#delete the social-database project if exists
if oc get project social-database &> /dev/null
then
    oc delete project social-database 
    gum spin --title "Deleting project social-database" -- oc wait --for=delete --timeout=600s namespace/social-database
fi

while oc get project social-database &> /dev/null; do
    gum spin --title "Waiting social-database to be deleted" -- sleep 2
done

gum format --theme=pink "### Deleting the project social-application"

#delete the social-application project if exists
if oc get project social-application &> /dev/null
then
    oc delete project social-application
    gum spin --title "Deleting project social-application" -- oc wait --for=delete --timeout=600s namespace/social-application
fi

while oc get project social-application &> /dev/null; do
    gum spin --title "Waiting social-application to be deleted" -- sleep 2
done
