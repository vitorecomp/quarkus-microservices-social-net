# Certification Renew Pipeline Container

Container enabling the renovation or download of a ZeroSSL certificates

## Stack

The tech stack used on the websites is:

- NodeJs
- TypeScript
- ZeroSSL Lib
- winston Logger
- Container

## References

To better undertand this code is nice to read the following references:

- [ZeroSSL Api Lib](https://www.npmjs.com/package/zerossl)

- [ZeroSSL api reference](https://zerossl.com/documentation/api/)

## Directory Structure definition

All the code  is contained in the main.ts file, is not that big. in the src folder
is possible to find all the code that enable the config loading as well as the necessary
code for a better logging experience using winston.

## Usage

The usage can be done by three ways:

### Using the quay image

Using a pre-build quay image available at: quay.io/vvieirax86/do180-custom-httpd

``` bash
podman pull quay.io/vvieirax86/do180-custom-httpd
podman run quay.io/vvieirax86/do180-custom-httpd -e ZERO_SSL_KEY="MY_ZERO_SSL_KEY" -e DOMAIN="www.vitorx86.dev"

```

### Building your own image

``` bash
git clone https://github.com/vitorecomp/certification-reniew-pipeline-container
cd certification-reniew-pipeline-container
podman build . --tag certification-reniew-pipeline-container
podman run certification-reniew-pipeline-container -e ZERO_SSL_KEY="MY_ZERO_SSL_KEY" -e DOMAIN="www.vitorx86.dev"

```

### Using the node as is

``` bash
git clone https://github.com/vitorecomp/certification-reniew-pipeline-container
cd certification-reniew-pipeline-container
npm run build
export NODE_ENV=production && export ZERO_SSL_KEY="MY_ZERO_SSL_KEY" && export DOMAIN="www.vitorx86.dev" && npm start
```

## Developing

To be able to develop this the better way is done by follow the following steps:

``` bash
git clone https://github.com/vitorecomp/certification-reniew-pipeline-container
cd certification-reniew-pipeline-container

cp src/config/dev-files/dev-config-template.json src/config/dev-files/dev-config.json

#add your vars on the dev-config file

npm run dev-test

```

## Todo

Here a points to start to contribute:

- Create the mocks of zero ssl
- Create unit tests
- Make the automatic deploy of the image on quay
- Create the github action pipelines
- use multi stage build container strategy
