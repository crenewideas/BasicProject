package cn.pxl.resource;

import cn.pxl.resource.util.ResourceUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

//Spring 资源加载器 ： DefaultResourceLoader 的使用。
public class Demo02DefaultResourceLoader {
    public static void main(String[] args) {
        String currentPath = "/"+System.getProperty("user.dir") + "/springLessons/resource/src/main/java/cn/pxl/resource/Demo01EncodedAndFileSystemResource.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(currentPath);
        String content = ResourceUtils.getContent(resource, "UTF-8");
        System.out.println(content);
    }
}
