#!/bin/bash

dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

generate_operator_group_yaml() {
    envsubst < $input_dir/operator-group-monitoring-template.yaml >| $output_dir/operator-group-monitoring.yaml
}

apply_monitoring_group() {
    oc apply -f $dir/../deploy_files/operator-group-monitoring.yaml
}

generate_prometheus_operator_yaml() {
    input_dir=$1
    output_dir=$2
    fast=true

    #get the operator options
    if [ "$fast" = true ]; then
        name=prometheus
        channel=beta
        currentCSV=prometheusoperator.0.37.0
    else
        IFS=$'\n'
        name=$(gum choose --selected crunchy-postgres-operator --header "Choose your postgres operator" $(oc get packagemanifests --template='{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}' | grep prometheus))
        # restore IFS
        unset IFS
        channel=$(gum choose --selected v5 --header "Choose your postgres operator version" $(oc get packagemanifests $name -o jsonpath='{.status.channels[*].name}'))
        currentCSV=$(gum choose --header "Get your initial csv" $(oc get packagemanifests $name -o jsonpath='{.status.channels[?(@.name=="'$channel'")].currentCSV}'))
    fi


    catalog_source=$(oc get packagemanifests $name -o jsonpath='{.status.catalogSource}')
    catalog_source_namespace=$(oc get packagemanifests $name -o jsonpath='{.status.catalogSourceNamespace}')

    export name=$name
    export channel=$channel
    export currentCSV=$currentCSV
    export catalog_source=$catalog_source
    export catalog_source_namespace=$catalog_source_namespace

    
    envsubst < $input_dir/monitoring/operator-prometheus.yaml >| $output_dir/operator-prometheus.yaml
}


generate_prometheus_crd_yaml() {
    envsubst < $input_dir/monitoring/crd-prometheus-template.yaml >| $output_dir/crd-prometheus.yaml
}

apply_prometheus() {
    oc apply -f $dir/../deploy_files/operator-prometheus.yaml
    oc apply -f $dir/../deploy_files/crd-prometheus.yaml
}

#create the monitoring project
deploy_monitoring() {
    oc new-project monitoring

    generate_operator_group_yaml $dir/template-files $dir/../deploy_files
    apply_monitoring_group

    #install the prometheus
    generate_prometheus_operator_yaml $dir/template-files $dir/../deploy_files
    generate_prometheus_crd_yaml $dir/template-files $dir/../deploy_files
    apply_prometheus

    #install the grafana operator
    #intall the crds
}

