package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.skhu.entity.Department;
import net.skhu.entity.Student;
import net.skhu.repository.DepartmentRepository;
import net.skhu.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {
	// 1. repository 선언
	@Autowired DepartmentRepository departmentRepository;
	@Autowired StudentRepository studentRepository;

	@RequestMapping("list")
	public String list(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "student/list";
	}

	@GetMapping("create")
	public String create(Model model) {
		Student student = new Student();
		List<Department> departments = departmentRepository.findAll();
		model.addAttribute("student", student);
		model.addAttribute("departments", departments);
		return "student/edit";
	}

	@PostMapping("create")
	public String create(Model model, Student student) {
		studentRepository.save(student);
		return "redirect:list";
	}

//	@GetMapping("edit")
//	public String edit(Model model, @RequestParam("id") int id) {
//		Student student = studentRepository.findById(id).get();
//
//		student.setDepartment(department);
//		model.addAttribute("student", student);
//		return "student/edit";
//	}

}
