#!/bin/sh

mvn clean package
java -jar target/inventory-0.0.1-SNAPSHOT.jar
