package excelheader;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @ClassName: TemplateFileUtil
 * @Description: 获取模板的工具栏
 * @Author: lin
 * @Date: 2020/6/24 9:28
 * History:
 * @<version> 1.0
 */
public class TemplateFileUtil {
    public static FileInputStream getTemplates(String tempName)
            throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:excel-templates/"+tempName));
    }

}
