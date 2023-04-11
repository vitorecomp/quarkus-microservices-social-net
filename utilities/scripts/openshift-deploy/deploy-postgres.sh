#!/bin/bash

#tranforms lines to an array
generate_operator_yaml() {
    input_dir=$1
    output_dir=$2

    IFS=$'\n'
    #get the operator options
    name=$(gum choose --selected crunchy-postgres-operator --header "Choose your postgres operator" $(oc get packagemanifests --template='{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}' | grep crunchy-postgres-operator))
    
    
    # restore IFS
    unset IFS
    channel=$(gum choose --selected v5 --header "Choose your postgres operator version" $(oc get packagemanifests $name -o jsonpath='{.status.channels[*].name}'))
    currentCSV=$(gum choose --header "Get your initial csv" $(oc get packagemanifests $name -o jsonpath='{.status.channels[?(@.name=="'$channel'")].currentCSV}'))


    catalog_source=$(oc get packagemanifests $name -o jsonpath='{.status.catalogSource}')
    catalog_source_namespace=$(oc get packagemanifests $name -o jsonpath='{.status.catalogSourceNamespace}')
    # restore IFS
    unset IFS

    export name=$name
    export channel=$channel
    export currentCSV=$currentCSV
    export catalog_source=$catalog_source
    export catalog_source_namespace=$catalog_source_namespace

    envsubst < $input_dir/operator-group-template.yaml >| $output_dir/postgres-operator-group.yaml
    envsubst < $input_dir/operator-template.yaml >| $output_dir/postgres-operator.yaml
}


generate_database_yaml() {
    input_dir=$1
    output_dir=$2
    envsubst < $input_dir/crd-template.yaml >| $output_dir/postgres-cluster-crd.yaml
}