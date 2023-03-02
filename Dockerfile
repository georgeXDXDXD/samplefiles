FROM ubuntu:latest
COPY . /samplefiles
RUN apt update
RUN apt install -y wget
RUN apt-get install -y openjdk-8-jdk
RUN wget wget https://archive.apache.org/dist/spark/spark-2.1.1/spark-2.1.1-bin-hadoop2.7.tgz
RUN tar -xvf spark-2.1.1-bin-hadoop2.7.tgz
RUN mv spark-2.1.1-bin-hadoop2.7 spark

