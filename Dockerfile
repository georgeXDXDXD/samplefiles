FROM ubuntu:latest
COPY . /samplefiles
RUN apt update
RUN apt install -y wget
RUN apt-get install -y openjdk-11-jdk
RUN wget https://dlcdn.apache.org/spark/spark-3.2.3/spark-3.2.3-bin-hadoop3.2.tgz
RUN tar -xvf spark-3.2.3-bin-hadoop3.2.tgz
RUN mv spark-3.2.3-bin-hadoop3.2 spark
ENTRYPOINT [."/spark/bin/spark-shell -i /samplefiles/spark-prgm.scala"]

