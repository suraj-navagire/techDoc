package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class SparkSQLFunctionClassExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLFunctionClassExample Started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				SparkSession spark = SparkSession.builder().appName("SparkSQLFilterExample").master("local[*]")
						.config("spark.sql.warehouse.dir", "file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/students.csv");

				filterUsingColumn(inputData);

				spark.close();
				System.out.println("SparkSQLFunctionClassExample ended");
		}

		private static void filterUsingColumn(Dataset<Row> inputData){

				//using col method of class function. Since we have imported it using static import we don't need to use class name.
				Column subjectColumn = col("subject");

				Column yearColumn = col("year");

				Dataset<Row> filteredData = inputData.filter(
						subjectColumn.equalTo("Modern Art").and(yearColumn.geq("2005")));

				filteredData.show();

				long count = filteredData.count();

				System.out.println("Second Filtered data count : "+count);
		}
}
