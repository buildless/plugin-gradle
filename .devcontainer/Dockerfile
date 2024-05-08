FROM --platform=linux/amd64 mcr.microsoft.com/vscode/devcontainers/base:ubuntu-24.04@sha256:c483e711bb47179497f8c630dafe07e6ce9cac8d6393f73a97af58dff67aa5d3

ARG DEV_USER=dev
ARG GRADLE_VERSION=8.1.1
ARG KOTLIN_VERSION=1.9.21
ARG GRADLE_DIST=bin
ARG JAVA_VERSION=java19

RUN apt-get update \
  && apt-get -y upgrade \
  && DEBIAN_FRONTEND=noninteractive apt-get -y install --no-install-recommends \
    ca-certificates \
    curl \
    git \
    gnupg \
    libc-dev \
  && apt-get update \
  && apt-get install -y wget tar \
  && set -eux \
  && apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 0xB1998361219BD9C9

CMD ["sleep", "infinity"]
