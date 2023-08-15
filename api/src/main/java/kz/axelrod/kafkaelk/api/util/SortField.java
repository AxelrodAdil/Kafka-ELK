package kz.axelrod.kafkaelk.api.util;

import io.swagger.v3.oas.annotations.Hidden;

public enum SortField {

    @Hidden
    CLASSROOM_ID("classId", "class_id"),
    STUDENT_FIRSTNAME("firstName", "first_name"),
    STUDENT_LASTNAME("lastName", "last_name"),
    STUDENT_AGE("age", "age"),
    STUDENT_ACADEMIC_PERFORMANCE("academicPerformance", "academic_performance"),
    INSTRUCTOR_FIRSTNAME("firstName", "first_name"),
    INSTRUCTOR_LASTNAME("lastName", "last_name"),
    COURSE_TITLE("title", "title");

    private final String nameProperty;
    private final String fieldName;

    SortField(String nameProperty, String fieldName) {
        this.nameProperty = nameProperty;
        this.fieldName = fieldName;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public String getFieldName() {
        return fieldName;
    }
}
