package org.example.courseranking;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

/**
 * In Case study we have to rank courses based on points.
 *
 * Points conditions:
 * If a user watches more than 90% of the course, the course gets 10 points
 * If a user watches > 50% but <90% , it scores 4
 * If a user watches > 25% but < 50% it scores 2
 * Less than 25% is no score
 */
public class SparkCourseRankingExample {
		public static void main(String[] args) {
				System.out.println("SparkCourseRankingExample started");

				Logger.getLogger("org.apache").setLevel(Level.WARN);

				System.setProperty("hadoop.home.dir", "C:\\hadoop");

				SparkConf conf = new SparkConf().setAppName("SparkCourseRankingExample").setMaster("local[*]");
				JavaSparkContext context = new JavaSparkContext(conf);

				JavaPairRDD<Integer, Integer> chapterCourseRDD = chapterCourseRDD(context);

				JavaPairRDD<Integer, Integer> userChapterRDD = userChapterRDD(context);

				JavaPairRDD<Integer, String> courseTitleRDD = courseTitleRDD(context);


				//Step 1 : Find filter duplicate records
				chapterCourseRDD = chapterCourseRDD.distinct();
				userChapterRDD = userChapterRDD.distinct();
				courseTitleRDD = courseTitleRDD.distinct();

				//Step 2 : Find how many chapters of a course completed by user
				JavaPairRDD<Integer, Integer> chapterUserRDD = userChapterRDD.mapToPair(
						row -> new Tuple2<>(row._2, row._1));

				JavaPairRDD<Integer, Tuple2<Integer, Integer>> chapterUserCourseRDD = chapterUserRDD.join(
						chapterCourseRDD);

				JavaPairRDD<Tuple2<Integer, Integer>, Integer> userCourseChapterCountRDD = chapterUserCourseRDD.mapToPair(
						row -> {
								Integer userId = row._2._1;
								Integer courseId = row._2._2;

								//Returning 1 instead of chapter id since we really don't need chapter id we just want count.
								return new Tuple2<>(new Tuple2<>(userId, courseId), 1);
						});

				userCourseChapterCountRDD = userCourseChapterCountRDD.reduceByKey((value1 , value2) -> value1 + value2);


				//Step 2 : Find total chapters in course
				//Returning 1 instead of  chapter id as we really don't need chapter id we just want count.
				JavaPairRDD<Integer, Integer> courseChapterCountRDD = chapterCourseRDD.mapToPair(
						row -> new Tuple2<>(row._2, 1));

				courseChapterCountRDD = courseChapterCountRDD.reduceByKey((val1 , val2) -> val1+val2);


				//Step 3 : Find percentage of course completed by users.
				//We can remove user id from key and keep only course id as we really don't need user id anymore as row indicates user course info only.
				JavaPairRDD<Integer, Integer> courseChapterCompletedCountRDD = userCourseChapterCountRDD.mapToPair(
						row -> {
								Integer courseId = row._1._2;
								Integer chapterCount = row._2;

								return new Tuple2<>(courseId, chapterCount);
						});

				JavaPairRDD<Integer, Tuple2<Integer, Integer>> courseChapterCompletedTotalCount = courseChapterCompletedCountRDD.join(
						courseChapterCountRDD);

				JavaPairRDD<Integer, Double> coursePercentRDD = courseChapterCompletedTotalCount.mapToPair(row -> {
						Integer courseId = row._1;
						Integer chapterCompleted = row._2._1;
						Integer totalChapter = row._2._2;

						Double percentageComplete = (double) chapterCompleted / totalChapter;

						return new Tuple2<>(courseId, percentageComplete);
				});

				//Step 4 : Add points to course completion based.
				JavaPairRDD<Integer, Long> courseViewPoints = coursePercentRDD.mapToPair(row -> {
						Integer courseId = row._1;
						Double percentage = row._2;

						Long points = 0L;
						if (percentage > 0.9) {
								points = 10L;
						} else if (percentage > 0.5) {
								points = 4L;
						} else if (percentage > 0.25) {
								points = 2L;
						}

						return new Tuple2<>(courseId, points);
				});

				//Step 5 : Calculate total points of each course
				JavaPairRDD<Integer, Long> coursePoints = courseViewPoints.reduceByKey((val1, val2) -> val1 + val2);

				//Step 6 : Add course name
				JavaPairRDD<Integer, Tuple2<Long, String>> courseNamePointsRDD = coursePoints.join(courseTitleRDD);

				//Step 7 : Now we don't need course id.
				JavaPairRDD<Long, String> pointsCourseRDD = courseNamePointsRDD.mapToPair(
						row -> new Tuple2<>(row._2._1, row._2._2));

				//Step 8 : Sort by points in descending to get top demanding courses
				pointsCourseRDD = pointsCourseRDD.sortByKey(false);

				//Step 9 : Print result
				pointsCourseRDD.collect().forEach(row -> System.out.println(row));
				context.close();

				System.out.println("SparkCourseRankingExample ended");

		}

		/**
		 * Read file and return pair rdd which will contain chapter id and course id.
		 *
		 * @param context
		 */
		private static JavaPairRDD<Integer, Integer> chapterCourseRDD(JavaSparkContext context){
				JavaRDD<String> chapterCourseRDD = context.textFile("src/main/resources/inputData/viewingFigures/chapters.csv");

				JavaPairRDD<Integer, Integer> chapterCoursePairRDD = chapterCourseRDD.mapToPair(row -> {
						String[] data = row.split(",");

						return new Tuple2<Integer, Integer>(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				});

				return chapterCoursePairRDD;
		}

		/**
		 * Read file and return pair rdd which will contain user id and chapter id.
		 *
		 * @param context
		 */
		private static JavaPairRDD<Integer, Integer> userChapterRDD(JavaSparkContext context){
				JavaRDD<String> userChapterRDD = context.textFile("src/main/resources/inputData/viewingFigures/views-*.csv");

				JavaPairRDD<Integer, Integer> userChapterPairRDD = userChapterRDD.mapToPair(row -> {
						String[] data = row.split(",");

						return new Tuple2<Integer, Integer>(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				});

				return userChapterPairRDD;
		}

		/**
		 * Read file and return pair rdd which will contain chapter id and course id.
		 *
		 * @param context
		 */
		private static JavaPairRDD<Integer, String> courseTitleRDD(JavaSparkContext context){
				JavaRDD<String> courseTitleRDD = context.textFile("src/main/resources/inputData/viewingFigures/titles.csv");

				JavaPairRDD<Integer, String> courseTitlePairRDD = courseTitleRDD.mapToPair(row -> {
						String[] data = row.split(",");

						return new Tuple2<Integer, String>(Integer.parseInt(data[0]), data[1]);
				});

				return courseTitlePairRDD;
		}
}
