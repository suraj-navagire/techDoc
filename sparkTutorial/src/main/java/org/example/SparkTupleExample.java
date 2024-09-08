package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class SparkTupleExample {
		public static void main(String[] args) {

				System.out.println("SparkTupleExample started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkConf conf = new SparkConf().setAppName("SparkTupleExample").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				List<Integer> inputData = new ArrayList<>();

				inputData.add(9);
				inputData.add(16);
				inputData.add(81);
				inputData.add(98);

				JavaRDD<Integer> inputRDD = context.parallelize(inputData);

				JavaRDD<Tuple2> sqrtRDD = inputRDD.map(value -> new Tuple2(value, Math.sqrt(value)));

				sqrtRDD.foreach(value -> System.out.println(value));

				context.close();

				System.out.println("SparkTupleExample ended");

		}

}
