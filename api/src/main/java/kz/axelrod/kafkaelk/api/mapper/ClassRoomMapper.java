package kz.axelrod.kafkaelk.api.mapper;

import kz.axelrod.kafkaelk.api.dto.ClassRoomResponse;
import kz.axelrod.kafkaelk.api.model.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassRoomMapper {

    public static ClassRoomResponse classRoomToResponse(ClassRoom classRoom) {
        var classRoomResponse = new ClassRoomResponse();
        var student = classRoom.getStudent();
        var schedule = classRoom.getSchedule();
        var course = schedule.getCourse();
        var instructor = schedule.getInstructor();

        classRoomResponse.setClassRoomId(classRoom.getClassId());
        classRoomResponse.setClassRoomAttendance(classRoom.getAttendance());
        classRoomResponse.setStudentFirstName(student.getFirstName());
        classRoomResponse.setStudentLastName(student.getLastName());
        classRoomResponse.setStudentAge(student.getAge());
        classRoomResponse.setStudentEmail(student.getEmail());
        classRoomResponse.setStudentAcademicPerformance(student.getAcademicPerformance());
        classRoomResponse.setClassRoomLocation(schedule.getLocation());
        classRoomResponse.setCourseId(course.getCourseId());
        classRoomResponse.setCourseTitle(course.getTitle());
        classRoomResponse.setCourseCost(course.getCost());
        classRoomResponse.setInstructorFirstName(instructor.getFirstName());
        classRoomResponse.setInstructorLastName(instructor.getLastName());
        classRoomResponse.setInstructorEmail(instructor.getContactEmail());
        return classRoomResponse;
    }
}
