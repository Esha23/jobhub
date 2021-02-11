package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.RecruiterDataTableConstants;

public class RecruiterQueries {
	
	public static final String addRecruiterData_Query = "INSERT INTO " + RecruiterDataTableConstants.TABLE_NAME + " ( "
			+ RecruiterDataTableConstants.RECRUITER_ID + " , " + RecruiterDataTableConstants.CATEGORY_ID+ " , " + RecruiterDataTableConstants.CONTACT + " , " + RecruiterDataTableConstants.GENDER 
			+ " , " + RecruiterDataTableConstants.EXPERIENCE + " , " + RecruiterDataTableConstants.EDUCATION + " , " + RecruiterDataTableConstants.LOCATION 
			+ " , " + RecruiterDataTableConstants.JOB_STATUS + " ) " 
			+ " VALUES ( :" + RecruiterDataTableConstants.RECRUITER_ID + " , :" + RecruiterDataTableConstants.CATEGORY_ID + " , :" + RecruiterDataTableConstants.CONTACT + " , :" + RecruiterDataTableConstants.GENDER
			+ " , :" + RecruiterDataTableConstants.EXPERIENCE + " , :" + RecruiterDataTableConstants.EDUCATION + " , :" + RecruiterDataTableConstants.LOCATION 
			+ " , :" + RecruiterDataTableConstants.JOB_STATUS + " )";
	
	public static final String getRecruiterData_Query = "SELECT * FROM " + RecruiterDataTableConstants.TABLE_NAME 
			+ " WHERE " + RecruiterDataTableConstants.RECRUITER_ID + " = :" + RecruiterDataTableConstants.RECRUITER_ID;

}
