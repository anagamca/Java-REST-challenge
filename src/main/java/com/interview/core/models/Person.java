package com.interview.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Rest safe Person model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

	private String firstname;
	
	private String lastname;
	
	private long age;
	
	private String gitHubAccount;
	
	private Date graduationDate;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getGitHubAccount() {
		return gitHubAccount;
	}

	public void setGitHubAccount(String gitHubAccount) {
		this.gitHubAccount = gitHubAccount;
	}

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}
	
	
}
