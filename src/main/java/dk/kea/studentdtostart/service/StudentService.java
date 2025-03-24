package dk.kea.studentdtostart.service;

import dk.kea.studentdtostart.dto.StudentRequestDTO;
import dk.kea.studentdtostart.dto.StudentResponseDTO;
import dk.kea.studentdtostart.model.Student;
import dk.kea.studentdtostart.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponses = new ArrayList<>();

        // Using a for-loop to convert each Student to a StudentResponseDTO
        for (Student student : students) {
            StudentResponseDTO studentResponse = new StudentResponseDTO(
                    student.getId(),
                    student.getName(),
                    student.getBornDate(),
                    student.getBornTime()
            );
            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }

    public StudentResponseDTO getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student studentResponse = optionalStudent.get();

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
                studentResponse.getId(),
                studentResponse.getName(),
                studentResponse.getBornDate(),
                studentResponse.getBornTime()
        );

        return studentResponseDTO;

    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequest) {
        //StudentResponseDTO studentResponse = studentRepository.save(studentRequest);

        Student newStudent = new Student(
                studentRequest.name(),
                studentRequest.password(),
                studentRequest.bornDate(),
                studentRequest.bornTime()
        );

        Student saveStudent = studentRepository.save(newStudent);

        StudentResponseDTO studentResponse = new StudentResponseDTO(
                saveStudent.getId(),
                saveStudent.getName(),
                saveStudent.getBornDate(),
                saveStudent.getBornTime()
        );

        return studentResponse;
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequest) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        student.setName(studentRequest.name());
        student.setPassword(studentRequest.password());
        student.setBornDate(studentRequest.bornDate());
        student.setBornTime(studentRequest.bornTime());

        Student studentResponse = studentRepository.save(student);

        return new StudentResponseDTO(
                studentResponse.getId(),
                studentResponse.getName(),
                studentResponse.getBornDate(),
                studentResponse.getBornTime()
        );
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            // Throw RuntimeException if student is not found
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}
