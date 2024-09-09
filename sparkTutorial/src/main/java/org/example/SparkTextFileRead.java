package org.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class SparkTextFileRead {
		public static void main(String[] args) {
				System.setProperty("hadoop.home.dir","C:\\hadoop");

				System.out.println("SparkTextFileRead started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				SparkConf conf = new SparkConf();
				conf.setAppName("SparkTextFileRead");
				conf.setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				JavaRDD<String> inputRDD = context.textFile("src/main/resources/Iron_Man_2008.txt");

				JavaRDD<String> wordRDD = inputRDD.flatMap(value -> Arrays.asList(value.split(" ")).iterator());

				wordRDD.foreach(val -> System.out.println(val));

				context.close();

				System.out.println("SparkTextFileRead ended");
		}
}
