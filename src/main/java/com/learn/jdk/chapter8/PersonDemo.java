package com.learn.jdk.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * BiFunction函数式接口实例演示
 * @ClassName: PersonDemo
 * @Description: BiFunction函数式接口实例演示
 * @Author: lin
 * @Date: 2019/12/27 17:07
 * History:
 * @<version> 1.0
 */
public class PersonDemo {
    public static void main(String[] args) {
        Person person1 = new Person("zairian", 20);
        Person person2 = new Person("lisa", 30);
        Person person3 = new Person("wangle", 40);
        List<Person> persons = Arrays.asList(person1, person2, person3);

        PersonDemo test = new PersonDemo();
//        List<Person> personResult = test.getPersonByUserName("lisa", persons);
//        personResult.forEach(person -> System.out.println(person.getUsername()));

        List<Person> personResult2 = test.getPersonByAge(20, persons);
        personResult2.forEach(person -> System.out.println(person.getAge()));

        List<Person> personResult3 = test.getPersonAge2(20 , persons, (age, personList)->
            personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList()));

        personResult3.forEach(person -> System.out.println(person.getAge()));

        System.out.println("===================================================");

        List<Person> personResult4 = test.getPersonAge2(20 , persons, (age, personList)->
                personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList()));

        personResult4.forEach(person -> System.out.println(person.getAge()));
    }

    /**
     * 根据参数 来判断符合条件的元素，然后返回集合
     * @param userName
     * @param persons
     * @return
     */
    public List<Person> getPersonByUserName(String userName, List<Person> persons){
        // 这行代码的意思就是 将persons转换成流,然后过滤流中每一个元素,
        // filter 也是返回stream 对象, 这是stream的中间操作,每次操作完成后又返回这个对象,
        // 最后想把stream转换成 集合 可通过collect(Collectors.toList())进行转换。
        // 每一个元素要符合这个判断条件, 最后把它转换成list进行返回
         return  persons.stream().filter(person -> person.getUsername().equals(userName)).
                 collect(Collectors.toList());
    }

    /**
     * 判断集合中大于传入的 age 参数。
     * @param age
     * @param persons
     * @return
     */
    public  List<Person> getPersonByAge(int age, List<Person> persons){
        // 首先定义了一个BiFunction 这样一个对象, 然后他有两个参数,
        // 对于这两个参数 直接使用stream(流)的方式 去找到里面的每一个对象,
        // 这个对象的年龄要大于 ageOfPerson, 然后.collect(Collectors.toList()) 转换成一个集合.
        // 如果是statement {return o2.compareTo(o1);} 这是一个完整的语句, 那么必须有return
        // 下面可以替换 expression lambda , 也就是去掉花括号 {} 和return
        BiFunction<Integer, List<Person>, List<Person>> biFunction =
//                (ageOfPerson, personList)->{
//           return   personList.stream().filter(person -> person.getAge()> ageOfPerson).
//                    collect(Collectors.toList());
//        };
        (ageOfPerson, personList)-> personList.stream().filter(person -> person.getAge()> ageOfPerson).
                    collect(Collectors.toList());


        // 上面只是定义好了BiFunction, 这里才是应用它
        return  biFunction.apply(age, persons);
    }


    public List<Person> getPersonAge2(int age,List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction){
        return  biFunction.apply(age, persons);
    }
}
