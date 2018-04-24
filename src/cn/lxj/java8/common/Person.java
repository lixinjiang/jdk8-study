package cn.lxj.java8.common;

/**
 * Person
 * description TODO
 * create by lxj 2018/4/24
 **/
public class Person {
    private Integer id;
    private String firstName;

    private String name;

    private Boolean sex;

    public Person(){}
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
