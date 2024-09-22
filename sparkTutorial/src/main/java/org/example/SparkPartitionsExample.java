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
 * In this example we are reading a file and creating a partitions. By default, spark allots 32mb of data to each partition.
 *
 * If file size is 90mb then spark will create 3 partitions of size 32 , 32, 26 mb
 *
 * Partitions can run in parallel depending upon number of thread in thread pool. If number of partitions are 64 and thread
 * count in thread pool is 32 then only 32 partitions will run in parallel. Once current partitions gets completed by thread
 * it will pick up next partition.
 *
 */
public class SparkPartitionsExample {
		public static void main(String[] args) {
				System.out.println("SparkPartitionsExample Started :");

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

				System.out.println("SparkPartitionsExample ended");
		}
}
