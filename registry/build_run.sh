#!/bin/sh

mvn clean package
java -jar target/registry-0.0.1-SNAPSHOT.jar
