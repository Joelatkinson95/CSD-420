// Joel Atkinson December 18,2025 CSD420 Assignment 11.2 JSON Example
/* This is example code I found in the Data Binding portion of tutorialspoint's tutorial on Jackson API
I downloaded the JARs for Jackson, changed the data in the code to be my name and age instead of the authors and ran it
this gave me a good understanding of how JSON works as well as how to implement it in Java using Jackson*/

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTester {
    public static void main(String args[]) {
        JacksonTester tester = new JacksonTester();
        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> studentDataMap = new HashMap<>();
            int[] marks = {1, 2, 3};

            Student student = new Student();
            student.setAge(30);
            student.setName("Joel");

            // JAVA Object
            studentDataMap.put("student", student);
            // JAVA String
            studentDataMap.put("name", "Joel Atkinson");
            // JAVA Boolean
            studentDataMap.put("verified", Boolean.FALSE);
            // Array
            studentDataMap.put("marks", marks);

            mapper.writeValue(new File("student.json"), studentDataMap);

            // Result student.json
            // {
            //   "student": {"name": "Joel", "age": 30},
            //   "marks": [1, 2, 3],
            //   "verified": false,
            //   "name": "Joel Atkinson"
            // }

            studentDataMap = mapper.readValue(new File("student.json"), Map.class);

            System.out.println(studentDataMap.get("student"));
            System.out.println(studentDataMap.get("name"));
            System.out.println(studentDataMap.get("verified"));
            System.out.println(studentDataMap.get("marks"));

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student [ name: " + name + ", age: " + age + " ]";
    }
}