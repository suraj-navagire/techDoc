package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * In this example we are showing count of logs for each level for each month. Also we are ordering result first by month and then level.
 */
public class SparkSQLOrderByExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLOrderByExample Started");

				System.setProperty("hadoop.home.dir", "c://hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkSession spark = SparkSession.builder().appName("SparkSQLFullSyntax").master("local[*]")
						.config("spark.sql.warehouse.dir","file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/biglog.txt");

				inputData.createOrReplaceTempView("loggingTable");

				//First it will order by month then within a month it will order by level
				Dataset<Row> result = spark.sql("select level, date_format(datetime, 'MMMM') as month , count(1) as count "
						+ "from loggingTable group by level, month order by cast(first(date_format(datetime, 'M')) as int), level");

				result.show();


				spark.close();

				System.out.println("SparkSQLOrderByExample ended");
		}
}
