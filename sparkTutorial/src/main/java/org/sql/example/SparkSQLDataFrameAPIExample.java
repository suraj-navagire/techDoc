package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;

import static org.apache.spark.sql.functions.*;

public class SparkSQLDataFrameAPIExample {
		public static void main(String[] args) {
				System.out.println("SparkSQLDataFrameAPIExample Started");

				System.setProperty("hadoop.home.dir", "c://hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkSession spark = SparkSession.builder().appName("SparkSQLDataFrameAPIExample").master("local[*]")
						.config("spark.sql.warehouse.dir", "file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				Dataset<Row> inputData = spark.read().option("header", true)
						.csv("src/main/resources/inputData/sql/biglog.txt");

				Dataset<Row> selectedData = inputData.select(col("level"),
						date_format(col("datetime"), "MMMM").alias("month"),
						date_format(col("datetime"), "M").cast(DataTypes.IntegerType).alias("monthNum"));

				selectedData = selectedData.groupBy(col("level"), col("month"), col("monthNum")).count();

				selectedData = selectedData.orderBy(col("monthNum"), col("level"));

				Dataset<Row> result = selectedData.drop(col("monthNum"));

				result.show();

				Dataset<Row> totalCount = selectedData.select(sum(col("count")));

				totalCount.show();

				spark.close();

				System.out.println("SparkSQLDataFrameAPIExample Ended");

		}
}
