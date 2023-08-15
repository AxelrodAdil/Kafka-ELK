package kz.axelrod.kafkaelk.api.controller;

import kz.axelrod.kafkaelk.api.dto.ApiResponse;
import kz.axelrod.kafkaelk.api.util.SortField;
import kz.axelrod.kafkaelk.api.service.AcademyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiController {

    private final AcademyServiceImpl academyService;

    @RequestMapping(value = "/academy", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<ApiResponse<List<?>>> getClassRoomResponse(
            @RequestParam(value = "course_id", required = false) Long courseId,
            @RequestParam(value = "course_title", required = false) String courseTitle,
            @RequestParam(value = "student_first_name", required = false) String studentFirstName,
            @RequestParam(value = "student_last_name", required = false) String studentLastName,
            @RequestParam(value = "sort_field", required = false) SortField sortField,
            @RequestParam(value = "sort_direction", required = false) Sort.Direction sortDirection
    ) {
        return ResponseEntity.ok(ApiResponse.success(academyService.getClassRoomResponse(
                courseId, courseTitle, studentFirstName, studentLastName, sortField, sortDirection))
        );
    }
}
