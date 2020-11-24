package ro.fasttrackit.curs17;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class PersonService {
    public static void main(String[] args) {

        List<Person> personList = List.of(
                new Person("Apostol", "Claudiu", 17, "Arad"),
                new Person("Szabo", "Marius", 16, "Turda"),
                new Person("Szabo", "Timea", 30, "Cluj"),
                new Person("Bontea", "Stefan", 56, "Targu Mures"),
                new Person("Apostol", "Paul", 62, "Suceava"),
                new Person("Bungau", "Marius", 15, "Cluj"),
                new Person("Szabo", "Levente", 60, "Oradea"),
                new Person("Bungau", "George", 65, "Oradea")
        );

        List<String> firstLastName = personList.stream()
                .flatMap(p -> Stream.of(p.getFirstName(), p.getLastName()))
                .collect(Collectors.toList());
        System.out.println(firstLastName);

        List<Person> majorPerson = personList.stream()
                .filter(p -> p.getAge() > 17)
                .collect(Collectors.toList());

        System.out.println(majorPerson);

        List<Person> personOradea = personList.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("Oradea"))
                .collect(Collectors.toList());
        System.out.println(personOradea);

        List<Person> personOradeaCluj = personList.stream()
                .filter(p -> p.getCity().equalsIgnoreCase("Oradea")
                        || p.getCity().equalsIgnoreCase("Cluj"))
                .collect(toList());
        System.out.println(personOradeaCluj);

        List<String> firstNameCAPITALIZED = personList.stream()
                .map(p -> p.getFirstName().toUpperCase())
                .collect(toList());
        System.out.println(firstNameCAPITALIZED);

        List<? extends Serializable> lastNameFirstChar = personList.stream()
                .flatMap(p -> Stream.of(p.getFirstName(), p.getLastName().substring(0, 1) + ".", p.getAge(), p.getCity()))
                .collect(toList());
        System.out.println(lastNameFirstChar);

        List<Person> filterAge = personList.stream()
                .filter(p -> p.getAge() > 18 && p.getAge() < 60)
                .collect(toList());
        System.out.println(filterAge);

        personList.stream()
                .filter(p -> p.getFirstName().startsWith("A"))
                .forEach(System.out::println);


        List<Person> personUniqe = personList.stream()
                .collect(groupingBy(Person::getFirstName)).values().stream().map(l -> (l.get(0)))
                .collect(toList());

        System.out.println(personUniqe);
        System.out.println("----------");

        personList.stream()
                .sorted(comparing(Person::getFirstName))
                .forEach(System.out::println);

        personList.stream()
                .sorted(comparing(Person::getLastName))
                .forEach(System.out::println);

        personList.stream()
                .sorted(Comparator.comparing(Person::getFirstName).
                        thenComparing(Person::getLastName).thenComparing(Person::getAge))
                .forEach(System.out::println);
    }
}

class Person {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String city;


    public Person(String firstName, String lastName, int age, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                city.equals(person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, city);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}' + '\n';
    }
}
