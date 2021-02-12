package com.project.jobhub.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.project.jobhub.dao.IApplicantDao;
import com.project.jobhub.dao.IUserDetailsDao;
import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.entities.MailDetails;
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.entities.UserDetails;
import com.project.jobhub.queries.ApplicantQueries;
import com.project.jobhub.tableConstants.ApplicantDataTableConstants;
import com.project.jobhub.tableConstants.ApplicantToCategoryTableConstants;
import com.project.jobhub.tableConstants.RecruiterDataTableConstants;

@Repository
public class ApplicantDao implements IApplicantDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	IUserDetailsDao iUserDetailsDao;

	@Override
	public void applyToCategory(String applicant_id, List<Category> appliedCategoryList) {
		for( Category category: appliedCategoryList) {
			final KeyHolder holder = new GeneratedKeyHolder();
			MapSqlParameterSource srcMap = new MapSqlParameterSource();
			srcMap.addValue(ApplicantToCategoryTableConstants.APPLICANT_ID, applicant_id);
			srcMap.addValue(ApplicantToCategoryTableConstants.CATEGORY_ID, category.getId());
			namedParameterJdbcTemplate.update(ApplicantQueries.applyToCategory_Query, srcMap, holder, new String[] { ApplicantToCategoryTableConstants.APPLICANT_ID, ApplicantToCategoryTableConstants.CATEGORY_ID });
		}
	}

	@Override
	public void addApplicantData(ApplicantData applicantData) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(ApplicantDataTableConstants.APPLICANT_ID, applicantData.getApplicant_id());
		srcMap.addValue(ApplicantDataTableConstants.CONTACT, applicantData.getContact());
		srcMap.addValue(ApplicantDataTableConstants.GENDER, applicantData.getGender());
		srcMap.addValue(ApplicantDataTableConstants.EXPERIENCE, applicantData.getExperience());
		srcMap.addValue(ApplicantDataTableConstants.EDUCATION, applicantData.getEducation());
		srcMap.addValue(ApplicantDataTableConstants.LOCATION, applicantData.getLocation());
		srcMap.addValue(ApplicantDataTableConstants.SALARY, applicantData.getSalary());
		namedParameterJdbcTemplate.update(ApplicantQueries.addApplicantData_Query, srcMap, holder, new String[] { ApplicantDataTableConstants.APPLICANT_ID });	
	}

	@Override
	public List<ApplicantData> getApplicantData(String applicant_id) {
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(ApplicantDataTableConstants.APPLICANT_ID, applicant_id);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantData_Query, srcMap,(resultSet, rowNum) -> {
			return mapApplicantData(resultSet);
		});
	}
	
	private ApplicantData mapApplicantData(ResultSet resultSet) throws SQLException {
		ApplicantData applicantData = new ApplicantData();
		applicantData.setApplicant_id(resultSet.getString(ApplicantDataTableConstants.APPLICANT_ID));
		applicantData.setContact(resultSet.getString(ApplicantDataTableConstants.CONTACT));
		applicantData.setGender(resultSet.getString(ApplicantDataTableConstants.GENDER));
		applicantData.setExperience(resultSet.getString(ApplicantDataTableConstants.EXPERIENCE));
		applicantData.setEducation(resultSet.getString(ApplicantDataTableConstants.EDUCATION));
		applicantData.setLocation(resultSet.getString(ApplicantDataTableConstants.LOCATION));
		applicantData.setSalary(resultSet.getInt(ApplicantDataTableConstants.SALARY));
		return applicantData;
	}

	@Override
	public List<RecruiterData> getApplicantRecommendedJobs(String applicant_id) {
		System.out.println("getapplicantrecommendedjobs func started");
		List<RecruiterData> recruiterDataByGenderList = getApplicantRecommendedJobsByGender(applicant_id);
		List<RecruiterData> recruiterDataByLocationList = getApplicantRecommendedJobsByLocation(applicant_id);
		List<RecruiterData> recruiterDataByEducationList = getApplicantRecommendedJobsByEducation(applicant_id);
		List<RecruiterData> recruiterDataByExperienceList = getApplicantRecommendedJobsByExperience(applicant_id);
		List<RecruiterData> recruiterDataByCategoryList = getApplicantRecommendedJobsByCategory(applicant_id);
		List<RecruiterData> recruiterDataBySalaryList = getApplicantRecommendedJobsBySalary(applicant_id);
		List<RecruiterData> allJobList = getAllJobId();
		Map<Integer, Integer> recruiterMatchFrequency  = new HashMap<Integer, Integer>();
		for(RecruiterData recruiter : allJobList) {
			recruiterMatchFrequency.put(recruiter.getJob_id(), 0);
		}
		for(RecruiterData recruiterByGender : recruiterDataByGenderList) {
			if(recruiterByGender.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterByGender.getJob_id(), recruiterMatchFrequency.get(recruiterByGender.getJob_id())+1);
		}
		for(RecruiterData recruiterByLocation : recruiterDataByLocationList) {
			if(recruiterByLocation.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterByLocation.getJob_id(), recruiterMatchFrequency.get(recruiterByLocation.getJob_id())+1);
		}
		for(RecruiterData recruiterByEducation : recruiterDataByEducationList) {
			if(recruiterByEducation.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterByEducation.getJob_id(), recruiterMatchFrequency.get(recruiterByEducation.getJob_id())+1);
		}
		for(RecruiterData recruiterByExperience : recruiterDataByExperienceList) {
			if(recruiterByExperience.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterByExperience.getJob_id(), recruiterMatchFrequency.get(recruiterByExperience.getJob_id())+1);
		}
		for(RecruiterData recruiterByCategory : recruiterDataByCategoryList) {
			if(recruiterByCategory.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterByCategory.getJob_id(), recruiterMatchFrequency.get(recruiterByCategory.getJob_id())+1);
		}
		for(RecruiterData recruiterBySalary : recruiterDataBySalaryList) {
			if(recruiterBySalary.getJobStatus().equals("OPEN"))
				recruiterMatchFrequency.put(recruiterBySalary.getJob_id(), recruiterMatchFrequency.get(recruiterBySalary.getJob_id())+1);
		}
		List<Integer> sortedRecruiterList = sortMapByFrequency(recruiterMatchFrequency);
		List<RecruiterData> recommendList = new ArrayList<RecruiterData>();
		for(Integer sortedRecruiterListItem : sortedRecruiterList) {
			recommendList.add(getRecruiterDataByJobId(sortedRecruiterListItem).get(0));
		}
		return recommendList;
	}
	
	public List<Integer> sortMapByFrequency(Map<Integer,Integer> recruiterMatchFrequency){
		List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(recruiterMatchFrequency.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, Integer>>(){
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());  
			}
		});
		List<Integer> sortedList = new ArrayList<Integer>();
		for (Entry<Integer, Integer> entry : list){  
			sortedList.add(entry.getKey()); 
			System.out.println("Key: " + entry.getKey());
			System.out.println("Value: " + entry.getValue());
		}
		return sortedList;	
	}
	
	private List<RecruiterData> getRecruiterDataByJobId(Integer job_id){
		String getRecruiterDataByJobId_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
				+ " WHERE " + RecruiterDataTableConstants.JOB_ID + " = " + job_id;
		return namedParameterJdbcTemplate.query(getRecruiterDataByJobId_Query,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	
	
	private List<RecruiterData> getAllJobId() {
		String status ="OPEN";
		String getAllRecruiterData_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
				+ " WHERE " + RecruiterDataTableConstants.JOB_STATUS + " = \"" + status + "\"";
		return namedParameterJdbcTemplate.query(getAllRecruiterData_Query,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	private String getApplicantGenderById(String applicant_id) {
		List<ApplicantData> applicantDataList = getApplicantData(applicant_id);
		return applicantDataList.get(0).getGender();
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsByGender(String applicant_id){
		String gender = getApplicantGenderById(applicant_id);
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.GENDER, gender);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantRecommendedJobsByGender_Query, srcMap,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	private String getApplicantLocationById(String applicant_id) {
		List<ApplicantData> applicantDataList = getApplicantData(applicant_id);
		return applicantDataList.get(0).getLocation();
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsByLocation(String applicant_id){
		String location = getApplicantLocationById(applicant_id);
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.LOCATION, location);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantRecommendedJobsByLocation_Query, srcMap,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}

	private String getApplicantEducationById(String applicant_id) {
		List<ApplicantData> applicantDataList = getApplicantData(applicant_id);
		return applicantDataList.get(0).getEducation();
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsByEducation(String applicant_id){
		String education = getApplicantEducationById(applicant_id);
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.EDUCATION, education);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantRecommendedJobsByEducation_Query, srcMap,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	private String getApplicantExperienceById(String applicant_id) {
		List<ApplicantData> applicantDataList = getApplicantData(applicant_id);
		return applicantDataList.get(0).getExperience();
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsByExperience(String applicant_id){
		String experience = getApplicantExperienceById(applicant_id);
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.EXPERIENCE, experience);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantRecommendedJobsByExperience_Query, srcMap,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	private Integer getApplicantSalaryById(String applicant_id) {
		List<ApplicantData> applicantDataList = getApplicantData(applicant_id);
		return applicantDataList.get(0).getSalary();
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsBySalary(String applicant_id){
		Integer salary = getApplicantSalaryById(applicant_id);
		String getApplicantRecommendedJobsBySalary_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
				+ " WHERE salary >= " + salary;
		return namedParameterJdbcTemplate.query(getApplicantRecommendedJobsBySalary_Query,(resultSet, rowNum) -> {
			System.out.println("Salary q" + getApplicantRecommendedJobsBySalary_Query);
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}
	
	private List<Integer> getAppliedCategoriesApplicant(String applicant_id){
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(ApplicantToCategoryTableConstants.APPLICANT_ID, applicant_id);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getAppliedCategoriesApplicant_Query, srcMap,(resultSet, rowNum) -> {
			return resultSet.getInt(ApplicantToCategoryTableConstants.CATEGORY_ID);
		});
	}
	
	private List<RecruiterData> getApplicantRecommendedJobsByCategory(String applicant_id){
		List<Integer> categoryList = getAppliedCategoriesApplicant(applicant_id);
		String match_category_list = "(";
		for(Integer category_id 	: categoryList ) {
			match_category_list = match_category_list + category_id + ",";
		}
		match_category_list = match_category_list.substring(0,match_category_list.length()-1);
		match_category_list = match_category_list + ")";
		String getApplicantRecommendedJobsByCategory_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME
				+ " WHERE " + RecruiterDataTableConstants.CATEGORY_ID + " IN " + match_category_list			;
		return namedParameterJdbcTemplate.query(getApplicantRecommendedJobsByCategory_Query,(resultSet, rowNum) -> {
			return RecruiterDao.mapRecruiterData(resultSet);
		});
	}

	@Override
	public MailDetails getApplicantProfile(String applicant_id) {
		UserDetails applicantDetails = iUserDetailsDao.getUserType(applicant_id);
		ApplicantData applicantData = getApplicantData(applicant_id).get(0);
		String mailBody = "Name: " + applicantDetails.getName() 
		 + "Email: " + applicantDetails.getEmail()
		 + "Contact: " + applicantData.getContact()
		 + "Gender: " + applicantData.getGender()
		 + "Experience: " + applicantData.getExperience()
		 + "Education: " + applicantData.getEducation()
		 + "Location: " + applicantData.getLocation()
		 + "Salary: " + applicantData.getSalary();
		MailDetails mailDetails = new MailDetails();
		mailDetails.setSubject("New Job Candidate");
		mailDetails.setBody(mailBody);
		return mailDetails;
	}
	
}
