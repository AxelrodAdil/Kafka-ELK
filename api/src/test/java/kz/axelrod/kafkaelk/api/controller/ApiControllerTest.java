package kz.axelrod.kafkaelk.api.controller;

import kz.axelrod.kafkaelk.api.dto.ApiResponse;
import kz.axelrod.kafkaelk.api.dto.ClassRoomResponse;
import kz.axelrod.kafkaelk.api.exception.BadRequestException;
import kz.axelrod.kafkaelk.api.service.AcademyServiceImpl;
import kz.axelrod.kafkaelk.api.util.SortField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiControllerTest {

    @Mock
    private AcademyServiceImpl academyService;

    @InjectMocks
    private ApiController apiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnClassRoomResponseForSuccessfulRequest() {
        Long courseId = 1L;
        String courseTitle = "Programming 101";
        String studentFirstName = "John";
        String studentLastName = "Doe";
        SortField sortField = SortField.STUDENT_FIRSTNAME;
        Sort.Direction sortDirection = Sort.Direction.ASC;
        List<ClassRoomResponse> mockResponse = Collections.singletonList(new ClassRoomResponse());
        when(academyService.getClassRoomResponse(courseId, courseTitle, studentFirstName, studentLastName, sortField, sortDirection))
                .thenReturn(mockResponse);
        ResponseEntity<ApiResponse<List<?>>> responseEntity = apiController.getClassRoomResponse(
                courseId, courseTitle, studentFirstName, studentLastName, sortField, sortDirection);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, Objects.requireNonNull(responseEntity.getBody()).getResult());
        assertTrue(responseEntity.getBody().isSuccess());
        verify(academyService, times(1)).getClassRoomResponse(courseId, courseTitle, studentFirstName, studentLastName, sortField, sortDirection);
        verifyNoMoreInteractions(academyService);
    }

    @Test
    void shouldReturnBadRequestForFailureRequest() {
        Long courseId = 1L;
        String courseTitle = "Programming 101";
        SortField sortField = SortField.STUDENT_FIRSTNAME;
        Sort.Direction sortDirection = Sort.Direction.ASC;
        String errorMessage = "Invalid input parameters.";
        when(academyService.getClassRoomResponse(courseId, courseTitle, null, null, sortField, sortDirection))
                .thenThrow(new BadRequestException(errorMessage));
        ResponseEntity<ApiResponse<List<?>>> responseEntity = apiController.getClassRoomResponse(
                courseId, courseTitle, null, null, sortField, sortDirection);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, Objects.requireNonNull(responseEntity.getBody()).getError());
        assertFalse(responseEntity.getBody().isSuccess());
        verify(academyService, times(1)).getClassRoomResponse(courseId, courseTitle, null, null, sortField, sortDirection);
        verifyNoMoreInteractions(academyService);
    }
}
