package com.oqa.quizservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oqa.quizservice.entity.Quiz;
import com.oqa.quizservice.entity.QuizSchedule;
import com.oqa.quizservice.service.QuizService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("quiz") 
public class QuizController 
{
	@Autowired
	QuizService quizService;
	
	@GetMapping(value = "/{quizId}",produces = "application/json")
	public ResponseEntity<Quiz> getQuizById(@PathVariable int quizId){
		Quiz q =quizService.getQuizById(quizId);
		if(q!=null)
			return new ResponseEntity<Quiz>(q,HttpStatus.OK);
		return new ResponseEntity<Quiz>(q,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/schedule/{scheduleId}")
	public ResponseEntity<QuizSchedule> getQuizScheduleByScheduleId(@PathVariable int  scheduleId){
		QuizSchedule qs = quizService.getQuizByScheduleId(scheduleId);
		if(qs != null)
			return new ResponseEntity<QuizSchedule>(qs,HttpStatus.OK);
		return new ResponseEntity<QuizSchedule>(qs,HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/submit",consumes = "application/json")
	public HttpStatus submitConductedQuiz(@RequestBody QuizSchedule quizSchedule) {
		if(quizService.submitConductedQuiz(quizSchedule))
			return HttpStatus.OK;
		return HttpStatus.FAILED_DEPENDENCY;
	}
	
	@GetMapping(value = "/subject/{subjectName}",produces = "application/json")
	public ResponseEntity<List<Quiz>> getQuizListBySubject(@PathVariable String subjectName)
	{
		List<Quiz> quizList = quizService.getQuizBySubject(subjectName);
		if(quizList != null)
			return new ResponseEntity<List<Quiz>>(quizList,HttpStatus.OK);
		return new ResponseEntity<List<Quiz>>(quizList,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(consumes = "application/json")
	public HttpStatus insertQuizDetail(@RequestBody Quiz quiz)
	{
		if(quizService.insertQuizDetail(quiz))
			return HttpStatus.OK;
		return HttpStatus.FAILED_DEPENDENCY;
	}
	
	@PutMapping(consumes = "application/json")
	public HttpStatus modifyQuizDetail(@RequestBody Quiz quiz) {
		if(quizService.modifyQuizDetail(quiz))
			return HttpStatus.OK;
		return HttpStatus.FAILED_DEPENDENCY;
	}
	
	@GetMapping(value = "/subjects",produces = "application/json")
	public ResponseEntity<List<String>> getSubjectListOfQuiz()
	{
		List<String> subjectList = quizService.getSubjectListOfQuiz();
		if(subjectList != null)
			return new ResponseEntity<List<String>>(subjectList,HttpStatus.OK);
		return new ResponseEntity<List<String>>(subjectList,HttpStatus.NOT_FOUND);
	}
	
//	@DeleteMapping(value = "/{quizId}")
//	public HttpStatus deleteQuiz(@PathVariable int quizId)
//	{
//		quizService.deleteQuiz(quizId);
//		return HttpStatus.OK;
//	}
//	
//	@PutMapping(value = "/{quizId}/userName/{userName}")
//	public ResponseEntity<String> createQuizSchedule(int quizId, String userName) {
//		if(quizService.createQuizSchedule(quizId, userName))
//			return new ResponseEntity<String>("Schedule Created",HttpStatus.CREATED);
//		return new ResponseEntity<String>("Schedule not created",HttpStatus.NOT_ACCEPTABLE);
//	}
	
	
	
}