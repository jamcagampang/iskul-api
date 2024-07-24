package com.jamsoftwares.iskul.classroom;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.jamsoftwares.iskul.classroom.exception.ClassroomNotFoundException;
import com.jamsoftwares.iskul.classroom.request.ClassroomRequestTO;
import com.jamsoftwares.iskul.common.CommonConstants;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Test For Classroom Service")
public class ClassroomServiceTests {

	private static final long CLASSROOM_ID_1 = 1L;
	private static final long TEACHER_1 = 1L;
	private static final String CLASSROOM_DESCRIPTION = "Sample Classroom Only";
	private static final String CLASSROOM_NAME = "My First Classroom";
	private static final String CLASSROOM_NAME_NEW = "My Second Classroom";

	@Mock
	private ClassroomRepository classroomRepository;

	@InjectMocks
	private ClassroomService classroomService;

	// Reusable Object For Many Test Cases
	private Classroom classroom1;
	private Classroom savedClassroom;

	@BeforeEach
	public void initializeClassroom() {
		this.classroom1 = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(TEACHER_1)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();
		this.savedClassroom = Classroom.builder()
			.id(CLASSROOM_ID_1)
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(TEACHER_1)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.lastModBy(TEACHER_1)
			.lastModDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.softDeleted(CommonConstants.RECORD_ACTIVE)
			.build();
	}

	@Test
	@DisplayName("When we insert a classroom, the service should provide the id.")
	public void ClassroomService_SaveClassroom_ReturnsPrimaryKey() {

		// Arrange
		when(this.classroomRepository.save(Mockito.any(Classroom.class))).thenReturn(this.savedClassroom);

		// Act
		Long classroomId = this.classroomService.saveClassroom(this.classroom1);

		// Assert
		Assertions.assertThat(classroomId).isNotNull();
		Assertions.assertThat(classroomId).isGreaterThan(0);
	}

	@Test
	@DisplayName("When we retrieve classroms for teacher, the service should return classrooms belongs only to that teacher.")
	@SuppressWarnings("unchecked")
	public void ClassroomService_RetrieveClassroomsOfTeacher_ReturnsOwned() {

		// Arrange
		when(this.classroomRepository.findAll((Example<Classroom>) Mockito.any())).thenReturn(Arrays.asList(this.savedClassroom));

		// Act
		List<Classroom> classrooms = this.classroomService.retrieveClassroomsOfTeacher(TEACHER_1);

		// Assert
		Assertions.assertThat(classrooms).isNotNull();
		Assertions.assertThat(classrooms.size()).isGreaterThan(0);
		Assertions.assertThat(classrooms.get(0).getTeacherId()).isEqualTo(TEACHER_1);
	}

	@Test
	@DisplayName("When we retrieve an active classroom, the service should provide a class.")
	public void ClassroomService_RetrieveActiveClassroomsById_ReturnsSomething() {

		// Arrange
		when(this.classroomRepository.findById(CLASSROOM_ID_1)).thenReturn(Optional.of(this.savedClassroom));

		// Act
		Classroom classroom = this.classroomService.retrieveClassroomsById(CLASSROOM_ID_1);

		// Assert
		Assertions.assertThat(classroom).isNotNull();
		Assertions.assertThat(classroom.getId()).isNotNull();
		Assertions.assertThat(classroom.getId()).isGreaterThan(0);
		Assertions.assertThat(classroom.getId()).isEqualTo(CLASSROOM_ID_1);
		Assertions.assertThat(classroom.getSoftDeleted()).isEqualTo(CommonConstants.RECORD_ACTIVE);
	}

	@Test
	@DisplayName("When we retrieve non-existing classroom, the service should throw ClassroomNotFoundException.")
	public void ClassroomService_RetrieveNonExistingClasroom_ThrowsClassroomNotFoundException() {

		// Arrange
		// No need to do anything, repository will return null on retrieve by id.

		// Act
		Exception exception = assertThrows(ClassroomNotFoundException.class, () -> {
			this.classroomService.retrieveClassroomsById(CLASSROOM_ID_1);
		});

		// Assert
		Assertions.assertThat(exception.getMessage()).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo("Classroom ID " + CLASSROOM_ID_1 + " is not existing.");
	}

	@Test
	@DisplayName("When we retrieve deleted classroom, the service should throw ClassroomNotFoundException.")
	public void ClassroomService_RetrieveDeletedClassrom_ReturnsSomethingButDeleted() {

		// Arrange
		this.savedClassroom.setSoftDeleted(CommonConstants.RECORD_DELETED);
		when(this.classroomRepository.findById(CLASSROOM_ID_1)).thenReturn(Optional.of(this.savedClassroom));

		// Act
		Exception exception = assertThrows(ClassroomNotFoundException.class, () -> {
			this.classroomService.retrieveClassroomsById(CLASSROOM_ID_1);
		});

		// Assert
		Assertions.assertThat(exception.getMessage()).isNotNull();
		Assertions.assertThat(exception.getMessage()).isEqualTo("Classroom ID " + CLASSROOM_ID_1 + " is already deleted.");
	}

	@Test
	@DisplayName("When we update an active classroom, service should return updated classroom.")
	public void ClassroomService_UpdateActiveClassroom_ReturnsUpdatedClassroom() {

		// Arrange
		when(this.classroomRepository.findById(CLASSROOM_ID_1)).thenReturn(Optional.of(this.savedClassroom));
		this.savedClassroom.setName(CLASSROOM_NAME_NEW);
		when(this.classroomRepository.save(Mockito.any(Classroom.class))).thenReturn(this.savedClassroom);
		ClassroomRequestTO request = ClassroomRequestTO.builder()
			.name(CLASSROOM_NAME_NEW)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();

		// Act
		Classroom classroom = this.classroomService.updateClassroom(request, CLASSROOM_ID_1, TEACHER_1);

		// Assert
		Assertions.assertThat(classroom.getName()).isNotNull();
		Assertions.assertThat(classroom.getName()).isEqualTo(CLASSROOM_NAME_NEW);
	}

	@Test
	@DisplayName("When we delete an active classroom, service should not throw an Exception.")
	public void ClassroomService_DeleteActiveClassroom_ThrowsNoException() {

		// Arrange
		when(this.classroomRepository.findById(CLASSROOM_ID_1)).thenReturn(Optional.of(this.savedClassroom));
		when(this.classroomRepository.save(Mockito.any(Classroom.class))).thenReturn(this.savedClassroom);

		// Act + Assert
		assertAll(() -> {
			this.classroomService.deleteClassroom(CLASSROOM_ID_1, TEACHER_1);
		});
	}
}
