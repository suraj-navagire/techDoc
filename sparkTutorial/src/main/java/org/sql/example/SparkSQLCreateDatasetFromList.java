package org.sql.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.List;

public class SparkSQLCreateDatasetFromList {
		public static void main(String[] args) {
				System.out.println("SparkSQLCreateDatasetFromList Started");

				System.setProperty("hadoop.home.dir", "c://hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkSession spark = SparkSession.builder().appName("SparkSQLFullSyntax").master("local[*]")
						.config("spark.sql.warehouse.dir","file:/C:/workspace/poc/github/techDoc/sparkTutorial/tmp").getOrCreate();

				List<Row> inMemory = new ArrayList<Row>();
				inMemory.add(RowFactory.create("WARN", "2016-12-31 04:19:32"));
				inMemory.add(RowFactory.create("FATAL", "2016-12-31 03:22:34"));
				inMemory.add(RowFactory.create("WARN", "2016-12-31 03:21:21"));
				inMemory.add(RowFactory.create("INFO", "2015-4-21 14:32:21"));
				inMemory.add(RowFactory.create("FATAL","2015-4-21 19:23:20"));


				StructField[] fields = new StructField[]{
						new StructField("Level", DataTypes.StringType, false, Metadata.empty()),
						new StructField("datetime", DataTypes.StringType, false, Metadata.empty())
				};

				StructType type = new StructType(fields);
				Dataset<Row> logData = spark.createDataFrame(inMemory, type);

				logData.show();

				spark.close();

				System.out.println("SparkSQLCreateDatasetFromList ended");
		}
}
