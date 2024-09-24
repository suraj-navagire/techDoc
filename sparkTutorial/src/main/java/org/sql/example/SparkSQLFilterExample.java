package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSQLFilterExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLFilterExample Started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				SparkSession spark = SparkSession.builder().appName("SparkSQLFilterExample").master("local[*]")
						.config("spark.sql.warehouse.dir", "file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/students.csv");

				filterUsingStringExpression(inputData);

				filterUsingFunctionalImplementation(inputData);

				filterUsingColumn(inputData);

				spark.close();
				System.out.println("SparkSQLFilterExample ended");
		}

		private static void filterUsingStringExpression(Dataset<Row> inputData){
				//One of the way to filter data
				Dataset<Row> filteredData = inputData.filter("subject = 'Modern Art' AND year >= '2005'");

				filteredData.show();

				long count = filteredData.count();

				System.out.println("Filtered data count : "+count);
		}

		private static void filterUsingFunctionalImplementation(Dataset<Row> inputData){
				//Another way to use filter
				Dataset<Row> filteredData = inputData.filter((FilterFunction<Row>) row -> {
						boolean  subject = row.getAs("subject").equals("Modern Art");
						int year = Integer.parseInt(row.getAs("year"));
						boolean yearPassed = year >= 2005;

						return subject && yearPassed;
				});

				filteredData.show();

				long count = filteredData.count();

				System.out.println("Second Filtered data count : "+count);
		}

		private static void filterUsingColumn(Dataset<Row> inputData){
				Column subjectColumn = inputData.col("subject");

				Column yearColumn = inputData.col("year");

				Dataset<Row> filteredData = inputData.filter(
						subjectColumn.equalTo("Modern Art").and(yearColumn.geq("2005")));

				filteredData.show();

				long count = filteredData.count();

				System.out.println("Second Filtered data count : "+count);
		}
}
