package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.ApplicantDataTableConstants;
import com.project.jobhub.tableConstants.ApplicantToCategoryTableConstants;
import com.project.jobhub.tableConstants.RecruiterDataTableConstants;

public class ApplicantQueries {
	
	public static final String applyToCategory_Query = "INSERT INTO " + ApplicantToCategoryTableConstants.TABLE_NAME + " ( "
			+ ApplicantToCategoryTableConstants.APPLICANT_ID + " , " + ApplicantToCategoryTableConstants.CATEGORY_ID + " ) " 
			+ " VALUES ( :" + ApplicantToCategoryTableConstants.APPLICANT_ID + " , :" + ApplicantToCategoryTableConstants.CATEGORY_ID + " )";

	public static final String addApplicantData_Query = "INSERT INTO " + ApplicantDataTableConstants.TABLE_NAME + " ( "
			+ ApplicantDataTableConstants.APPLICANT_ID + " , " + ApplicantDataTableConstants.CONTACT + " , " + ApplicantDataTableConstants.GENDER 
			+ " , " + ApplicantDataTableConstants.EXPERIENCE + " , " + ApplicantDataTableConstants.EDUCATION + " , " + ApplicantDataTableConstants.LOCATION 
			+ " , " + ApplicantDataTableConstants.SALARY + " ) " 
			+ " VALUES ( :" + ApplicantDataTableConstants.APPLICANT_ID + " , :" + ApplicantDataTableConstants.CONTACT + " , :" 
			+ ApplicantDataTableConstants.GENDER + " , :" + ApplicantDataTableConstants.EXPERIENCE + " , :" + ApplicantDataTableConstants.EDUCATION 
			+ " , :" + ApplicantDataTableConstants.LOCATION + " , :" + ApplicantDataTableConstants.SALARY + " )";
	
	public static final String getApplicantData_Query = "SELECT * FROM " + ApplicantDataTableConstants.TABLE_NAME 
			+ " WHERE " + ApplicantDataTableConstants.APPLICANT_ID + " = :" + ApplicantDataTableConstants.APPLICANT_ID;
	
	public static final String getApplicantRecommendedJobsByGender_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.GENDER  + " = :" + RecruiterDataTableConstants.GENDER;
	
	public static final String getApplicantRecommendedJobsByLocation_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.LOCATION + " = :" + RecruiterDataTableConstants.LOCATION;
	
	public static final String getApplicantRecommendedJobsByEducation_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.EDUCATION + " = :" + RecruiterDataTableConstants.EDUCATION;
	
	public static final String getApplicantRecommendedJobsByExperience_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.EXPERIENCE + " >= :" + RecruiterDataTableConstants.EXPERIENCE;
	
	public static final String getApplicantRecommendedJobsBySalary_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.SALARY + " >= :" + RecruiterDataTableConstants.SALARY;
	
	public static final String getAllRecruiterData_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.JOB_STATUS + " = :" + RecruiterDataTableConstants.JOB_STATUS_VALUE;
	
	public static final String getAppliedCategoriesApplicant_Query = "SELECT * FROM " + ApplicantToCategoryTableConstants.TABLE_NAME 
			+ " WHERE " + ApplicantToCategoryTableConstants.APPLICANT_ID + " = :" + ApplicantToCategoryTableConstants.APPLICANT_ID;
	
	public static final String getApplicantRecommendedJobsByCategory_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME
			+ " WHERE " + RecruiterDataTableConstants.CATEGORY_ID + " IN " + ApplicantToCategoryTableConstants.MATCH_CATEGORY_LIST;
	
}
