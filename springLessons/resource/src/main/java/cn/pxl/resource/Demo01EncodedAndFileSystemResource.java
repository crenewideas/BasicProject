package cn.pxl.resource;

import cn.pxl.resource.util.ResourceUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import sun.reflect.misc.ConstructorUtil;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
//带有编码的EncodedResource基本使用。
public class Demo01EncodedAndFileSystemResource {
    public static void main(String[] args) {
        String currentPath = System.getProperty("user.dir") + "/springLessons/resource/src/main/java/cn/pxl/resource/Demo01EncodedAndFileSystemResource.java";
        File file = new File(currentPath);
        //fileSystemResource -> WritableResource -> Resource
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        String content = ResourceUtils.getContent(fileSystemResource, "UTF-8");
        System.out.println(content);
    }
}
