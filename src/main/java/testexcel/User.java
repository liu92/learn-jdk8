package testexcel;

/**
 * @ClassName: User
 * @Description:
 * @Author: lin
 * @Date: 2020/6/24 13:58
 * History:
 * @<version> 1.0
 */
public class User {
    private String name;
    private Integer age;
    private float height;
    private String address;
    private String sex;
    private String p;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public User(String name, Integer age, float height, String address, String sex, String p) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.address = address;
        this.sex = sex;
        this.p = p;
    }
}
