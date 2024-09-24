package org.rdd.example;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class SparkJoinExample {
		public static void main(String[] args) {
				System.out.println("SparkJoinExample started: ");
				Logger.getLogger("org.apache").setLevel(Level.WARN);
				System.setProperty("hadoop.home.dir", "C:\\hadoop");

				SparkConf conf = new SparkConf().setAppName("SparkJoinExample").setMaster("local[*]");

				JavaSparkContext context = new JavaSparkContext(conf);

				innerJoin(context);

				leftOutJoin(context);

				rightOuterJoin(context);

				cartesianJoin(context);

				context.close();

				System.out.println("SparkJoinExample ended");
		}

		private static void innerJoin(JavaSparkContext context){

				System.out.println("inner join: ");
				JavaPairRDD<Integer, Integer> visitsRDD = context.parallelizePairs(getUserIdVisitsList());

				JavaPairRDD<Integer, String> nameRDD = context.parallelizePairs(getUserIdNameList());

				JavaPairRDD<Integer, Tuple2<Integer, String>> joinedRDD = visitsRDD.join(nameRDD);

				List<Tuple2<Integer, Tuple2<Integer, String>>> resultList = joinedRDD.collect();


				resultList.forEach(result -> System.out.println("User : "+result._1+", Visits : "+result._2._1+", Name : "+result._2._2));
		}

		private static void leftOutJoin(JavaSparkContext context){
				System.out.println("Left outer join: ");
				JavaPairRDD<Integer, Integer> visitsRDD = context.parallelizePairs(getUserIdVisitsList());

				JavaPairRDD<Integer, String> nameRDD = context.parallelizePairs(getUserIdNameList());

				JavaPairRDD<Integer, Tuple2<Integer, Optional<String>>> joinedRDD = visitsRDD.leftOuterJoin(nameRDD);

				List<Tuple2<Integer, Tuple2<Integer, Optional<String>>>> resultList = joinedRDD.collect();


				resultList.forEach(result -> System.out.println("User : "+result._1+", Visits : "+result._2._1+", Name : "+result._2._2.orElse("Unknown")));
		}

		private static void rightOuterJoin(JavaSparkContext context){
				System.out.println("Right outer join: ");
				JavaPairRDD<Integer, Integer> visitsRDD = context.parallelizePairs(getUserIdVisitsList());

				JavaPairRDD<Integer, String> nameRDD = context.parallelizePairs(getUserIdNameList());

				JavaPairRDD<Integer, Tuple2<Optional<Integer>, String>> joinedRDD = visitsRDD.rightOuterJoin(nameRDD);

				List<Tuple2<Integer, Tuple2<Optional<Integer>, String>>> resultList = joinedRDD.collect();


				resultList.forEach(result -> System.out.println("User : "+result._1+", Visits : "+result._2._1.orElse(0)+", Name : "+result._2._2));
		}

		/**
		 * This is cross join means left row will get mapped to all right row and so on.
		 * @param context
		 */
		private static void cartesianJoin(JavaSparkContext context){
				System.out.println("Cartesian join: ");
				JavaPairRDD<Integer, Integer> visitsRDD = context.parallelizePairs(getUserIdVisitsList());

				JavaPairRDD<Integer, String> nameRDD = context.parallelizePairs(getUserIdNameList());

				JavaPairRDD<Tuple2<Integer, Integer>, Tuple2<Integer, String>> joinedRDD = visitsRDD.cartesian(nameRDD);

				List<Tuple2<Tuple2<Integer, Integer>, Tuple2<Integer, String>>> resultList = joinedRDD.collect();


				resultList.forEach(result -> System.out.println(result));
		}

		private static List<Tuple2<Integer, String>> getUserIdNameList(){
				List<Tuple2<Integer, String>> userList = new ArrayList<>();

				userList.add(new Tuple2<>(1, "John"));
				userList.add(new Tuple2<>(2, "Bob"));
				userList.add(new Tuple2<>(3, "Alan"));
				userList.add(new Tuple2<>(4, "Doris"));
				userList.add(new Tuple2<>(5, "Merybelle"));
				userList.add(new Tuple2<>(6, "Raquel"));

				return userList;
		}

		private static List<Tuple2<Integer, Integer>> getUserIdVisitsList(){
				List<Tuple2<Integer, Integer>> userList = new ArrayList<>();

				userList.add(new Tuple2<>(4, 18));
				userList.add(new Tuple2<>(6, 4));
				userList.add(new Tuple2<>(10, 9));

				return userList;
		}
}
