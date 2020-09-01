package tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExportTest
 * @Description:
 * @Author: lin
 * @Date: 2020/7/8 11:15
 * History:
 * @<version> 1.0
 */
public class ExportTest {
    public static void main(String[] args) {
        Map<String, String> map = ExcelHeader.INTRODUCTION_COLLECT_DATA_TEST_HEADER;
        List<String> collectDataHeaderList = ExcelHeader.INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST;
        Map<String,String> stringMap = new HashMap<>(16);
        int t =1;
        for (Map.Entry<String, String> e: map.entrySet()) {
            stringMap.put("id_"+t, t+"");
            stringMap.put("pid_"+t, "0");
            stringMap.put("content_"+t,e.getValue());
            stringMap.put("fieldName_"+t,e.getKey());
            t++;
        }

        System.out.println("stringMap================"+stringMap);
    }
}
