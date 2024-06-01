package dsa;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    public Student convert(Person p, Student student){
        student.age = p.age;
        student.name = p.name;
        return student;
    }

    public String validate(Person person){
        StringBuilder stringBuilder = new StringBuilder();
        if(person.name == null){
            stringBuilder.append("Name can't be null");
        }
        if(!(person.age!=null && person.age > 0 && person.age < 100 && person.age % 10!=0)){
            stringBuilder.append("Age invalid");
        }
        return stringBuilder.toString();
    }

    @Test
    public void test1() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("A",25));
        personList.add(new Person(null,35));
        personList.add(new Person(null,null));
        personList.add(new Person("C",10));
        personList.add(new Person("D",100));
        personList.add(new Person("E",-1));
        personList.add(new Person("F",0));
        personList.add(new Person("G",55));

        List<Student> studentList = personList.stream().map(p -> {
            Student s = new Student(validate(p));
            convert(p,s);
            return s;
        }).filter(stud -> stud.getMessage()!=null && stud.getMessage().length()>0).collect(Collectors.toList());

        System.out.println("Student List:");
        studentList.forEach(System.out::println);

        personList.removeAll(studentList);

        System.out.println("Person List:");
        personList.forEach(System.out::println);

        Collections.sort(personList, Person.AgeComparator);
    }
}

class Person{
    public static Comparator<? super Person> AgeComparator;
    String name;
    Integer age;

    public Person(){}

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(name, age);
    }*/

    public static class AgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person person1, Person person2) {
            return Integer.compare(person1.age, person2.age);
        }
    }
}

class Student extends Person{
    String message;

    public Student(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", message='" + message + '\'' +
                '}';
    }
}
