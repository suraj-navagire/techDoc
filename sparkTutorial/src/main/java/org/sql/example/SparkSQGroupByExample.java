package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * In this example we are printing count of each level of logs for each month.
 */
public class SparkSQGroupByExample {
		public static void main(String[] args) {
				System.out.println("SparkSQGroupByExample Started");

				System.setProperty("hadoop.home.dir", "c://hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkSession spark = SparkSession.builder().appName("SparkSQLFullSyntax").master("local[*]")
						.config("spark.sql.warehouse.dir","file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/biglog.txt");

				inputData.createOrReplaceTempView("loggingTable");

				Dataset<Row> result = spark.sql("select level, date_format(datetime, 'MMMM') as month , count(1) as count from loggingTable group by level, month");

				result.show();

				result.createOrReplaceTempView("resultTable");

				Dataset<Row> count = spark.sql("select sum(count) as TotalCount from resultTable");

				count.show();

				spark.close();

				System.out.println("SparkSQGroupByExample ended");
		}
}
