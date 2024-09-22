package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.List;
import java.util.Scanner;

/**
 * Spark shuffling happens when there a wide transformation i.e. when data gets shuffled between partitions.
 *
 * We can check this in DAG of spark UI. Visit localhost:4040 to see spark ui.
 *
 * I have stopped program using scanner to see spark UI.
 */
public class SparkShufflingExample {
		public static void main(String[] args) {
				System.out.println("SparkShuffling Started :");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				SparkConf conf = new SparkConf().setAppName("SparkShuffling").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<String> inputRDD = context.textFile("src/main/resources/inputData/viewingFigures/bigLog.txt");

				JavaPairRDD<String, String> logPairRDD = inputRDD.mapToPair(
						row -> new Tuple2<>(row.split(":")[0], row.split(":")[1]));

				//At this point shuffle will take place
				JavaPairRDD<String, Iterable<String>> groupRDD = logPairRDD.groupByKey();

				JavaPairRDD<String, Long> keyCountRDD = groupRDD.mapToPair(row -> {
						Long size = 0L;
						for (String s : row._2) {
								size++;
						}

						return new Tuple2<>(row._1, size);
				});
				List<Tuple2<String, Long>> result = keyCountRDD.collect();

				result.forEach( row -> System.out.println(row));

				Scanner sc = new Scanner(System.in);
				sc.nextLine();


				context.close();

				System.out.println("SparkShuffling ended");
		}
}
