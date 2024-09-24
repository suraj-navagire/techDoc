package org.rdd.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class SparkPairRDD {
		public static void main(String[] args) {
				System.out.println("SparkPairRDD started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkConf conf = new SparkConf().setAppName("SparkPairRDD").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				List<String> inputData = new ArrayList<>();

				inputData.add("WARN:first warning");
				inputData.add("ERROR:first error");
				inputData.add("WARN:second warning");
				inputData.add("FINE:first fine");

				JavaRDD<String> inputRDD = context.parallelize(inputData);

				JavaPairRDD<String, Long> pairRDD = inputRDD.mapToPair(value -> {
						String[] val = value.split(":");

						String level = val[0];

						return new Tuple2<>(level, 1L);
				});

				pairRDD.foreach(val -> System.out.println(val));

				JavaPairRDD<String, Long> count = pairRDD.reduceByKey((val1, val2) -> val1 + val2);

				count.foreach(val -> System.out.println(val));

				context.close();

				System.out.println("SparkPairRDD ended");
		}
}
