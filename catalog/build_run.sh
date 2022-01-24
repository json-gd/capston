#!/bin/sh

mvn clean package
java -jar target/catalog-0.0.1-SNAPSHOT.jar
