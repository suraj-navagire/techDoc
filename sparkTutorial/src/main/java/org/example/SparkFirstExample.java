package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class SparkFirstExample {
		public static void main(String[] args) {
				System.out.println("SparkFirstExample started");
				List<Double> inputData = new ArrayList<>();
				inputData.add(3.0);
				inputData.add(5.0);
				inputData.add(10.0);
				inputData.add(13.98);

				Logger.getLogger("org.apache").setLevel(Level.WARN);
				SparkConf conf = new SparkConf();
				conf.setAppName("SparkFirstExample");
				conf.setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<Double> rdd = context.parallelize(inputData);

				context.close();

				System.out.println("SparkFirstExample ended");
		}
}