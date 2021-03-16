package challenges;

import java.util.Objects;
import java.util.TreeSet;

public class Person {

    private String name;

    private String surname;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        person1.setAge(10);
        person1.setName("Ivan");

        person2.setAge(20);
        person2.setName("Nikolai");

        person3.setAge(20);
        person3.setName("Ivan");

//        System.out.println("Result of compare is " + person1.equals(person2));

        PersonComparator personComparator = new PersonComparator();

        TreeSet<Person> people = new TreeSet<>(personComparator);
        people.add(person1);
        people.add(person2);
        people.add(person3);

        people.forEach(System.out::println);
    }

}
