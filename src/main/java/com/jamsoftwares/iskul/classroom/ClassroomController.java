package com.jamsoftwares.iskul.classroom;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jamsoftwares.iskul.account.AccountConstants;
import com.jamsoftwares.iskul.account.AccountService;
import com.jamsoftwares.iskul.account.model.User;
import com.jamsoftwares.iskul.classroom.request.ClassroomRequestTO;
import com.jamsoftwares.iskul.classroom.response.ResponseMultipleTO;
import com.jamsoftwares.iskul.classroom.response.ResponseSingleTO;

@RestController
public class ClassroomController {

	Logger logger = LogManager.getLogger(ClassroomController.class);

	@Autowired
	private ClassroomService classroomService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/classrooms")
	public ResponseEntity<ResponseMultipleTO> retrieveClassrooms(
			@CookieValue(name = "userId", required = true) Long userId) {

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Getting Classrooms of User ID: " + userId);

		User user = this.accountService.getAccount(userId);

		List<Classroom> classrooms = null;

		if (AccountConstants.USER_TYPE_TEACHER.equals(user.getUserType())) {
			logger.info("[ClassroomController-createClassroom(Classroom, Long)] - User is a teacher. ");
			classrooms = this.classroomService.retrieveClassroomsOfTeacher(userId);
		} else {
			logger.info("[ClassroomController-createClassroom(Classroom, Long)] - User is a student. ");
		}

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Retrived Classrooms - " + (Objects.nonNull(classrooms) ? classrooms.size() : 0));

		ResponseMultipleTO response = new ResponseMultipleTO();
		response.setUserId(userId);
		response.setClassrooms(classrooms);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/classrooms/{classroomId}")
	public ResponseEntity<ResponseSingleTO> getClassroom(@CookieValue(name = "userId", required = true) Long userId,
			@PathVariable Long classroomId) {

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Getting Classroom ID: " + classroomId);

		Classroom classroom = this.classroomService.retrieveClassroomsById(classroomId);

		ResponseSingleTO response = new ResponseSingleTO();
		response.setUserId(userId);
		response.setClassroom(classroom);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Classroom Retrieved - " + classroom);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/classrooms")
	public ResponseEntity<ResponseSingleTO> createClassroom(@Validated @RequestBody ClassroomRequestTO classroomRequest,
			@CookieValue(name = "userId", required = true) Long userId) {

		// User must be existing and a teacher to add the classroom.
		accountService.checkUserIfTeacher(userId);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Creating Classroom For User ID: " + userId);

		Classroom classroom = Classroom.builder()
			.teacherId(userId)
			.name(classroomRequest.getName())
			.description(classroomRequest.getDescription())
			.expiryDate(classroomRequest.getExpiryDate())
			.createdBy(userId)
			.createdDt(LocalDateTime.now())
			.build();

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Classroom - " + classroom);

		Long classroomId = classroomService.saveClassroom(classroom);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Classroom Created - " + classroomId);

		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(classroomId)
			.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/classrooms/{classroomId}")
	public ResponseEntity<ResponseSingleTO> updateClassroom(@Validated @RequestBody ClassroomRequestTO classroomRequest,
			@CookieValue(name = "userId", required = true) Long userId, @PathVariable Long classroomId) {

		// User must be existing and a teacher to add the classroom.
		accountService.checkUserIfTeacher(userId);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Updating Classroom ID: " + classroomId);

		Classroom classroom = this.classroomService.updateClassroom(classroomRequest, classroomId, userId);

		ResponseSingleTO response = new ResponseSingleTO();
		response.setUserId(userId);
		response.setClassroom(classroom);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Classroom Updated - " + classroom);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/classrooms/{classroomId}")
	public ResponseEntity<ResponseSingleTO> deleteClassroom(@CookieValue(name = "userId", required = true) Long userId, @PathVariable Long classroomId) {

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Deleting Classroom ID: " + classroomId);

		this.classroomService.deleteClassroom(classroomId, userId);

		ResponseSingleTO response = new ResponseSingleTO();
		response.setUserId(userId);

		logger.info("[ClassroomController-createClassroom(Classroom, Long)] - Classroom Deleted - " + classroomId);

		return ResponseEntity.ok(response);
	}
}
