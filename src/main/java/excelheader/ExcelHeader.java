package excelheader;

/**
 * @ClassName: ExcelHeader
 * @Description:
 * @Author: lin
 * @Date: 2020/6/24 9:26
 * History:
 * @<version> 1.0
 */
public class ExcelHeader implements Comparable<ExcelHeader>{

    /**
     * excel的标题名称
     */
    private String title;
    /**
     * 每一个标题的顺序
     */
    private int order;
    /**
     * 说对应方法名称
     */
    private String methodName;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public int compareTo(ExcelHeader o) {
        return Integer.compare(order, o.order);
    }
    public ExcelHeader(String title, int order, String methodName) {
        super();
        this.title = title;
        this.order = order;
        this.methodName = methodName;
    }
}
