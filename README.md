# Simple Net - Backend

Simple social Network Backend builded using quarkus 3.0.

## Technologies

This projects uses the following technologies stack

### Development Stack

- Quarkus 3.0
- Java 17

### Infra Stack

- Postgres
- Crunchy Data Postgres Cluster
- Openshift or Kubernetes

### Load Testing Stack

- Grafana
- Prometheus
- K6 Load Testing

### Insight Analysis Stack

- Jupyter Hub
- Open Data Hub
- Pandas
- Scikit Learn

## Interface

All the code capabilities can be access using a rest interface,
the documentation of the REST could be found in the swagger available
running each service in dev mode, the annotations combined with
the swagger plugin will provide all the necessary documentation for
the services.

## Running

The project could be run using two main ways:

### Development Env

In development mode is possible to simple use the quarkus combined with its
dev services, making possible a minimal configuration environment.

To run it, it's just necessary the following command:

```sh
quarkus dev
```

### Production Env

The production env is a little more complicated, mainly because this project
tries to provide a good scalability, this will be possible by making a deploy
on a orchestrated environment. Where this environment could be a Openshift or a Kubernetes.

To install the systems and all its dependencies is possible by running the script deploy.sh
where it will give the two options kubernetes or openshift. For the script to work as intended
is necessary that a session with the orchestration platform was already open, and the logged user
must have the permissions to create namespaces and make deploys.

To run it on a kubernetes, it's just necessary the following command:

```sh
sh deploy kubernetes
```

To run it on a openshift, it's just necessary the following command:

```sh
sh deploy openshift
```

## Contributing

If you want to contribute with the project this can be adding any of the features below, or by fixing some
issues on the project.

- Create and test the kubernetes deploy
- Add the automated tests
- Add a coverage test on the git flags
- Implement the feed using kafka
- Add a infinity span to cache the feed requests
- Implement the authentication using keycloak
- Add the secrets on the jupyter hub deploy
