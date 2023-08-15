package kz.axelrod.kafkaelk.api.util;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

public enum FilterField {

    @Hidden
    COURSE_ID("courseId", "course_id", List.of("schedule", "course")),
    STUDENT_FIRSTNAME("firstName", "first_name", List.of("student")),
    STUDENT_LASTNAME("lastName", "last_name", List.of("student")),
    STUDENT_AGE("age", "age", List.of("student")),
    STUDENT_ACADEMIC_PERFORMANCE("academicPerformance", "academic_performance", List.of("student")),
    INSTRUCTOR_FIRSTNAME("firstName", "first_name", List.of("schedule", "instructor")),
    INSTRUCTOR_LASTNAME("lastName", "last_name", List.of("schedule", "instructor")),
    COURSE_TITLE("title", "title", List.of("schedule", "course"));

    private final String nameProperty;
    private final String fieldName;
    private final List<String> tableNameList;

    FilterField(String nameProperty, String fieldName, List<String> tableNameList) {
        this.nameProperty = nameProperty;
        this.fieldName = fieldName;
        this.tableNameList = tableNameList;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public String getFieldName() {
        return fieldName;
    }

    public List<String> getTableNameList() {
        return tableNameList;
    }
}
