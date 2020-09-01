package excel;

/**
 * @ClassName: WeekVo
 * @Description:
 * @Author: lin
 * @Date: 2020/7/7 15:12
 * History:
 * @<version> 1.0
 */
public class WeekVo {
    /**
     * 每周
     */
    private Integer weekNum;

    /**
     * 每周数据
     */
    private Integer generationNumber;

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public Integer getGenerationNumber() {
        return generationNumber;
    }

    public void setGenerationNumber(Integer generationNumber) {
        this.generationNumber = generationNumber;
    }
}
