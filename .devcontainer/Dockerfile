FROM --platform=linux/amd64 mcr.microsoft.com/vscode/devcontainers/base:ubuntu-22.04@sha256:fdb79b61fb4e955b4042ddb25bbae616316984bcd46c193196c6e1664a5d367f

ARG DEV_USER=dev
ARG GRADLE_VERSION=8.1.1
ARG KOTLIN_VERSION=1.9.0
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
