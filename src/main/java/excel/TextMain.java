package excel;

import tree.ExcelHeader;

import java.util.*;

/**
 * @ClassName: TextMain
 * @Description: 测试类
 * @Author: lin
 * @Date: 2020/6/23 16:23
 * History:
 * @<version> 1.0
 */
public class TextMain {

    public static void main(String[] args) {
        //单级的表头
//        Map<String,String> map=new HashMap<>(16);
//        map.put("登录名","u_login_id");
//        Map<String,String>  map1=new HashMap<>(16);
//        map1.put("用户名","u_name");
//        Map<String,String>  map2=new HashMap<>(16);
//        map2.put("角色","u_role");
//        Map<String,String>  map3=new HashMap<>(16);
//        map3.put("部门","u_dep");
//        Map<String,String>  map4=new HashMap<>(16);
//        map4.put("用户类型","u_type");
//        List<Map<String,String>> titleList=new ArrayList<>();
//        titleList.add(map);
//        titleList.add(map1);
//        titleList.add(map2);
//        titleList.add(map3);
//        titleList.add(map4);
//
//        //单级的 行内数据
//        List<Map<String,String>> rowList=new ArrayList<>();
//        for(int i=0;i<7;i++){
//            Map m= new HashMap<String,String>();
//            m.put("u_login_id","登录名"+i);
//            m.put("u_name","张三"+i);
//            m.put("u_role","角色"+i);
//            m.put("u_dep","部门"+i);
//            m.put("u_type","用户类型"+i);
//
//            rowList.add(m);
//        }
//        ExcelTool excelTool = new ExcelTool("单级表头的表格",15,20);
//        List<Column>  titleData=excelTool.columnTransformer(titleList);
//        try {
//            excelTool.exportExcel(titleData,rowList,"D://outExcel1.xlsx",true,false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        /**
         * ======================================= List<Map>数据 多级表头,数据如下:
         */
        //List<Map>数据 多级表头,数据如下:
        //        登录名  姓名       aa
        //                      角色    部门
//        List<Map<String,String>> titleList=new ArrayList<>();
//        Map<String,String> titleMap=new HashMap<String,String>();
//        titleMap.put("id","11");
//        titleMap.put("pid","0");
//        titleMap.put("content","登录名");
//        titleMap.put("fielName","u_login_id");
//        Map<String,String> titleMap1=new HashMap<String,String>();
//        titleMap1.put("id","1");
//        titleMap1.put("pid","0");
//        titleMap1.put("content","姓名");
//        titleMap1.put("fielName","u_name");
//        Map<String,String> titleMap2=new HashMap<String,String>();
//        titleMap2.put("id","2");
//        titleMap2.put("pid","0");
//        titleMap2.put("content","角色加部门");
//        titleMap2.put("fielName",null);
//        Map<String,String> titleMap3=new HashMap<String,String>();
//        titleMap3.put("id","3");
//        titleMap3.put("pid","2");
//        titleMap3.put("content","角色");
//        titleMap3.put("fielName","u_role");
//        Map<String,String> titleMap4=new HashMap<String,String>();
//        titleMap4.put("id","4");
//        titleMap4.put("pid","2");
//        titleMap4.put("content","部门");
//        titleMap4.put("fielName","u_dep");
//        Map<String,String> titleMap5=new HashMap<String,String>();
//        titleMap5.put("id","22");
//        titleMap5.put("pid","0");
//        titleMap5.put("content","角色加部门1");
//        titleMap5.put("fielName",null);
//        Map<String,String> titleMap6=new HashMap<String,String>();
//        titleMap6.put("id","22_1");
//        titleMap6.put("pid","22");
//        titleMap6.put("content","角色1");
//        titleMap6.put("fielName","u_role");
//        Map<String,String> titleMap7=new HashMap<String,String>();
//        titleMap7.put("id","22_2");
//        titleMap7.put("pid","22");
//        titleMap7.put("content","部门1");
//        titleMap7.put("fielName","u_dep");
//        titleList.add(titleMap);
//        titleList.add(titleMap1);
//        titleList.add(titleMap2);
//        titleList.add(titleMap3);
//        titleList.add(titleMap4);
//        titleList.add(titleMap5);
//        titleList.add(titleMap6);
//        titleList.add(titleMap7);
//       // 单级的 行内数据
//        List<Map<String,String>> rowList=new ArrayList<>();
//        for(int i=0;i<7;i++){
//            Map m= new HashMap<String,String>();
//            m.put("u_login_id","登录名"+i);
//            m.put("u_name","张三"+i);
//            m.put("u_role","角色"+i);
//            m.put("u_dep","部门"+i);
//            m.put("u_type","用户类型"+i);
//            rowList.add(m);
//        }
//        ExcelTool excelTool = new ExcelTool("List<Map>数据 多级表头表格",20,20);
//        List<Column>  titleData= null;
//        try {
//            titleData = excelTool.columnTransformer(titleList,"id",
//                    "pid","content","fielName","0");
//
//            //导出表格
//            excelTool.exportExcel(titleData,rowList,"D://outExcel2.xlsx",
//                    true,false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        /**
         * 测试分层级
         */


//        List<Map<String,String>> titleList=new ArrayList<>();
//        //多条数据
//        Map<String,String> titleMap=new HashMap<>(16);
//        titleMap.put("id","1");
//        //父级id
//        titleMap.put("pid","0");
//        titleMap.put("content","项目");
//        titleMap.put("fielName","u_project");
//
//
//        Map<String,String> titleMap1=new HashMap<>(16);
//        titleMap1.put("id","2");
//        titleMap1.put("pid","0");
//        //相当于表头
//        titleMap1.put("content","场线");
//        titleMap1.put("fielName","u_field_line");
//
//        Map<String,String> titleMap2=new HashMap<>(16);
//        titleMap2.put("id","3");
//        titleMap2.put("pid","0");
//        titleMap2.put("content","代次");
//        titleMap2.put("fielName","u_generation");
//
//        Map<String,String> titleMap3=new HashMap<>(16);
//        titleMap3.put("id","4");
//        titleMap3.put("pid","0");
//        titleMap3.put("content","种源");
//        titleMap3.put("fielName","u_provenance");
//
//        Map<String,String> titleMap4=new HashMap<>(16);
//        titleMap4.put("id","5");
//        titleMap4.put("pid","0");
//        titleMap4.put("content","合同状态");
//        titleMap4.put("fielName","u_agreement_status");
//        Map<String,String> titleMap5=new HashMap<>(16);
//
//        titleMap5.put("id","6");
//        titleMap5.put("pid","0");
//        titleMap5.put("content","猪场状态");
//        titleMap5.put("fielName","u_pig_farm_status");
//        Map<String,String> titleMap6=new HashMap<>(16);
//        titleMap6.put("id","7");
//        titleMap6.put("pid","0");
//        titleMap6.put("content","规模(头)");
//        titleMap6.put("fielName","u_scope");
//
//        Map<String,String> titleMap7=new HashMap<>(16);
//
//        titleMap7.put("id","8");
//        titleMap7.put("pid","0");
//        titleMap7.put("content","产床数");
//        titleMap7.put("fielName","u_delivery_BedNum");
//
//
//        Map<String,String> titleMap8=new HashMap<>(16);
//
//
//        titleMap8.put("id","9");
//        titleMap8.put("pid","0");
//        titleMap8.put("content","50kg引种日期");
//        titleMap8.put("fielName","u_fifty_int_start_time");
//
//
//        Map<String,String> titleMap9=new HashMap<>(16);
//        titleMap9.put("id","10");
//        titleMap9.put("pid","0");
//        titleMap9.put("content","50kg截止引种日期");
//        titleMap9.put("fielName","u_fifty_tnt_end_time");
//
//        Map<String,String> titleMap10=new HashMap<>(16);
//
//
//        titleMap10.put("id","11");
//        titleMap10.put("pid","0");
//        titleMap10.put("content","竣工日期");
//        titleMap10.put("fielName","u_completion_time");
//
//        Map<String,String> titleMap11=new HashMap<>(16);
//
//        titleMap11.put("id","12");
//        titleMap11.put("pid","0");
//        titleMap11.put("content","第1周");
//        titleMap11.put("fielName","u_week");
//        Map<String,String> titleMap12=new HashMap<>(16);
//
//        titleMap12.put("id","13");
//        titleMap12.put("pid","0");
//        titleMap12.put("content","第2周");
//        titleMap12.put("fielName","u_week");
//        Map<String,String> titleMap13=new HashMap<>(16);
//
//        titleMap13.put("id","14");
//        titleMap13.put("pid","0");
//        titleMap13.put("content","第3周");
//        titleMap13.put("fielName","u_week");
//
//        Map<String,String> titleMap14=new HashMap<>(16);
//
//        titleMap14.put("id","15");
//        titleMap14.put("pid","0");
//        titleMap14.put("content","第4周");
//        titleMap14.put("fielName","u_week");
//        Map<String,String> titleMap15=new HashMap<>(16);
//
//        titleMap15.put("id","16");
//        titleMap15.put("pid","0");
//        titleMap15.put("content","第5周");
//        titleMap15.put("fielName","u_week");
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
//        // 单级的 行内数据
//        List<Map<String,String>> rowList=new ArrayList<>();
//        for(int i=0;i<7;i++){
//            Map<String,String> m= new HashMap<>(16);
//            m.put("u_project","山东菏泽董官屯项目"+i);
//            m.put("u_field_line","场线"+i);
//            m.put("u_generation","GP"+i);
//            m.put("u_provenance","鲁西北内部"+i);
//            m.put("u_agreement_status","已签"+i);
//            m.put("u_pig_farm_status","A类-930标准场"+i);
//            m.put("u_scope","规模(头)"+i);
//            m.put("u_delivery_BedNum","产床数"+i);
//            m.put("u_fifty_int_start_time","50kg引种日期"+i);
//            m.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
//            m.put("u_completion_time","竣工日期"+i);
//            m.put("u_week",i+"");
//
//            rowList.add(m);
//        }
//        ExcelTool excelTool = new ExcelTool("List<Map>数据 多级表头表格",20,20);
//        List<Column>  titleData= null;
//        try {
//            titleData = excelTool.columnTransformerTwo(titleList,"id",
//                    "pid","content","fielName","0");
//
//            //导出表格
//            excelTool.exportExcel(titleData,rowList,"D://outExcel3.xlsx",
//                    true,false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




        /**
         * 实体类（entity）数据 多级表头
         * ======================================================================
         */
        //实体类（entity）数据 多级表头,数据如下:
        //        登录名  姓名       aa
        //                      角色    部门

        List<TitleEntity> titleList=new ArrayList<>();
        TitleEntity titleEntity0=new TitleEntity("0",null,"总表",null);

        TitleEntity titleEntity=new TitleEntity("11","0","登录名2","u_login_id");

        TitleEntity titleEntity1=new TitleEntity("1","0","姓名","u_name");
        TitleEntity titleEntity11=new TitleEntity("1_1","1","姓名1","u_name");

        TitleEntity titleEntity2=new TitleEntity("2","0","角色和部门",null);
        TitleEntity titleEntity3=new TitleEntity("3","2","角色","u_role");
        TitleEntity titleEntity4=new TitleEntity("4","2","部门","u_dep");

        TitleEntity titleEntity5=new TitleEntity("33","0","角色加部门1",null);
        TitleEntity titleEntity6=new TitleEntity("33_1","33","角色33","u_role");
        TitleEntity titleEntity7=new TitleEntity("33_2","33_1","部门33","u_dep");

        TitleEntity titleEntity8=new TitleEntity("44","0","角色加部门2",null);
        TitleEntity titleEntity9=new TitleEntity("44_1","44","角色44","u_role");
        TitleEntity titleEntity10=new TitleEntity("44_2","44","部门44","u_dep");
        TitleEntity titleEntity12=new TitleEntity("44_3","44_2","44_2","u_dep");
        titleList.add(titleEntity0);
        titleList.add(titleEntity);
        titleList.add(titleEntity1);
        titleList.add(titleEntity2);
        titleList.add(titleEntity3);
        titleList.add(titleEntity4);
        titleList.add(titleEntity5);
        titleList.add(titleEntity6);
        titleList.add(titleEntity7);
        titleList.add(titleEntity8);
        titleList.add(titleEntity9);
        titleList.add(titleEntity10);
        titleList.add(titleEntity11);
        titleList.add(titleEntity12);
        //单级的 行内数据
        List<Map<String,String>> rowList=new ArrayList<>();
        for(int i=0;i<7;i++){
            Map<String,String> m= new HashMap<String,String>();
            m.put("u_login_id","登录名"+i);
            m.put("u_name","张三"+i);
            m.put("u_role","角色"+i);
            m.put("u_dep","部门"+i);
            m.put("u_type","用户类型"+i);
            rowList.add(m);
        }
        ExcelTool excelTool = new ExcelTool("实体类（entity）数据 多级表头表格",20,20);
        List<Column> titleData = null;
        try {
            titleData = excelTool.columnTransformer(titleList,"t_id","t_pid","t_content",
                    "t_fielName","0");
            excelTool.exportExcel(titleData,rowList,"D://outExcel.xlsx",true,true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //=========================================================================


//        List<Map<String,String>> titleList=new ArrayList<>();
//        //多条数据
//        Map<String,String> titleMap=new HashMap<>(16);
//
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
//
//
//
//        // 单级的 行内数据
//        List<Map<String,String>> rowList=new ArrayList<>();
//        for(int i=0;i<7;i++){
//            Map<String,String> m= new HashMap<>(16);
//            m.put("u_project" ,"山东菏泽董官屯项目"+i);
//            m.put("u_provenance","鲁西北内部"+i);
//            m.put("u_agreement_status","已签"+i);
//            m.put("u_pig_farm_status","A类-930标准场"+i);
//            m.put("u_scope","规模(头)"+i);
//            m.put("u_delivery_BedNum","产床数"+i);
//            m.put("u_fifty_int_start_time","50kg引种日期"+i);
//            m.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
//            m.put("u_completion_time","竣工日期"+i);
//            m.put("u_week",i+"");
//
//            Map<String,String> m1= new HashMap<>(16);
//            m1.put("u_project","山东菏泽董官屯项目"+i);
//            m1.put("u_provenance","鲁西北内部"+i);
//            m1.put("u_agreement_status","已签"+i);
//            m1.put("u_pig_farm_status","A类-930标准场"+i);
//            m1.put("u_scope","规模(头)"+i);
//            m1.put("u_delivery_BedNum","产床数"+i);
//            m1.put("u_fifty_int_start_time","50kg引种日期"+i);
//            m1.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
//            m1.put("u_completion_time","竣工日期"+i);
//            m1.put("u_week",i+"");
//
//
//
//
//            Map<String,String> m2= new HashMap<>(16);
//
//            m2.put("u_field_line","场线"+i);
//            m2.put("u_generation","GP"+i);
//            m2.put("u_pig_farm_status", "A类-930标准场"+i);
//            m2.put("u_scope","规模(头)"+i);
//            m2.put("u_delivery_BedNum","产床数"+i);
//            m2.put("u_fifty_int_start_time","50kg引种日期"+i);
//            m2.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
//            m2.put("u_completion_time","竣工日期"+i);
//            m2.put("u_week",i+"");
//            Map<String,String> m3= new HashMap<>(16);
//
//            m3.put("u_field_line","场线"+i);
//            m3.put("u_generation","GP"+i);
//            m3.put("u_pig_farm_status", "A类-930标准场"+i);
//            m3.put("u_scope","规模(头)"+i);
//            m3.put("u_delivery_BedNum","产床数"+i);
//            m3.put("u_fifty_int_start_time","50kg引种日期"+i);
//            m3.put("u_fifty_tnt_end_time","50kg截止引种日期"+i);
//            m3.put("u_completion_time","竣工日期"+i);
//            m3.put("u_week",i+"");
//
//
//            rowList.add(m);
//            rowList.add(m2);
//            rowList.add(m3);
//
//        }
//        ExcelTool excelTool = new ExcelTool("List<Map>数据 多级表头表格",20,20);
//        List<Column>  titleData= null;
//        try {
//            titleData = excelTool.columnTransformer(titleList,"id",
//                    "pid","content","fieldName","0");
//
//            //导出表格
//            excelTool.exportExcel(titleData, rowList,"D://outExcel222.xlsx",
//                    true,false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //=========================================================================

        //读取excel
//          ExcelTool excelTool = new ExcelTool();
//        try {
//            List<List<String>> readexecl=excelTool.getExcelValues("D://importExcel.xlsx",1);
//            List<List<Map<String,String>>> readexeclC=excelTool.getExcelMapVal("D://importExcel.xlsx",1);
//
//            int count= excelTool.hasSheetCount("D://importExcel.xlsx");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
