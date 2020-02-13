# ParquetDSEFS

Required
--------

DSE 6.x version with Spark and DSEFS Enabled
JDK 1.8 Version
Maven 3.x Version



Steps to Run
------------

1. Download or clone above repository
2. mvn clean build (Build the jar)
3. Create keyspace using CQLSH

CREATE KEYSPACE cycling
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 3 
  };


4. Create table using CQLSH

CREATE TABLE user_address_multiple_search (
unique timeuuid,
address_name text,
address1 text,
address2 text,
address3 text,
birthday date,
city text,
country text,
first_name text,
geo text,
hamlet text,
last_name text,
latitude double,
longitude double,
marital text,
mid_name text,
occupation text,
ordinal text,
race text,
sex text,
state text,
title text,
zip text,
PRIMARY KEY (unique, address_name));

5. Load or Insert data to above table

6. Run below command to submit spark application 

dse spark-submit 
	--deploy-mode cluster
	--class com.datastax.dsefs.parquet.ParquetConverter  
	--executor-memory 8G 
	--total-executor-cores 9 
	/../sparkjar/parquet-1.0.0-SNAPSHOT-jar-with-dependencies.jar
  
  7. Launch Spark SQL to verify
  
  dse spark --driver-memory 2G --executor-memory 4G --executor-cores 3
   
   8. Verify the count
   
    scala> spark.sql ("select count(*) from parquet.`dsefs:///ParquetConverter1`").show
    +--------+
    |count(1)|
    +--------+
    | 1000000|
    +--------+


  
