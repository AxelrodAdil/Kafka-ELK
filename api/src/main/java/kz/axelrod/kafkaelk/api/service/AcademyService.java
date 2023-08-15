package kz.axelrod.kafkaelk.api.service;

import kz.axelrod.kafkaelk.api.dto.ClassRoomResponse;
import kz.axelrod.kafkaelk.api.util.SortField;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AcademyService {

    List<ClassRoomResponse> getClassRoomResponse(Long courseId, String courseTitle, String studentFirstName,
                                                 String studentLastName, SortField sortField, Sort.Direction sortDirection);
}
