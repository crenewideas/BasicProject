package cn.pxl.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

public class ResourceUtils {
    public static String getContent(Resource resource,String encoding){
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        //字符输入流
        Reader reader = null;
        try {
            reader = encodedResource.getReader();
            return IOUtils.toString(reader) ;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
