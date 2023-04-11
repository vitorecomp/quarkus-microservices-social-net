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

gum format --theme=pink "# Start the cleaning process"

#confirm with gum if the user wants to delete the project
if ! gum confirm "Do you want to delete the follwing projects (social-database)?"; then
    exit
fi

gum format --theme=pink "## Deleting the project social-database"

#delete the social-database project if exists
if oc get project social-database &> /dev/null
then
   gum spin --title "Deleting project social-database" -- oc delete project --wait social-database 
fi

while oc get project social-database &> /dev/null; do
    gum spin --title "Waiting everthing to be deleted" -- sleep 10
done
