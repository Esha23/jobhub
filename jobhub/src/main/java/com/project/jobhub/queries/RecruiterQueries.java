package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.RecruiterDataTableConstants;
import com.project.jobhub.tableConstants.UserDetailsTableConstants;

public class RecruiterQueries {
	
	public static final String addRecruiterData_Query = "INSERT INTO " + RecruiterDataTableConstants.TABLE_NAME + " ( "
			+ RecruiterDataTableConstants.RECRUITER_ID + " , " + RecruiterDataTableConstants.CATEGORY_ID+ " , " + RecruiterDataTableConstants.CONTACT + " , " + RecruiterDataTableConstants.GENDER 
			+ " , " + RecruiterDataTableConstants.EXPERIENCE + " , " + RecruiterDataTableConstants.EDUCATION + " , " + RecruiterDataTableConstants.LOCATION + " , " + RecruiterDataTableConstants.SALARY
			+ " , " + RecruiterDataTableConstants.TITLE + " , " + RecruiterDataTableConstants.DESCRIPTION + " , " + RecruiterDataTableConstants.JOB_STATUS + " ) " 
			+ " VALUES ( :" + RecruiterDataTableConstants.RECRUITER_ID + " , :" + RecruiterDataTableConstants.CATEGORY_ID + " , :" + RecruiterDataTableConstants.CONTACT + " , :" + RecruiterDataTableConstants.GENDER
			+ " , :" + RecruiterDataTableConstants.EXPERIENCE + " , :" + RecruiterDataTableConstants.EDUCATION + " , :" + RecruiterDataTableConstants.LOCATION + " , :" + RecruiterDataTableConstants.SALARY
			+ " , :" + RecruiterDataTableConstants.TITLE + " , :" + RecruiterDataTableConstants.DESCRIPTION + " , :" + RecruiterDataTableConstants.JOB_STATUS + " )";
	
	public static final String getRecruiterData_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.RECRUITER_ID + " = :" + RecruiterDataTableConstants.RECRUITER_ID;
	
	public static final String closeJob_Query = "UPDATE " + RecruiterDataTableConstants.TABLE_NAME + " SET " + RecruiterDataTableConstants.JOB_STATUS + "= :" + RecruiterDataTableConstants.JOB_STATUS + " WHERE " 
			+ RecruiterDataTableConstants.JOB_ID + "= :" + RecruiterDataTableConstants.JOB_ID;
	
	public static final String getRecruiterEmail_Query = "SELECT * FROM " + UserDetailsTableConstants.TABLE_NAME 
			+ " WHERE " + UserDetailsTableConstants.ID + "= :" + UserDetailsTableConstants.ID;

}
