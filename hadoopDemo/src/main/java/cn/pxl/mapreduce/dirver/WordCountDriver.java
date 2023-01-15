package cn.pxl.mapreduce.dirver;

import cn.pxl.mapreduce.mapper.WordCountMapper;
import cn.pxl.mapreduce.reduce.WordCountReudcer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        Job instance = Job.getInstance(configuration);
        instance.setJarByClass(WordCountDriver.class);
        instance.setMapperClass(WordCountMapper.class);
        instance.setReducerClass(WordCountReudcer.class);
        instance.setMapOutputKeyClass(Text.class);
        instance.setMapOutputValueClass(IntWritable.class);
        instance.setOutputKeyClass(Text.class);
        instance.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(instance,new Path(args[0]));
        FileOutputFormat.setOutputPath(instance,new Path(args[1]));
        instance.waitForCompletion(true);

    }
}
