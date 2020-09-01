package excel;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SysTagConf
 * @Description:
 * @Author: lin
 * @Date: 2020/7/7 10:29
 * History:
 * @<version> 1.0
 */
public class SysTagConf implements Serializable {
    /**
     * 唯一标识
     */
    private String rowGuid;
    /**
    标签名称
     */
    private String name;
    /**
     * 授权类型 0全部 1目录清单 2实施清单 3办理项
     */
    private String opType;
    /**
     * 使用层级 0不限 2省级 3地市级 4区县级
     */
    private String useLevel;
    /**
     * 排序
     */
    private float sort;
    /**
     * 父节点标识
     */
    private String parentGuid;
    /**
     * 备注
     */
    private String bakNote;
    /**
     * 创建人ID
     */
    private String createId;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新人ID
     */
    private String updateId;
    /**
     * 更新人名称
     */
    private String updateName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 父节点名称
     */
    private String parentName;
    /**
     * 子级
     */
    private List<SysTagConf> childList;


    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getUseLevel() {
        return useLevel;
    }

    public void setUseLevel(String useLevel) {
        this.useLevel = useLevel;
    }

    public float getSort() {
        return sort;
    }

    public void setSort(float sort) {
        this.sort = sort;
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }

    public String getBakNote() {
        return bakNote;
    }

    public void setBakNote(String bakNote) {
        this.bakNote = bakNote;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysTagConf> getChildList() {
        return childList;
    }

    public void setChildList(List<SysTagConf> childList) {
        this.childList = childList;
    }
}
