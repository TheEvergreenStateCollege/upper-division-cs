FROM ubuntu:20.04
ENV SHELL=/usr/bin/bash

LABEL maintainer="Upper Division CS <https://github.com/TheEvergreenStateCollege/upper-division-cs>"

USER root

# Install keys for yggdrasil to access machines at Evergreen on different subnets
# via IPv6 tunneling
# From https://yggdrasil-network.github.io/installation-linux-deb.html
RUN mkdir -p /usr/local/apt-keys

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends
RUN apt-get install -yqq gpg

RUN mkdir /keys
ADD ./config/yggdrasil-key.txt /keys/yggdrasil-key.txt
RUN gpg --import /keys/yggdrasil-key.txt
# Fetching doesn't work when building the image in GitPod for some reason
# RUN gpg --fetch-keys https://neilalexander.s3.dualstack.eu-west-2.amazonaws.com/deb/key.txt
RUN gpg --export BC1BF63BD10B8F1A | tee /usr/local/apt-keys/yggdrasil-keyring.gpg > /dev/null

RUN echo 'deb [signed-by=/usr/local/apt-keys/yggdrasil-keyring.gpg] http://neilalexander.s3.dualstack.eu-west-2.amazonaws.com/deb/ debian yggdrasil' | tee /etc/apt/sources.list.d/yggdrasil.list

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends

RUN apt-get install -yqq wget
RUN apt-get install -yqq ca-certificates
RUN apt-get install -yqq ssh
RUN apt-get install -yqq git
RUN apt-get install -yqq sudo
RUN apt-get install -yqq unzip
RUN apt-get install -yqq gcc
RUN apt-get install -yqq zlib1g-dev
RUN apt-get install -yqq htop
RUN apt-get install -yqq asciinema
RUN apt-get install -yqq python3-pip
RUN apt-get install -yqq yggdrasil

RUN apt-get clean && rm -rf /var/lib/apt/lists/*

RUN mkdir ~/scripts
COPY ./scripts/dl-graalvm.sh /root/scripts/dl-graalvm.sh
COPY ./scripts/.shrc /root/.shrc

RUN ssh-keyscan github.com

# Download the right GraalVM for the given architecture
RUN . /root/scripts/dl-graalvm.sh
RUN . /root/.shrc; gu install nodejs
RUN . /root/.shrc; gu install python

# Download and install maven
WORKDIR /opt
RUN wget "https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz"
RUN tar -xzvf apache-maven-3.9.4-bin.tar.gz && rm apache-maven-3.9.4-bin.tar.gz
# add java and maven to path
ENV PATH=/opt/apache-maven-3.9.4/bin:/opt/graalvm-community-openjdk-20.0.2+9.1/bin:${PATH}

RUN mkdir ~/src
RUN cd ~/src; git clone https://github.com/TheEvergreenStateCollege/upper-division-cs

# We add these layers at the end as much as possible to reuse previous build cache
ADD ./config/yggdrasil.conf /etc/yggdrasil/yggdrasil.conf

WORKDIR "${HOME}"
