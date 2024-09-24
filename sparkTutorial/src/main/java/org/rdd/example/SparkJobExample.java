package org.rdd.example;

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
 * For very action spark creates new job. In this example we have provided 2 actions so spark will create 2 job.
 *
 * When new job gets created it starts computation from last write operation.
 *
 * In following example count will start its computing from reading data i.e. shuffled, but this shuffled saved data won't be grouped.
 *
 *
 */
public class SparkJobExample {
		public static void main(String[] args) {
				System.out.println("SparkJobExample Started :");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "c://hadoop");

				SparkConf conf = new SparkConf().setAppName("SparkShuffling").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<String> inputRDD = context.textFile("src/main/resources/inputData/viewingFigures/bigLog.txt");

				JavaPairRDD<String, String> logPairRDD = inputRDD.mapToPair(
						row -> new Tuple2<>(row.split(":")[0], row.split(":")[1]));

				//At this point shuffle will take place
				//Count action's job will start its operation from this point. As while doing group by it saves shuffled data.
				// But this saved data is not grouped. So count action will read and group it again in its stage.
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

				Long count = keyCountRDD.count();

				System.out.println("Count : "+count);

				Scanner sc = new Scanner(System.in);
				sc.nextLine();


				context.close();

				System.out.println("SparkJobExample ended");
		}
}
