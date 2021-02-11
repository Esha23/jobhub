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

import com.project.jobhub.dao.IApplicantDao;
import com.project.jobhub.entities.ApplicantData;
import com.project.jobhub.entities.Category;
import com.project.jobhub.queries.ApplicantQueries;
import com.project.jobhub.tableConstants.ApplicantDataTableConstants;
import com.project.jobhub.tableConstants.ApplicantToCategoryTableConstants;

@Repository
public class ApplicantDao implements IApplicantDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
		namedParameterJdbcTemplate.update(ApplicantQueries.addApplicantData_Query, srcMap, holder, new String[] { ApplicantDataTableConstants.APPLICANT_ID });	
	}

	@Override
	public List<ApplicantData> getApplicantData(String applicant_id) {
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(ApplicantDataTableConstants.APPLICANT_ID, applicant_id);
		return namedParameterJdbcTemplate.query(ApplicantQueries.getApplicantData_Query, srcMap,(resultSet, rowNum) -> {
			return mapApplicantData(resultSet);
		});
//		return applicantDataList.get(0);
	}
	
	private ApplicantData mapApplicantData(ResultSet resultSet) throws SQLException {
		ApplicantData applicantData = new ApplicantData();
		applicantData.setApplicant_id(resultSet.getString(ApplicantDataTableConstants.APPLICANT_ID));
		applicantData.setContact(resultSet.getString(ApplicantDataTableConstants.CONTACT));
		applicantData.setGender(resultSet.getString(ApplicantDataTableConstants.GENDER));
		applicantData.setExperience(resultSet.getString(ApplicantDataTableConstants.EXPERIENCE));
		applicantData.setEducation(resultSet.getString(ApplicantDataTableConstants.EDUCATION));
		applicantData.setLocation(resultSet.getString(ApplicantDataTableConstants.LOCATION));
		return applicantData;
	}

}
