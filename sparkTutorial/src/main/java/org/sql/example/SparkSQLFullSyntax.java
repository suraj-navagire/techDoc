package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSQLFullSyntax {
		public static void main(String[] args) {
				System.out.println("SparkSQLFullSyntax Started");

				System.setProperty("hadoop.home.dir", "c://hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkSession spark = SparkSession.builder().appName("SparkSQLFullSyntax").master("local[*]")
						.config("spark.sql.warehouse.dir","file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/students.csv");

				inputData.createOrReplaceTempView("students");

				Dataset<Row> allStudentData = spark.sql("select * from students");

				allStudentData.show();

				Dataset<Row> yearData = spark.sql("select distinct(year) from students where year >= 2007 order by year");

				yearData.show();

				spark.close();

				System.out.println("SparkSQLFullSyntax ended");
		}
}
