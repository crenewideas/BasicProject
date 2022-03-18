package cn.pxl.capture01.subsection07;

import cn.pxl.capture01.common.ReadFile;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

//Optional<T> ：包装器对象，包装了T对象，那么不为空，值存在；没有包装T对象，那么称值不存在。
public class OptionalType {

    //用法
    public static void optionalTest(){
        Stream<String> stream = Arrays.stream(ReadFile.readFileIntoStringArray());
        Optional<String> findAnyOptional = stream.parallel().filter(word -> word.startsWith("Q")).findAny();
        String startWithQ = findAnyOptional.orElse("空");
        String s = findAnyOptional.orElseGet(() -> Math.random() + "");
        //如果值存在就进行输出
        findAnyOptional.ifPresent(System.out::println);
        //java 9 : ifPresentOrElse
    }

    //管道化
    public static void pipelineTest(){
        Stream<String> stream = Arrays.stream(ReadFile.readFileIntoStringArray());
        Optional<String> findAnyOptional = stream.parallel().filter(word -> word.startsWith("a")).findAny();
        //将Optional中的值 转换为大写。如果其中值为空，那么不进行转换
        Optional<String> upperCaseOptional = findAnyOptional.map(String::toUpperCase);
        //满足特定条件的值进行转换
        //Length Big Than Ten Then To Lower Case
        Optional<String> lengthBigThanTenThenToLowerCase = findAnyOptional.filter(word -> word.length() > 10).map(String::toLowerCase);
        //java 9 :将空Optional替换为一个可替代的Optional :
        //findAnyOptional.or()
        if (findAnyOptional.isPresent()) {
            //get方法只有在确定findAnyOptional有值时才能被调用，如果在没值时就调用这个方法，会报异常。
            String s = findAnyOptional.get();
        }
    }

    //创建自己的Optional对象,如取一个数的倒数
    public static Optional<Double> getReciprocal(Double x){
        return x == 0 ? Optional.empty() : Optional.of(1/x);
        //return Optional.ofNullable(1/x);//同上方代码含义一样。
    }

    //取一个数的平方根
    public static Optional<Double> squareRoot(Double x){
        return x == 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
        //return Optional.ofNullable(1/x);//同上方代码含义一样。
    }

    //使用flatMap 构建 Optional值的函数。如：获取x这个数的倒数的平方根
    public static void flatMapOptional(Double x){
        //获取倒数的optional
        Optional<Double> reciprocal = getReciprocal(x);
        //计算这个倒数的平方根
        Optional<Double> aDouble = reciprocal.flatMap(OptionalType::squareRoot);
        //只要二者有一个结果为空，那么整个结果就为空。
    }

    //将Optional对象转换为流：转换的流中具有0个或1个对象，从而使返回的结果变得很有用。
    //java 8 不支持！！
    public static void fiatOptionalToStream(){
        Optional<Double> reciprocal = getReciprocal(2D);
        //reciprocal.flatMap(Optional::stream)
    }



}
