package tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExcelHeader
 * @Description: 导出excel 文件请求header
 * @Author: lin
 * @Date: 2020/6/20 12:58
 * History:
 * @<version> 1.0
 */
public class ExcelHeader {

    /**
     * 工程看板—工程建设-场线header
     */
    public static Map<String, String> FIELD_LINE_HEADER = new LinkedHashMap<>();

    public static List<String> FIELD_LINE_HEADER_LIST;

    static {
        FIELD_LINE_HEADER.put("seq","序号");
        FIELD_LINE_HEADER.put("fieldLineId","场线id");
        FIELD_LINE_HEADER.put("projectCode","项目编码");
        FIELD_LINE_HEADER.put("projectShortName","项目名称");

        FIELD_LINE_HEADER.put("fieldLineName","场线名");
        FIELD_LINE_HEADER.put("baseConstructionPercentage","基础设施(%)*");
        FIELD_LINE_HEADER.put("mainProjectPercentage","主体工程(%)*");
        FIELD_LINE_HEADER.put("breedingEquipmentPercentage","养殖设备(%)*");
        FIELD_LINE_HEADER.put("planFinishTime","计划完成时间");
        FIELD_LINE_HEADER.put("actualFinishTime","预计/实际完成时间");
        FIELD_LINE_HEADER.put("generation","代次");
        FIELD_LINE_HEADER.put("scale","规模");

        FIELD_LINE_HEADER_LIST = new ArrayList<>(FIELD_LINE_HEADER.size());

        FIELD_LINE_HEADER_LIST.add("seq");
        FIELD_LINE_HEADER_LIST.add("fieldLineId");
        FIELD_LINE_HEADER_LIST.add("projectCode");
        FIELD_LINE_HEADER_LIST.add("projectShortName");
        FIELD_LINE_HEADER_LIST.add("fieldLineName");
        FIELD_LINE_HEADER_LIST.add("baseConstructionPercentage");
        FIELD_LINE_HEADER_LIST.add("mainProjectPercentage");
        FIELD_LINE_HEADER_LIST.add("breedingEquipmentPercentage");
        FIELD_LINE_HEADER_LIST.add("planFinishTime");
        FIELD_LINE_HEADER_LIST.add("actualFinishTime");
        FIELD_LINE_HEADER_LIST.add("generation");
        FIELD_LINE_HEADER_LIST.add("scale");
    }



    /**
     * 引种汇总—数据header
     */
    public static Map<String, String> INTRODUCTION_COLLECT_DATA_HEADER = new LinkedHashMap<>();

    public static List<String> INTRODUCTION_COLLECT_DATA_HEADER_LIST;

    static {
        INTRODUCTION_COLLECT_DATA_HEADER.put("seq","序号");
        INTRODUCTION_COLLECT_DATA_HEADER.put("introFiledLineId","引种计划生成场线id");
        INTRODUCTION_COLLECT_DATA_HEADER.put("projectName","项目名称");
        INTRODUCTION_COLLECT_DATA_HEADER.put("fieldLineName","场线");
        INTRODUCTION_COLLECT_DATA_HEADER.put("generation","代次");
        INTRODUCTION_COLLECT_DATA_HEADER.put("provenance","种源");
        INTRODUCTION_COLLECT_DATA_HEADER.put("agreementStatus","合同状态");
        INTRODUCTION_COLLECT_DATA_HEADER.put("pigFarmStatus","猪场状态");
        INTRODUCTION_COLLECT_DATA_HEADER.put("scope","规模");
        INTRODUCTION_COLLECT_DATA_HEADER.put("deliveryBedNum","产床数");
        INTRODUCTION_COLLECT_DATA_HEADER.put("fiftyIntStartTime","50kg引种日期");
        INTRODUCTION_COLLECT_DATA_HEADER.put("fiftyIntEndTime","50kg截至引种日期");
        INTRODUCTION_COLLECT_DATA_HEADER.put("completionTime","竣工日期");
        INTRODUCTION_COLLECT_DATA_HEADER.put("verticalName","纵队名称");
        INTRODUCTION_COLLECT_DATA_HEADER.put("introductionBatch","批次");
        INTRODUCTION_COLLECT_DATA_HEADER.put("weekIntDay","周引种天数");


        INTRODUCTION_COLLECT_DATA_HEADER_LIST = new ArrayList<>(INTRODUCTION_COLLECT_DATA_HEADER.size());

        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("seq");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("introFiledLineId");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("projectName");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("fieldLineName");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("generation");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("provenance");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("agreementStatus");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("pigFarmStatus");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("scope");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("deliveryBedNum");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("fiftyIntStartTime");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("fiftyIntEndTime");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("completionTime");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("verticalName");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("introductionBatch");
        INTRODUCTION_COLLECT_DATA_HEADER_LIST.add("weekIntDay");
    }


    /**
     * 引种汇总—测试数据header(勿用)
     */
    public static Map<String, String> INTRODUCTION_COLLECT_DATA_TEST_HEADER = new LinkedHashMap<>();

    public static List<String> INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST;

    static {
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_project","项目名称");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_field_line","场线");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_generation","代次");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_provenance","种源");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_agreement_status","合同状态");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_pig_farm_status","猪场状态");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_scope","规模");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_delivery_BedNum","产床数");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_fifty_int_start_time","50kg引种日期");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_fifty_tnt_end_time","50kg截至引种日期");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER.put("u_completion_time","竣工日期");



        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST = new ArrayList<>(INTRODUCTION_COLLECT_DATA_TEST_HEADER.size());

        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_project");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_field_line");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_generation");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_provenance");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_agreement_status");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_pig_farm_status");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_scope");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_delivery_BedNum");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_fifty_int_start_time");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_fifty_tnt_end_time");
        INTRODUCTION_COLLECT_DATA_TEST_HEADER_LIST.add("u_completion_time");

    }





    /**
     * 引种计划汇总 —月相关数据header
     */
    public static Map<String, String> INTRODUCTION_COLLECT_MOTHS_DATA_HEADER = new LinkedHashMap<>();

    public static List<String> INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST;

    static {
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("seq","序号");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("projectName","项目名称");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("fieldLineName","场线");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("generation","代次");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("provenance","种源");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("agreementStatus","合同状态");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("pigFarmStatus","猪场状态");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("scope","规模");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("deliveryBedNum","产床数");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("fiftyIntStartTime","50kg引种日期");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("fiftyIntEndTime","50kg截至引种日期");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.put("completionTime","竣工日期");



        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST = new ArrayList<>(INTRODUCTION_COLLECT_MOTHS_DATA_HEADER.size());

        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("seq");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("projectName");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("fieldLineName");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("generation");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("provenance");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("agreementStatus");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("pigFarmStatus");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("scope");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("deliveryBedNum");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("fiftyIntStartTime");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("fiftyIntEndTime");
        INTRODUCTION_COLLECT_MOTHS_DATA_HEADER_LIST.add("completionTime");
    }
}
