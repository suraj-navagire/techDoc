package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSQLFileReadExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLFileReadExample Started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				//Need to provide warehouse location else it will take default location. It's actually hive metastore.
				SparkSession spark = SparkSession.builder().appName("SparkSQLFileReadExample").master("local[*]").config("spark.sql.warehouse.dir", "file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp")
						.getOrCreate();

				//Set header property true to tell spark that this file contains header line.
				Dataset<Row> inputDataSet = spark.read().option("header",true).csv("src/main/resources/inputData/sql/students.csv");

				inputDataSet.show();

				Row firstRow = inputDataSet.first();
				String val = firstRow.get(2).toString();
				String val2 = firstRow.getAs("subject").toString();

				System.out.println("Subject column : "+ val+", "+val2);

				long count = inputDataSet.count();

				System.out.println("Total count : "+ count);

				System.out.println("SparkSQLFileReadExample ended");
				spark.close();
		}
}
