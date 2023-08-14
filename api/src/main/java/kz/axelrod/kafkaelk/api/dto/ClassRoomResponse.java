package kz.axelrod.kafkaelk.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClassRoomResponse {

    private Long classRoomId;
    private Long courseId;
    private String courseTitle;
    private BigDecimal courseCost;
    private String classRoomLocation;
    private Boolean classRoomAttendance;
    private String studentFirstName;
    private String studentLastName;
    private Integer studentAge;
    private Integer studentAcademicPerformance;
    private String studentEmail;
    private String instructorFirstName;
    private String instructorLastName;
    private String instructorEmail;
}
