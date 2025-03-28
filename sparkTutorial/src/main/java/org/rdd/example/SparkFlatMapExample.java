package org.rdd.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparkFlatMapExample {
		public static void main(String[] args) {
				System.out.println("SparkFlatMapExample started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				List<String> inputData = new ArrayList<>();

				inputData.add("WARN: first warning");
				inputData.add("ERROR: first error");
				inputData.add("WARN: second warning");
				inputData.add("FINE: first fine");

				SparkConf conf = new SparkConf();
				conf.setAppName("SparkFlatMapExample");
				conf.setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<String> inputRDD = context.parallelize(inputData);
				JavaRDD<String> wordsRDD = inputRDD.flatMap(value -> Arrays.asList(value.split(" ")).iterator());

				wordsRDD.foreach(value -> System.out.println(value));

				context.close();

				System.out.println("SparkFlatMapExample started");
		}
}
