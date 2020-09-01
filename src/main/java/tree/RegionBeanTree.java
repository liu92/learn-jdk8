package tree;

import java.util.List;

/**
 * @ClassName: RegionBeanTree
 * @Description: 树对象
 * @Author: lin
 * @Date: 2020/7/7 9:38
 * History:
 * @<version> 1.0
 */
public class RegionBeanTree {

    /**
     * 城市编码
     */
    private String code;

    /**
     * 城市
     */
    private String label;

    /**
     * 父级id
     */
    private String pid;

    List<RegionBeanTree> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<RegionBeanTree> getChildren() {
        return children;
    }

    public void setChildren(List<RegionBeanTree> children) {
        this.children = children;
    }
}
