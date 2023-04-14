#!/bin/bash

deploy_postgres_secret() {
    oc get secret social-database-postgres-pguser-social-database-postgres --namespace=social-database -o yaml \
        | sed 's/namespace: .*/namespace: social-application/' | sed 's/ name: .*/ name: postgres-secret-openshift/' \
        | sed -e '/ownerRefere/, +6d' \
        | sed -e '/uid:/, 1d' \
        | sed -e '/resourceVersion:/, 1d' \
        | sed -r '/creationTimestamp:/, 1d' \
        | oc apply -f -
}


# function to deploy the quarkus post-service
deploy_quarkus_post_service() {
    gum format --theme=pink "## Deploying the quarkus post-service"
    cd $1/post-service
    mvn package -Dquarkus.kubernetes.deploy=true -DskipTests
}

# function to deploy the quarkus user-service
deploy_quarkus_user_service() {
    gum format --theme=pink "## Deploying the quarkus user-service"
    cd $1/user-service
    mvn package -Dquarkus.kubernetes.deploy=true -DskipTests
}

# function to deploy the quarkus feed-service
deploy_quarkus_feed_service() {
    gum format --theme=pink "## Deploying the quarkus feed-service"
    cd $1/feed-service
    mvn package -Dquarkus.kubernetes.deploy=true -DskipTests
}