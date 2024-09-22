package org.example;

import com.google.common.collect.Iterables;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Group by works in 2 stages i.e. shuffle data and map it to based on key.
 */
public class SparkGroupByKeyExample {
		public static void main(String[] args) {
				System.out.println("SparkGroupByKeyExample started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkConf conf = new SparkConf().setAppName("SparkGroupByKeyExample").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				List<String> inputData = new ArrayList<>();

				inputData.add("WARN:first warning");
				inputData.add("ERROR:first error");
				inputData.add("WARN:second warning");
				inputData.add("FINE:first fine");

				JavaRDD<String> inputRDD = context.parallelize(inputData);

				JavaPairRDD<String, String> pairRDD = inputRDD.mapToPair(value -> {
						String[] val = value.split(":");

						String level = val[0];
						String msg = val[1];

						return new Tuple2<>(level, msg);
				});

				JavaPairRDD<String, Iterable<String>> groupByKeyRDD = pairRDD.groupByKey();

				groupByKeyRDD.foreach(val -> System.out.println("key : "+ val._1+" count : "+ Iterables.size(val._2)));

				context.close();

				System.out.println("SparkGroupByKeyExample ended");

		}
}
