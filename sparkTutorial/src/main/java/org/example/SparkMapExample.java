package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class SparkMapExample {
		public static void main(String[] args) {
				System.out.println("Spark Map Example started");

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

				JavaRDD<Double> sqrtRDD = inputRDD.map(value -> Math.sqrt(value));

				sqrtRDD.foreach(value -> System.out.println(value));

				System.out.println("SparkMapExample ended");
		}
}
