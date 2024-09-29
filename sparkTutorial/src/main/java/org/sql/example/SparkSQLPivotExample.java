package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.date_format;

public class SparkSQLPivotExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLPivotExample Started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				SparkSession spark = SparkSession.builder().appName("SparkSQLPivotExample").master("local[*]")
						.config("spark.sql.warehouse.dir", "file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/biglog.txt");

				Dataset<Row> selectedData = inputData.select(col("level"), date_format(col("datetime"), "MMMM").alias("month"));

				String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

				List<Object> monthList = Arrays.asList(months);

				selectedData = selectedData.groupBy(col("level")).pivot(col("month"), monthList).count();

				selectedData.show();

				spark.close();

				System.out.println("SparkSQLPivotExample ended");
		}
}
