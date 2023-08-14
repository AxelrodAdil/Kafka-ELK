package kz.axelrod.kafkaelk.api.service;

import kz.axelrod.kafkaelk.api.dto.ClassRoomResponse;
import kz.axelrod.kafkaelk.api.dto.SearchObject;
import kz.axelrod.kafkaelk.api.dto.SortField;
import kz.axelrod.kafkaelk.api.model.ClassRoom;
import kz.axelrod.kafkaelk.api.repository.ClassRoomRepository;
import kz.axelrod.kafkaelk.api.repository.StudentRepository;
import kz.axelrod.kafkaelk.api.repository.impl.ClassRoomSpecification;
import kz.axelrod.kafkaelk.api.repository.impl.SearchOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcademyService {

    private final StudentRepository studentRepository;
    private final ClassRoomRepository classRoomRepository;

    private List<SearchObject> getEqualSearchObjectList(String fieldName, String fieldValue) {
        return List.of(new SearchObject(fieldName, fieldValue, SearchOperation.EQUAL));
    }

    private List<ClassRoom> getAllClassRoomByFunctionalInterface(List<SearchObject> searchObjectList) {
        ClassRoomSpecification classRoomSpecification = new ClassRoomSpecification();
        searchObjectList.stream().map(e -> new SearchObject(e.getKey(), e.getValue(), e.getOperation()))
                .forEach(classRoomSpecification::add);
        return classRoomRepository.findAll(classRoomSpecification);
    }

    private List<ClassRoomResponse> getSortedClassRoomResponseList(List<ClassRoomResponse> classRoomList,
                                                                   Sort.Direction mainSortDirection, SortField sortField) {
        Comparator<ClassRoomResponse> comparator = switch (sortField) {
            case CLASSROOM_ID -> Comparator.comparing(ClassRoomResponse::getClassRoomId);
            case STUDENT_FIRSTNAME -> Comparator.comparing(ClassRoomResponse::getStudentFirstName);
            case STUDENT_LASTNAME -> Comparator.comparing(ClassRoomResponse::getStudentLastName);
            case STUDENT_AGE -> Comparator.comparing(ClassRoomResponse::getStudentAge);
            case STUDENT_ACADEMIC_PERFORMANCE -> Comparator.comparing(ClassRoomResponse::getStudentAcademicPerformance);
            case INSTRUCTOR_FIRSTNAME -> Comparator.comparing(ClassRoomResponse::getInstructorFirstName);
            case INSTRUCTOR_LASTNAME -> Comparator.comparing(ClassRoomResponse::getInstructorLastName);
            case COURSE_TITLE -> Comparator.comparing(ClassRoomResponse::getCourseTitle);
        };
        if (mainSortDirection == Sort.Direction.DESC) comparator = comparator.reversed();
        return classRoomList.stream().sorted(comparator).collect(Collectors.toList());
    }
}
