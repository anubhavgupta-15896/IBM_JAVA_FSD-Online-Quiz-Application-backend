package com.oqa.quizservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="quiz_schedule")
public class QuizSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private int scheduleId;
	
	@Column(name = "user_name")
	private String userName;
	
	@ManyToOne
	@JoinColumn(name="quiz_id", referencedColumnName = "quiz_id")
	private Quiz quiz;
	
	@Column(name = "quiz_date")
	private LocalDate quizDate;
	
	@Column(name = "quiz_time")
	private LocalTime quizTime;
	
	@Transient
	@OneToMany
	@JoinColumn(name="schedule_id", referencedColumnName="schedule_id")
	private  List<UserQuizHistory> userQuizAnswerList;

	

	public QuizSchedule(int scheduleId, String userName, Quiz quiz, LocalDate quizDate, LocalTime quizTime) {
		super();
		this.scheduleId = scheduleId;
		this.userName = userName;
		this.quiz = quiz;
		this.quizDate = quizDate;
		this.quizTime = quizTime;
	}
	
	

	public QuizSchedule(String userName, Quiz quiz) {
		super();
		this.userName = userName;
		this.quiz = quiz;
	}



	public QuizSchedule() {
		super();
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public LocalDate getQuizDate() {
		return quizDate;
	}

	public void setQuizDate(LocalDate quizDate) {
		this.quizDate = LocalDate.now();
	}

	public LocalTime getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(LocalTime quizTime) {
		this.quizTime = LocalTime.now();
	}

	public List<UserQuizHistory> getUserQuizAnswerList() {
		return userQuizAnswerList;
	}

	public void setUserQuizAnswerList(List<UserQuizHistory> userQuizAnswerList) {
		this.userQuizAnswerList = userQuizAnswerList;
	}



	@Override
	public String toString() {
		return "QuizSchedule [scheduleId=" + scheduleId + ", userName=" + userName + ", quiz=" + quiz + ", quizDate="
				+ quizDate + ", quizTime=" + quizTime + ", userQuizAnswerList=" + userQuizAnswerList + "]";
	}
	
	
	
}

