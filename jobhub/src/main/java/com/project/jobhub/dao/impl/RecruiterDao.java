package com.project.jobhub.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.jobhub.dao.IRecruiterDao;
import com.project.jobhub.entities.RecruiterData;
import com.project.jobhub.queries.RecruiterQueries;
import com.project.jobhub.tableConstants.RecruiterDataTableConstants;
import com.project.jobhub.tableConstants.UserDetailsTableConstants;

@Repository
public class RecruiterDao implements IRecruiterDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void addRecruiterData(RecruiterData recruiterData) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.RECRUITER_ID, recruiterData.getRecruiter_id());
		srcMap.addValue(RecruiterDataTableConstants.CATEGORY_ID, recruiterData.getCategory_id());
		srcMap.addValue(RecruiterDataTableConstants.CONTACT, recruiterData.getContact());
		srcMap.addValue(RecruiterDataTableConstants.GENDER, recruiterData.getGender());
		srcMap.addValue(RecruiterDataTableConstants.EXPERIENCE, recruiterData.getExperience());
		srcMap.addValue(RecruiterDataTableConstants.EDUCATION, recruiterData.getEducation());
		srcMap.addValue(RecruiterDataTableConstants.LOCATION, recruiterData.getLocation());
		srcMap.addValue(RecruiterDataTableConstants.SALARY, recruiterData.getSalary());
		srcMap.addValue(RecruiterDataTableConstants.TITLE, recruiterData.getTitle());
		srcMap.addValue(RecruiterDataTableConstants.DESCRIPTION, recruiterData.getDescription());
		srcMap.addValue(RecruiterDataTableConstants.JOB_STATUS, "OPEN");
		namedParameterJdbcTemplate.update(RecruiterQueries.addRecruiterData_Query, srcMap, holder, new String[] { RecruiterDataTableConstants.JOB_ID });	
	}
	
	@Override
	public void closeJob(Integer job_id) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.JOB_ID, job_id);
		srcMap.addValue(RecruiterDataTableConstants.JOB_STATUS, "CLOSED");
		namedParameterJdbcTemplate.update(RecruiterQueries.closeJob_Query, srcMap, holder, new String[] { RecruiterDataTableConstants.JOB_ID });	
	}
	
	@Override
	public List<RecruiterData> getRecruiterData(String recruiter_id) {
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(RecruiterDataTableConstants.RECRUITER_ID, recruiter_id);
		return namedParameterJdbcTemplate.query(RecruiterQueries.getRecruiterData_Query, srcMap,(resultSet, rowNum) -> {
			return mapRecruiterData(resultSet);
		});
//		return applicantDataList.get(0);
	}
	
	public static RecruiterData mapRecruiterData(ResultSet resultSet) throws SQLException {
		RecruiterData recruiterData = new RecruiterData();
		recruiterData.setJob_id(resultSet.getInt(RecruiterDataTableConstants.JOB_ID));
		recruiterData.setRecruiter_id(resultSet.getString(RecruiterDataTableConstants.RECRUITER_ID));
		recruiterData.setCategory_id(resultSet.getString(RecruiterDataTableConstants.CATEGORY_ID));
		recruiterData.setContact(resultSet.getString(RecruiterDataTableConstants.CONTACT));
		recruiterData.setGender(resultSet.getString(RecruiterDataTableConstants.GENDER));
		recruiterData.setExperience(resultSet.getString(RecruiterDataTableConstants.EXPERIENCE));
		recruiterData.setEducation(resultSet.getString(RecruiterDataTableConstants.EDUCATION));
		recruiterData.setLocation(resultSet.getString(RecruiterDataTableConstants.LOCATION));
		recruiterData.setSalary(resultSet.getInt(RecruiterDataTableConstants.SALARY));
		recruiterData.setTitle(resultSet.getString(RecruiterDataTableConstants.TITLE));
		recruiterData.setDescription(resultSet.getString(RecruiterDataTableConstants.DESCRIPTION));
		recruiterData.setJobStatus(resultSet.getString(RecruiterDataTableConstants.JOB_STATUS));
		return recruiterData;
	}

	@Override
	public List<String> getRecruiterEmail(String recruiter_id) {
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(UserDetailsTableConstants.ID, recruiter_id);
		return namedParameterJdbcTemplate.query(RecruiterQueries.getRecruiterEmail_Query, srcMap,(resultSet, rowNum) -> {
			return resultSet.getString(UserDetailsTableConstants.EMAIL);
		});
	}

}
