package testexcel;

import java.io.*;
import java.util.Properties;

/**
 * @ClassName: GetFilePlace
 * @Description:
 * @Author: lin
 * @Date: 2020/6/24 13:52
 * History:
 * @<version> 1.0
 */
public class GetFilePlace {
    /**
     * 读取文件，获取excel保存的根目录
     * @return  excel保存的根目录
     */
    public   String getFilePath()
    {
        //获得tomcat所在的工作路径
        String dir = System.getProperty("user.dir");

        //获取到存储了文件存储位置的filedir.properties 文件路径
        String realDir = dir + File.separator + "src" + File.separator +"META-INF" + File.separator + "filedir.properties";

        /*String realDir = dir.substring(0, dir.length()-4) + File.separator +"webapps" + File.separator + "generateExcels"
                      + File.separator + "classes" + File.separator + "META-INF" + File.separator + "config" + File.separator + "filedir.properties";
    */
        return realDir;
    }

    /**
     * 获取filePath路径【properities文件】中key对应的值，
     * @param filePath properities文件路径【包含properities文件】
     * @param key 要查找的key值
     * @return key对应的value
     */
    public   String GetValueByKey(String filePath, String key)
    {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            String value = pps.getProperty(key);
            in.close();
            return value;

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询properities文件中可以对应的存储地点
     * @param key 查询主键
     * @return    key对应的存储地址
     */
    public  String getFileDirFromProperties(String key)
    {
        return GetValueByKey(getFilePath(),key);
    }

    public static void main(String[] args)
    {
        System.out.println(new GetFilePlace().getFileDirFromProperties("filePath"));
    }
}
