package cn.pxl.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//配置化自动更新的MessageSources实现
//1.获取资源位置（文件）
//2.初始化 Properties 对象
//3.实现resolveCode方法
//4.监听资源文件（JAVA NIO 2 WatchService）
//5.线程池处理文件编号，采用异步方式执行监听。
//6.重新装载 Properties 对象
public class DynamicMessageFormat extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String RESOURCE_LOCATION = "/META-INF/messages.properties";
    private static final String RESOURCE_FILE_NAME = "messages.properties";
    private static final String ENCODING = "UTF-8";
    private final Resource messagePropertyResource;

    private final Properties messageProperties;
    private final ExecutorService executorService;

    private ResourceLoader resourceLoader;

    public DynamicMessageFormat() {
        this.messagePropertyResource = getMessagePropertyResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        //4.监听资源文件（JAVA NIO 2 WatchService）
        onMessagePropertyChange();
    }

    private void onMessagePropertyChange() {
        Resource messagePropertyResource = this.messagePropertyResource;
        if (messagePropertyResource.isFile()) {
            try {
                File file = messagePropertyResource.getFile();
                Path path = file.toPath();
                //获取上一层目录
                Path parentPath = path.getParent();
                //获取当前默认文件系统
                FileSystem defaultFileSystem = FileSystems.getDefault();
                //新建 WatchService
                WatchService watchService = defaultFileSystem.newWatchService();
                //注册 WatchService 到 parentPath ， 并且关心修改事件
                parentPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                dealChange(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //异步处理资源文件变化事件
    private void dealChange(WatchService watchService) {
        executorService.submit(()->{
            while (true){
                WatchKey watchKey = watchService.take();
                try {
                    //watchKey 是否有效？
                    if (watchKey.isValid()) {
                        for (WatchEvent<?> pollEvent : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            Path dirPath = (Path) watchable;
                            //事件所关联的对象即注册目录的子文件（或子文件夹）
                            //事件发生源的相对路径
                            Path contextPath = (Path) pollEvent.context();
                            if(RESOURCE_FILE_NAME.equals(contextPath.getFileName().toString())){
                                //处理为绝对路径
                                Path resolvePath = dirPath.resolve(contextPath);
                                System.out.println("修改的文件：" + resolvePath);
                                //重新装载文件
                                File file = resolvePath.toFile();
                                //最新的修改后的 properties
                                Properties newProperties = loadMessageProperties(new FileReader(file));
                                //新 properties 覆盖原有的 properties
                                synchronized (messageProperties){
                                    this.messageProperties.clear();
                                    this.messageProperties.putAll(newProperties);
                                }
                            }
                        }
                    }
                }finally {
                    //重置 watchKey
                    if(watchKey != null){
                        watchKey.reset();
                    }
                }
            }
        });
    }

    //确定文件的位置。
    private Resource getMessagePropertyResource(){
        ResourceLoader resourceLoader = getResourceLoader();
        //1.获取资源位置（文件）
        return resourceLoader.getResource(RESOURCE_LOCATION);
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertyResource, ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadMessageProperties(Reader reader){
        //2.初始化 Properties 对象
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicMessageFormat dynamicMessageFormat = new DynamicMessageFormat();
        for (int i = 0; i < 100; i++) {
            String message = dynamicMessageFormat.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000);
        }
    }

    private ResourceLoader getResourceLoader(){
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    //获取 MessageFormat
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if(StringUtils.hasText(messageFormatPattern)){
            return new MessageFormat(messageFormatPattern,locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
