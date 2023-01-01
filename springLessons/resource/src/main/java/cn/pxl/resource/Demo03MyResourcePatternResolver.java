package cn.pxl.resource;

import cn.pxl.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;

//自定义 ResourcePatternResolver 示例
public class Demo03MyResourcePatternResolver {
    public static void main(String[] args) throws IOException {
//        patternResolver();
        myPatternResolver();
    }


    // 利用 PathMatchingResourcePatternResolver 解析出匹配的所有文件
    private static void patternResolver() throws IOException {
        //读取当前包下所有的 .java 文件 *.java
        String currentPackagePath = "/"+System.getProperty("user.dir") + "/springLessons/resource/src/main/java/cn/pxl/resource/";
        String locationPattern = currentPackagePath + "*.java";
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        Resource[] resources = pathMatchingResourcePatternResolver.getResources(locationPattern);
        for (Resource resource : resources) {
            String content = ResourceUtils.getContent(resource, "UTF-8");
            System.out.println(content);
            System.out.println("=================================");
        }
    }

    // 利用 自定义 PathMatching 解析出匹配的所有文件
    private static void myPatternResolver() throws IOException {
        //读取当前包下所有的 .java 文件 *.java
        String currentPackagePath = "/"+System.getProperty("user.dir") + "/springLessons/resource/src/main/java/cn/pxl/resource/";
        String locationPattern = currentPackagePath + "*.java";
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        pathMatchingResourcePatternResolver.setPathMatcher(new MyPathMatching());
        Resource[] resources = pathMatchingResourcePatternResolver.getResources(locationPattern);
        for (Resource resource : resources) {
            String content = ResourceUtils.getContent(resource, "UTF-8");
            System.out.println(content);
            System.out.println("=================================");
        }
    }

    // 自定义 PathMatching 路径匹配器，匹配符合条件的所有文件。
    private static class MyPathMatching implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            //
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
