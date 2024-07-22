package com.jamsoftwares.iskul.classroom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jamsoftwares.iskul.classroom.exception.ClassroomNotFoundException;
import com.jamsoftwares.iskul.classroom.request.ClassroomRequestTO;
import com.jamsoftwares.iskul.common.CommonConstants;

import jakarta.transaction.Transactional;

@Service
public class ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepo;

	@Transactional
	public Long saveClassroom(Classroom classroom) {

		Classroom savedClassroom = this.classroomRepo.save(classroom);

		return savedClassroom.getId();
	}

	public List<Classroom> retrieveClassroomsOfTeacher(Long userId) {

		Classroom classroom = new Classroom();
		classroom.setTeacherId(userId);
		classroom.setSoftDeleted(CommonConstants.RECORD_ACTIVE);

		Example<Classroom> example = Example.of(classroom);

		return this.classroomRepo.findAll(example);
	}

	public Classroom retrieveClassroomsById(Long classroomId) {

		Classroom classroom = this.classroomRepo.findById(classroomId).orElseThrow(() -> new ClassroomNotFoundException("Classroom ID " + classroomId + " is not existing."));

		// Must not be soft deleted.
		if (CommonConstants.RECORD_DELETED.equals(classroom.getSoftDeleted())) {
			throw new ClassroomNotFoundException("Classroom ID " + classroomId + " is already deleted.");
		}

		return classroom;
	}

	@Transactional
	public Classroom updateClassroom(ClassroomRequestTO classroomRequest, Long classroomId, Long userId) {

		Classroom classroom = this.classroomRepo.findById(classroomId).orElseThrow(() -> new ClassroomNotFoundException("Classroom ID " + classroomId + " is not existing."));

		// Must not be soft deleted.
		if (CommonConstants.RECORD_DELETED.equals(classroom.getSoftDeleted())) {
			throw new ClassroomNotFoundException("Classroom ID " + classroomId + " is already deleted.");
		}

		classroom.setName(classroomRequest.getName());
		classroom.setDescription(classroomRequest.getDescription());
		classroom.setExpiryDate(classroomRequest.getExpiryDate());
		classroom.setLastModBy(userId);
		classroom.setLastModDt(LocalDateTime.now());

		Classroom updatedClassroom = this.classroomRepo.save(classroom);

		return updatedClassroom;
	}

	@Transactional
	public void deleteClassroom(Long classroomId, Long userId) {

		Classroom classroom = this.classroomRepo.findById(classroomId).orElseThrow(() -> new ClassroomNotFoundException("Classroom ID " + classroomId + " is not existing."));

		// Must not be soft deleted.
		if (CommonConstants.RECORD_DELETED.equals(classroom.getSoftDeleted())) {
			throw new ClassroomNotFoundException("Classroom ID " + classroomId + " is already deleted.");
		}

		classroom.setLastModBy(userId);
		classroom.setLastModDt(LocalDateTime.now());
		classroom.setSoftDeleted(CommonConstants.RECORD_DELETED);

		this.classroomRepo.save(classroom);
	}
}
