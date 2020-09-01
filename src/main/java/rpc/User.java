package rpc;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description:
 * @Author: lin
 * @Date: 2020/8/11 16:18
 * History:
 * @<version> 1.0
 */
public class User implements Serializable {
    private static final  long serialVersionUID = 1L;

    public User(){}

    public User(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
