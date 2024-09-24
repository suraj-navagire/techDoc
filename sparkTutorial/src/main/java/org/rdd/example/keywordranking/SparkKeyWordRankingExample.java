package org.rdd.example.keywordranking;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class SparkKeyWordRankingExample {
		public static void main(String[] args) {
				System.out.println("SparkKeyWordRankingExample started");

				System.setProperty("hadoop.home.dir", "C:\\hadoop");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				//Following files can be downloaded from spark udemy tutorial attachment
//				String file = "src/main/resources/input-spring.txt";
				String file = "src/main/resources/input.txt";
				SparkConf conf = new SparkConf().setAppName("Keywords Ranking App").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<String> inputRDD = context.textFile(file);

				inputRDD = inputRDD.map(sentence -> sentence.replaceAll("[^a-zA-Z\\s]", "").toLowerCase());

				JavaRDD<String> wordsRDD = inputRDD.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

				wordsRDD = wordsRDD.filter(word -> !BoringWordCache.isWordExists(word));

				JavaPairRDD<String, Long> pairWordsRDD = wordsRDD.mapToPair(word -> new Tuple2<>(word, 1L));

				JavaPairRDD<String, Long> reducedWordRDD = pairWordsRDD.reduceByKey(
						(value1, value2) -> value1 + value2);

				JavaPairRDD<Long, String> newWordRDD = reducedWordRDD.mapToPair(
						tuple -> new Tuple2<>(tuple._2, tuple._1));

				JavaPairRDD<Long, String> wordRankingRDD = newWordRDD.sortByKey(false);

				//This will give entire result
//				List<Tuple2<Long, String>> result = wordRankingRDD.collect();

				//Use this if we want only 10 result
				List<Tuple2<Long, String>> result = wordRankingRDD.take(10);

				result.forEach(value -> System.out.println(value));

				context.close();

				System.out.println("SparkKeyWordRankingExample Ended");
		}
}