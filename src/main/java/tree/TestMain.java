package tree;

import excel.Column;
import excel.ExcelTool;
import excel.TitleEntity;

import java.util.*;

/**
 * @ClassName: TestMain
 * @Description:
 * @Author: lin
 * @Date: 2020/7/7 15:25
 * History:
 * @<version> 1.0
 */
public class TestMain {
    public static void main(String[] args) {

//        List<Map<String,String>> titleList=new ArrayList<>();
//        //多条数据
//        Map<String,String> titleMap=new HashMap<>(16);

//        titleMap.put("id","1");
//        //父级id
//        titleMap.put("pid","0");
//        titleMap.put("content","项目");
//        titleMap.put("fieldName","u_project");
//
//
//        Map<String,String> titleMap1=new HashMap<>(16);
//        titleMap1.put("id","2");
//        titleMap1.put("pid","0");
//        //相当于表头
//        titleMap1.put("content","场线");
//        titleMap1.put("fieldName","u_field_line");
//
//        Map<String,String> titleMap2=new HashMap<>(16);
//        titleMap2.put("id","3");
//        titleMap2.put("pid","0");
//        titleMap2.put("content","代次");
//        titleMap2.put("fieldName","u_generation");
//
//        Map<String,String> titleMap3=new HashMap<>(16);
//        titleMap3.put("id","4");
//        titleMap3.put("pid","0");
//        titleMap3.put("content","种源");
//        titleMap3.put("fieldName","u_provenance");
//
//        Map<String,String> titleMap4=new HashMap<>(16);
//        titleMap4.put("id","5");
//        titleMap4.put("pid","0");
//        titleMap4.put("content","合同状态");
//        titleMap4.put("fieldName","u_agreement_status");
//        Map<String,String> titleMap5=new HashMap<>(16);
//
//        titleMap5.put("id","6");
//        titleMap5.put("pid","0");
//        titleMap5.put("content","猪场状态");
//        titleMap5.put("fieldName","u_pig_farm_status");
//        Map<String,String> titleMap6=new HashMap<>(16);
//        titleMap6.put("id","7");
//        titleMap6.put("pid","0");
//        titleMap6.put("content","规模(头)");
//        titleMap6.put("fieldName","u_scope");
//
//        Map<String,String> titleMap7=new HashMap<>(16);
//
//        titleMap7.put("id","8");
//        titleMap7.put("pid","0");
//        titleMap7.put("content","产床数");
//        titleMap7.put("fieldName","u_delivery_BedNum");
//
//
//        Map<String,String> titleMap8=new HashMap<>(16);
//
//
//        titleMap8.put("id","9");
//        titleMap8.put("pid","0");
//        titleMap8.put("content","50kg引种日期");
//        titleMap8.put("fieldName","u_fifty_int_start_time");
//
//
//        Map<String,String> titleMap9=new HashMap<>(16);
//        titleMap9.put("id","10");
//        titleMap9.put("pid","0");
//        titleMap9.put("content","50kg截止引种日期");
//        titleMap9.put("fieldName","u_fifty_tnt_end_time");
//
//        Map<String,String> titleMap10=new HashMap<>(16);
//
//
//        titleMap10.put("id","11");
//        titleMap10.put("pid","0");
//        titleMap10.put("content","竣工日期");
//        titleMap10.put("fieldName","u_completion_time");
//
//        Map<String,String> titleMap11=new HashMap<>(16);
//
//        titleMap11.put("id","12");
//        titleMap11.put("pid","0");
//        titleMap11.put("content","第1周");
//        titleMap11.put("fieldName","u_week");
//        Map<String,String> titleMap12=new HashMap<>(16);
//
//        titleMap12.put("id","13");
//        titleMap12.put("pid","0");
//        titleMap12.put("content","第2周");
//        titleMap12.put("fieldName","u_week");
//        Map<String,String> titleMap13=new HashMap<>(16);
//
//        titleMap13.put("id","14");
//        titleMap13.put("pid","0");
//        titleMap13.put("content","第3周");
//        titleMap13.put("fieldName","u_week");
//
//        Map<String,String> titleMap14=new HashMap<>(16);
//
//        titleMap14.put("id","15");
//        titleMap14.put("pid","0");
//        titleMap14.put("content","第4周");
//        titleMap14.put("fieldName","u_week");
//        Map<String,String> titleMap15=new HashMap<>(16);
//
//        titleMap15.put("id","16");
//        titleMap15.put("pid","0");
//        titleMap15.put("content","第5周");
//        titleMap15.put("fieldName","u_week");
//
//
//        titleList.add(titleMap);
//        titleList.add(titleMap1);
//        titleList.add(titleMap2);
//        titleList.add(titleMap3);
//        titleList.add(titleMap4);
//        titleList.add(titleMap5);
//        titleList.add(titleMap6);
//        titleList.add(titleMap7);
//        titleList.add(titleMap8);
//        titleList.add(titleMap9);
//        titleList.add(titleMap10);
//        titleList.add(titleMap11);
//        titleList.add(titleMap12);
//        titleList.add(titleMap13);
//        titleList.add(titleMap14);
//        titleList.add(titleMap15);


        List<TitleEntity> titleList=new ArrayList<>();
        //多条数据
//        Map<String,String> titleMap=new HashMap<>(16);

        Map<String, String> map = ExcelHeader.INTRODUCTION_COLLECT_DATA_TEST_HEADER;

        int t =1;
        for (Map.Entry<String, String> e: map.entrySet()) {
            TitleEntity titleEntity = new TitleEntity();
            titleEntity.setT_id(t+"");
            titleEntity.setT_pid("0");
            titleEntity.setT_content(e.getValue());
            titleEntity.setT_fielName(e.getKey());
            titleList.add(titleEntity);
            t++;
        }
//

        // 单级的 行内数据
        List<Map<String,String>> rowList=new ArrayList<>();
        for(int i=0;i<7;i++){
            Map<String,String> m= new HashMap<>(16);
            m.put("u_project","山东菏泽董官屯项目"+i);
            m.put("u_provenance","鲁西北内部"+i);
            m.put("u_agreement_status","已签"+i);
            m.put("u_pig_farm_status","A类-930标准场"+i);
            m.put("u_scope","规模(头)"+i);
            m.put("u_delivery_BedNum","产床数"+i);
            m.put("u_fifty_int_start_time","50kg引种日期"+i);
            m.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
            m.put("u_completion_time","竣工日期"+i);
            m.put("u_week",i+"");


            Map<String,String> m1= new HashMap<>(16);
            m1.put("u_project","山东菏泽董官屯项目"+i);
            m1.put("u_provenance","鲁西北内部"+i);
            m1.put("u_agreement_status","已签"+i);
            m1.put("u_pig_farm_status","A类-930标准场"+i);
            m1.put("u_scope","规模(头)"+i);
            m1.put("u_delivery_BedNum","产床数"+i);
            m1.put("u_fifty_int_start_time","50kg引种日期"+i);
            m1.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
            m1.put("u_completion_time","竣工日期"+i);
            m1.put("u_week",i+"");




            Map<String,String> m2= new HashMap<>(16);

            m2.put("u_field_line","场线"+i);
            m2.put("u_generation","GP"+i);
            m2.put("u_pig_farm_status", "A类-930标准场"+i);
            m2.put("u_scope","规模(头)"+i);
            m2.put("u_delivery_BedNum","产床数"+i);
            m2.put("u_fifty_int_start_time","50kg引种日期"+i);
            m2.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
            m2.put("u_completion_time","竣工日期"+i);
            m2.put("u_week",i+"");

            Map<String,String> m3= new HashMap<>(16);
            m3.put("u_field_line","场线"+i);
            m3.put("u_generation","GP"+i);
            m3.put("u_pig_farm_status", "A类-930标准场"+i);
            m3.put("u_scope","规模(头)"+i);
            m3.put("u_delivery_BedNum","产床数"+i);
            m3.put("u_fifty_int_start_time","50kg引种日期"+i);
            m3.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
            m3.put("u_completion_time","竣工日期"+i);
            m3.put("u_week",i+"");

            rowList.add(m);
            rowList.add(m2);
            rowList.add(m3);

        }



        ExcelTool excelTool = new ExcelTool("实体数据 多级表头表格",20,20);
        List<Column>  titleData= null;
        try {

            titleData = excelTool.columnTransformerThree(titleList,"t_id","t_pid","t_content",
                    "t_fielName","0");
            //导出表格
            excelTool.exportExcel(titleData, rowList,"D://outExcelThree.xlsx",
                    true,false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
