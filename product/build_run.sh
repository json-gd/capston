#!/bin/sh

mvn clean package
java -jar target/product-0.0.1-SNAPSHOT.jar
