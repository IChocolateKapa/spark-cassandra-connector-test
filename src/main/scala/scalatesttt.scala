package scalatest

import org.apache.spark.SparkContext

import org.apache.spark.SparkContext._

import org.apache.spark.SparkConf

import com.datastax.spark.connector._

import com.datastax.spark.connector.streaming._

object TestExample {
  def main(args: Array[String]) {
    val conf = new SparkConf(true).set("spark.cassandra.connection.host", "127.0.0.1")

	val sc = new SparkContext("spark://127.0.0.1:7077", "test", conf)

	val ssc = new StreamingContext(conf, Seconds(n))

	val stream = ssc.actorStream[String](Props[SimpleStreamingActor], actorName, StorageLevel.MEMORY_AND_DISK)

	val wc = stream.flatMap(_.split("\\s+")).map(x => (x, 1)).reduceByKey(_ + _).saveToCassandra("streaming_test", "words", SomeColumns("word", "count"))

	val rdd = sc.cassandraTable("test", "kv")

	println(rdd.count)

	println(rdd.first)

	println(rdd.map(_.getInt("value")).sum)

	val collection = sc.parallelize(Seq(("key3", 3), ("key4", 4)))
	collection.saveToCassandra("test", "kv", SomeColumns("key", "value"))
  }
}
