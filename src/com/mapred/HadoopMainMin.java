package com.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HadoopMainMin {
	public static void main(String[] args) throws Exception {
		//load Configuration
		Configuration conf = new Configuration();
		//create a job with the configuration
		Job job = new Job(conf);
		
		//setJob
			//set jar by class
			job.setJarByClass(HadoopMainMin.class);
			//set Mapper and Reduce class
			job.setMapperClass(Map.class);
			job.setReducerClass(Reduce.class);
			//set output key value class
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(NullWritable.class);
		//set input and output path
		FileInputFormat.setInputPaths(job, new Path("hdfs://namenode:9000/wordCount"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://namenode:9000/wordCount/out"));
		
		job.waitForCompletion(true);
	}
	
	public static class Map extends Mapper<NullWritable, NullWritable, NullWritable,NullWritable>{
		
	}
	public static class Reduce extends Reducer<NullWritable, NullWritable, NullWritable, NullWritable>{
		
	}
}
