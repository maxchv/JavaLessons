package ua.itstep.shaptala;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StreamExcersice {

	private enum Sex {
        MAN,
        WOMEN
    }

    private static class Student {
        private final String name;
        private final Integer age;
        private final Sex sex;

        public Student(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student people = (Student) o;
            return Objects.equals(name, people.name) &&
                    Objects.equals(age, people.age) &&
                    Objects.equals(sex, people.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }
	
    static Collection<Student> students = Arrays.asList(
            new Student("Вася", 16, Sex.MAN),
            new Student("Петя", 23, Sex.MAN),
            new Student("Соня", 18, Sex.WOMEN),
            new Student("Виктор Петрович", 65, Sex.MAN),
            new Student("Дима", 25, Sex.MAN),
            new Student("Катя", 21, Sex.WOMEN),
            new Student("Семен", 33, Sex.MAN),
            new Student("Елена", 42, Sex.WOMEN),
            new Student("Иван Иванович", 69, Sex.MAN)
    );
    
    static List<Student> ex01() {
    	// TODO: Задание 1
    	// Выбрать всех мужчин-военнообязанных (возраст от 18 до 27 лет)
    	
    	return null;
    }
    
    static Double ex02() {
    	// TODO: Задание 2
    	// Найти средний возраст среди мужчин
    	
    	return null;
    }
    
    static Long ex03() {
    	// TODO: Задание 3
    	// Найти кол-во потенциально работоспособных 
    	// студентов в выборке (т.е. от 18 лет и учитывая 
    	// что женщины выходят в 55 лет, а мужчина в 60)
    	
    	return null;
    	
    }
    
    static List<Student> ex04() {
    	// TODO: Задание 4    	
    	// Отсортировать студентов по имени в обратном алфавитном порядке
    	
    	return null;
    }
    
    static Student ex05() {
    	//  TODO: Задание 5
    	//  найти студента с максимальным возрастом
    	
    	return null;
    }
    
    static Student ex06() {
    	// TODO: Задание 6
    	// Найти студента с минимальным возрастом
    	
    	return null;
    }
    
    public static void main(String[] args) {
		// TODO: тестировать здесь
	}
} 
