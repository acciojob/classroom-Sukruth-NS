package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    HashMap<String,Student> StudentDb = new HashMap<>();

    HashMap<String,Teacher> TeacherDb = new HashMap<>();

    HashMap<String,String> StudentPairDb = new HashMap<>();

    public void addStudent(Student student){
        String key = student.getName();
        StudentDb.put(key,student);
    }

    public void addTeacher(Teacher teacher){
        String key = teacher.getName();
        TeacherDb.put(key,teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        StudentPairDb.put(student,teacher);
    }

    public Student getStudentByName(String name){
       return StudentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return TeacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        List<String> list = new ArrayList<>();
        for(Map.Entry<String,String> entry : StudentPairDb.entrySet()){
            if(entry.getValue().equals(teacherName)){
                String studentName = entry.getKey();
                list.add(studentName);
            }
        }
        return list;
    }

    public List<String> getAllStudents(){
        List<String> studentList = new ArrayList<>();
        for (String studentName : StudentDb.keySet() ) {
            studentList.add(studentName);
        }
        return studentList;
    }

    public void deleteTeacherByName(String teacher){
        TeacherDb.remove(teacher);
        for(Map.Entry<String,String> entry : StudentPairDb.entrySet()){
            if(entry.getValue().equals(teacher)){
                String student = entry.getKey();
                StudentDb.remove(student);
                StudentPairDb.remove(student,teacher);
            }
        }
    }

    public void deleteAllTeachers(){
        for (Map.Entry<String,String> entry : StudentPairDb.entrySet()) {
            String student = entry.getKey();
            String teacher = entry.getValue();
            StudentPairDb.remove(student,teacher);
            StudentDb.remove(student);
            TeacherDb.remove(teacher);
        }
    }
}
