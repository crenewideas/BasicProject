package cn.pxl.mojo;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * mvn cn.pxl:myMavenPluginDemo:1.0-SNAPSHOT:count
 * 继承AbstractMojo、实现execute()方法、提供@goal标注。
 * @goal count
 */
public class CountMojo extends AbstractMojo {

    private static final String[] INCLUDES_DEFAULT = {"java","xml","properties"};
    /**
     * @ parameter expression="${project.basedir}"
     * @ required
     * @ readonly
     */
    private File baseDir;
    /**
     * @ parameter expression="${project.build.sourceDirectory}"
     * @ required
     * @ readonly
     */
    private File sourceDirectory;
    /**
     * @ parameter expression="${project.build.testSourceDirectory}"
     * @ required
     * @ readonly
     */
    private File testSourceDirectory;
    /**
     * @ parameter expression="${project.build.resources}"
     * @ required
     * @ readonly
     */
    private List<Resource> resources;
    /**
     * @ parameter expression="${project.build.testResources}"
     * @ required
     * @ readonly
     */
    private List<Resource> testResources;
    /**
     * @ parameter
     */
    private String[] includes;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("count插件的统计数量的方法已被执行！");
//        if(includes == null || includes.length == 0){
//            includes = INCLUDES_DEFAULT;
//        }
//        try{
//            countDir(sourceDirectory);
//            countDir(testSourceDirectory);
//            for (Resource resource : resources) {
//                countDir(new File(resource.getDirectory()));
//            }
//            for (Resource resource : testResources) {
//                countDir(new File(resource.getDirectory()));
//            }
//        }catch (IOException e){
//            throw new MojoExecutionException(e.getMessage());
//        }
    }

    private void countDir(File dir) throws IOException {
        if(dir == null || !dir.exists()){
            return;
        }
        ArrayList<File> collects = new ArrayList<>();
        collectFiles(collects,dir);
        int lines = 0;
        for (File collectFile : collects) {
            lines += collectLine(collectFile);
        }
        String path = dir.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        getLog().info(path + ":" + lines + "lines of code in " + collects.size() + " files");

    }

    private void collectFiles(ArrayList<File> collects, File file) {
        if(file.isFile()){
            for (String include : includes) {
                if (file.getName().endsWith("." + include)) {
                    collects.add(file);
                    break;
                }
            }
        }else {
            File[] files = file.listFiles();
            if(files != null){
                for (File subFile : files) {
                    collectFiles(collects,subFile);
                }
            }
        }
    }

    private int collectLine(File collectFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(collectFile));
        int line = 0;
        try {
            while (bufferedReader.ready()) {
                bufferedReader.readLine();
                line ++;
            }
        }
        finally{
            bufferedReader.close();
        }
        return line;
    }
}
