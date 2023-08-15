package kz.axelrod.kafkaelk.api.service;

import kz.axelrod.kafkaelk.api.dto.ClassRoomResponse;
import kz.axelrod.kafkaelk.api.dto.SearchObject;
import kz.axelrod.kafkaelk.api.exception.BadRequestException;
import kz.axelrod.kafkaelk.api.mapper.ClassRoomMapper;
import kz.axelrod.kafkaelk.api.util.SortField;
import kz.axelrod.kafkaelk.api.model.ClassRoom;
import kz.axelrod.kafkaelk.api.repository.ClassRoomRepository;
import kz.axelrod.kafkaelk.api.repository.impl.ClassRoomSpecification;
import kz.axelrod.kafkaelk.api.repository.impl.SearchOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static kz.axelrod.kafkaelk.api.util.FilterField.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final ClassRoomRepository classRoomRepository;

    public List<ClassRoomResponse> getClassRoomResponse(Long courseId, String courseTitle, String studentFirstName,
                                                        String studentLastName, SortField sortField,
                                                        Sort.Direction sortDirection) {
        validateInput(courseId, courseTitle, studentFirstName, studentLastName);
        var mainSortField = Objects.requireNonNullElse(sortField, SortField.CLASSROOM_ID);
        var mainSortDirection = Objects.requireNonNullElse(sortDirection, Sort.Direction.ASC);
        var classRoomList = getAllClassRoomByFunctionalInterface(getListOfSearchObject(courseId, courseTitle,
                studentFirstName, studentLastName));
        log.info("GetClassRoomResponse --- classRoomList size {}", classRoomList.size());
        var classRoomResponseList = classRoomList.stream().map(ClassRoomMapper::classRoomToResponse).toList();
        return getSortedClassRoomResponseList(classRoomResponseList, mainSortDirection, mainSortField);
    }

    private List<SearchObject> getListOfSearchObject(Long courseId, String courseTitle,
                                                     String studentFirstName, String studentLastName) {
        List<SearchObject> searchObjectList = new ArrayList<>();
        if (courseId != null)
            searchObjectList.add(new SearchObject(COURSE_ID.getNameProperty(), courseId,
                    SearchOperation.JOIN_THAN_EQUAL, COURSE_ID.getTableNameList()));
        if (!isNullOrEmpty(courseTitle))
            searchObjectList.add(new SearchObject(COURSE_TITLE.getNameProperty(), courseTitle,
                    SearchOperation.JOIN_THAN_EQUAL, COURSE_TITLE.getTableNameList()));
        if (!isNullOrEmpty(studentFirstName))
            searchObjectList.add(new SearchObject(STUDENT_FIRSTNAME.getNameProperty(), studentFirstName,
                    SearchOperation.JOIN_THAN_EQUAL, STUDENT_FIRSTNAME.getTableNameList()));
        if (!isNullOrEmpty(studentLastName))
            searchObjectList.add(new SearchObject(STUDENT_LASTNAME.getNameProperty(), studentLastName,
                    SearchOperation.JOIN_THAN_EQUAL, STUDENT_LASTNAME.getTableNameList()));
        log.info("GetListOfSearchObject --- searchObjectList size {}", searchObjectList.size());
        return searchObjectList;
    }

    private List<ClassRoom> getAllClassRoomByFunctionalInterface(List<SearchObject> searchObjectList) {
        ClassRoomSpecification classRoomSpecification = new ClassRoomSpecification();
        searchObjectList.stream().map(e -> new SearchObject(e.getKey(), e.getValue(), e.getOperation(), e.getJoinTableNameList()))
                .forEach(classRoomSpecification::add);
        return classRoomRepository.findAll(classRoomSpecification);
    }

    private List<ClassRoomResponse> getSortedClassRoomResponseList(List<ClassRoomResponse> classRoomList,
                                                                   Sort.Direction sortDirection, SortField sortField) {
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
        if (sortDirection == Sort.Direction.DESC) comparator = comparator.reversed();
        return classRoomList.stream().sorted(comparator).collect(Collectors.toList());
    }

    private void validateInput(Long courseId, String courseTitle, String studentFirstName, String studentLastName) {
        if (courseId == null && isNullOrEmpty(courseTitle) && isNullOrEmpty(studentFirstName)
                && isNullOrEmpty(studentLastName))
            throw new BadRequestException("Not received required parameters");
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
