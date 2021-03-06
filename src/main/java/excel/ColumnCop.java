package excel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Column
 * @Description:
 * @Author: lin
 * @Date: 2020/6/23 16:10
 * History:
 * @<version> 1.0
 */
public class ColumnCop {
    /**
     * 单元格内容
     */
    private String content;
    /**
     * 字段名称，用户导出表格时反射调用
     */
    private String fieldName;
    /**
     * 这个单元格的集合
     */
    private List<ColumnCop> listTpamscolumn = new ArrayList<ColumnCop>();

    int totalRow;
    int totalCol;
    /**
     * excel第几行
     */
    int row;
    /**
     * excel第几列
     */
    int col;
    /**
     * 跨多少行
     */
    int rLen;
    /**
     * excel跨多少列
     */
    int cLen;
    /**
     *是否有子节点
     */
    private boolean hasChildRen;
    /**
     * 树的级别 从0开始
     */
    private int treeStep;
    /**
     * id
     */
    private String id;
    /**
     * 父级id
     */
    private String pid;
    public ColumnCop(){};
    public ColumnCop(String content, String fieldName){
        this.content = content;
        this.fieldName = fieldName;
    }

    public ColumnCop(String fieldName, String content, int treeStep) {
        this.treeStep = treeStep;
        this.fieldName = fieldName;
        this.content = content;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalCol() {
        return totalCol;
    }

    public void setTotalCol(int totalCol) {
        this.totalCol = totalCol;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasChildRen() {
        return hasChildRen;
    }

    public void setHasChildRen(boolean hasChildRen) {
        this.hasChildRen = hasChildRen;
    }

    public int getTreeStep() {
        return treeStep;
    }

    public void setTreeStep(int treeStep) {
        this.treeStep = treeStep;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<ColumnCop> getListTpamscolumn() {
        return listTpamscolumn;
    }

    public void setListTpamscolumn(List<ColumnCop> listTpamscolumn) {
        this.listTpamscolumn = listTpamscolumn;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getrLen() {
        return rLen;
    }

    public void setrLen(int rLen) {
        this.rLen = rLen;
    }

    public int getcLen() {
        return cLen;
    }

    public void setcLen(int cLen) {
        this.cLen = cLen;
    }
}
