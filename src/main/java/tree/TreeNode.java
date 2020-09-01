package tree;

import excel.WeekVo;

import java.util.List;

/**
 * @ClassName: TreeNode
 * @Description: 一个树形结构
 * @Author: lin
 * @Date: 2020/7/7 15:11
 * History:
 * @<version> 1.0
 */
public class TreeNode {
    /**
     * 名称
     */
    private String name;
    /**
     *  id
     */
    private String id;
    /**
     * 父ID
     */
    private String pid;
    private List<TreeNode> child;

    private List<WeekVo> weekVos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChild() {
        return child;
    }

    public void setChild(List<TreeNode> child) {
        this.child = child;
    }

    public List<WeekVo> getWeekVos() {
        return weekVos;
    }

    public void setWeekVos(List<WeekVo> weekVos) {
        this.weekVos = weekVos;
    }
}
