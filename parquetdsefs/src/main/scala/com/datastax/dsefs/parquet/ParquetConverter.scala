package com.datastax.dsefs.parquet

import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.SparkSession
import com.datastax.spark.connector.DseConfiguration._
/**
 * Converting Table data into Parquet Format and storing into DSEFS
 * @author vinodjembu
 */
object ParquetConverter {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("ParquetConverter")
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._
    val df = spark.sql(" select * from cycling.user_address_multiple_search ")
    df.write.mode("append").parquet("dsefs:///ParquetConverter")

  }

}