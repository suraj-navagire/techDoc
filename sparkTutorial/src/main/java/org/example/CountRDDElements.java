package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class CountRDDElements {
		public static void main(String[] args) {
				System.out.println("Spark CountRDDElements Example started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				List<Integer> inputData = new ArrayList<>();
				inputData.add(2);
				inputData.add(87);
				inputData.add(98);
				inputData.add(7);
				inputData.add(43);

				SparkConf conf = new SparkConf().setAppName("SparkMapExample").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<Integer> inputRDD = context.parallelize(inputData);

				//Don't use this function to count on big data set as performance degrades.
				//inputRDD.count()

				//Use following map reduce instead.

				JavaRDD<Integer> transformedRDD = inputRDD.map(value -> 1);

				Integer result = transformedRDD.reduce((value1, value2) -> value1 + value2);

				System.out.println("Total number of count : " + result);

				System.out.println("Spark CountRDDElements example ended");
		}
}