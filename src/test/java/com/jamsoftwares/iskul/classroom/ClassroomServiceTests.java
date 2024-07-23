package com.jamsoftwares.iskul.classroom;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import com.jamsoftwares.iskul.common.CommonConstants;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Unit Test For Classroom Repository")
public class ClassroomServiceTests {

	private static final long TEACHER_1 = 1L;
	private static final long TEACHER_2 = 2L;
	private static final String CLASSROOM_DESCRIPTION = "Sample Classroom Only";
	private static final String CLASSROOM_NAME = "My First Classroom";
	private static final String CLASSROOM_NAME_NEW = "My Second Classroom";

	@Autowired
	private ClassroomRepository classroomRepository;

	@Test
	@DisplayName("When we retrieve a specific existing classroom, we should be receiving a classroom records with correct ID.")
	public void ClassroomRepository_RetrieveById_ReturnClassroom() {

		// Arrange
		Classroom classroom = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(TEACHER_1)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();
		this.classroomRepository.save(classroom);

		// Act
		Classroom retrievedClassroom = this.classroomRepository.findById(classroom.getId()).get();

		// Assert
		Assertions.assertThat(retrievedClassroom).isNotNull();
        Assertions.assertThat(retrievedClassroom.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("When we retrieve classrooms belong to the teacher, they should be really belong to the teacher.")
	public void ClassroomRepository_FindAllByTeacherId_ReturnClassroomOfTeacher() {

		// Arrange
		Classroom classroom1 = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(1L)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.softDeleted(CommonConstants.RECORD_ACTIVE)
			.build();
		Classroom classroom3 = Classroom.builder()
			.teacherId(TEACHER_2)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(1L)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.softDeleted(CommonConstants.RECORD_ACTIVE)
			.build();
		Classroom classroom2 = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(1L)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.softDeleted(CommonConstants.RECORD_ACTIVE)
			.build();
		this.classroomRepository.save(classroom1);
		this.classroomRepository.save(classroom2);
		this.classroomRepository.save(classroom3);

		// Act
		Classroom classroom = new Classroom();
		classroom.setTeacherId(TEACHER_1);
		classroom.setSoftDeleted(CommonConstants.RECORD_ACTIVE);
		Example<Classroom> example = Example.of(classroom);
		List<Classroom> classrooms = this.classroomRepository.findAll(example);

		// Assert
		Assertions.assertThat(classrooms.size()).isEqualTo(2);
        Assertions.assertThat(classrooms.get(0).getTeacherId()).isNotNull();
        Assertions.assertThat(classrooms.get(1).getTeacherId()).isNotNull();
        Assertions.assertThat(classrooms.get(0).getTeacherId()).isEqualTo(TEACHER_1);
        Assertions.assertThat(classrooms.get(1).getTeacherId()).isEqualTo(TEACHER_1);
	}

	@Test
	@DisplayName("When we insert a classroom, it should create a record in the db and will return a record with correct ID.")
	public void ClassroomRepository_SaveOne_ReturnSameClassroom() {

		// Arrange
		Classroom classroom = Classroom.builder()
			.teacherId(1L)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(1L)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();

		// Act
		Classroom savedClassroom = this.classroomRepository.save(classroom);

		// Assert
		Assertions.assertThat(savedClassroom).isNotNull();
        Assertions.assertThat(savedClassroom.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("When we delete (soft) the classroom, it should be set to \"deleted\" in the db and will still show in next retreives as deleted.")
	public void ClassroomRepository_DeleteOne_ReturnEmptyClassroom() {

		// Arrange
		Classroom classroom = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(TEACHER_1)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();
		this.classroomRepository.save(classroom);
		classroom.setLastModBy(1L);
		classroom.setLastModDt(LocalDateTime.now());
		classroom.setSoftDeleted(CommonConstants.RECORD_DELETED);
		this.classroomRepository.save(classroom);

		// Act
		Classroom deletedClassroom = this.classroomRepository.findById(classroom.getId()).get();

		// Assert
        Assertions.assertThat(deletedClassroom.getLastModBy()).isNotNull();
        Assertions.assertThat(deletedClassroom.getLastModDt()).isNotNull();
		Assertions.assertThat(deletedClassroom.getSoftDeleted()).isEqualTo(CommonConstants.RECORD_DELETED);
	}

	@Test
	@DisplayName("When we update the classroom name, it should be successfully saved in the db and will show in next retrieves.")
	public void ClassroomRepository_UpdateName_ReturnClassroomWithNewName() {

		// Arrange
		Classroom classroom = Classroom.builder()
			.teacherId(TEACHER_1)
			.name(CLASSROOM_NAME)
			.description(CLASSROOM_DESCRIPTION)
			.expiryDate(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.createdBy(1L)
			.createdDt(LocalDateTime.now().withHour(12).withMinute(0).withSecond(0))
			.build();
		this.classroomRepository.save(classroom);
		classroom.setName(CLASSROOM_NAME_NEW);
		this.classroomRepository.save(classroom);

		// Act
		Classroom updatedClassroom = this.classroomRepository.findById(classroom.getId()).get();

		// Assert
        Assertions.assertThat(updatedClassroom.getName()).isNotNull();
        Assertions.assertThat(updatedClassroom.getName()).isEqualTo(CLASSROOM_NAME_NEW);
	}
}
