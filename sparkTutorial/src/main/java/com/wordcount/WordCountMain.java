package com.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountMain {
		public static void main(String[] args) {
				try {
						WordCountMain wordCountMain = new WordCountMain();
						wordCountMain.start();
				} catch (Throwable e){
						e.printStackTrace();
				}

		}

		private void start(){
				SparkConf conf = new SparkConf();
				conf.setAppName("WordCountSparkApplication");
				conf.setMaster("localhost[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				//				JavaRDD<String> fileRDD = context.textFile(
				//						"C:\\workspace\\tmp\\sparkDataset\\wikisent2.txt", 1);

				JavaRDD<String> fileRDD = context.textFile(
						"C:\\workspace\\tmp\\sparkDataset\\test.txt", 1);

				JavaRDD<String> wordsRDD = fileRDD.flatMap(lines -> Arrays.asList(lines.split(" ")).iterator());

				JavaPairRDD<String, Integer> tupleWordRDD = wordsRDD.mapToPair(word -> Tuple2.apply(word, 1));

				JavaPairRDD<String, Integer> countedRDD = tupleWordRDD.reduceByKey(
						(Integer i1, Integer i2) -> i1 + i2);

				countedRDD.saveAsTextFile("result.txt");

				context.stop();
		}
}
