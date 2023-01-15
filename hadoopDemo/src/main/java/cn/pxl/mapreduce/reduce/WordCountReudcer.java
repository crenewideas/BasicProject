package cn.pxl.mapreduce.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReudcer extends Reducer<Text, IntWritable,Text,IntWritable> {

    int total = 0;

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            total+=value.get();
        }
        context.write(key,new IntWritable(total));
    }
}
