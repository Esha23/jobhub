package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.ApplicantDataTableConstants;
import com.project.jobhub.tableConstants.ApplicantToCategoryTableConstants;

public class ApplicantQueries {
	
	public static final String applyToCategory_Query = "INSERT INTO " + ApplicantToCategoryTableConstants.TABLE_NAME + " ( "
			+ ApplicantToCategoryTableConstants.APPLICANT_ID + " , " + ApplicantToCategoryTableConstants.CATEGORY_ID + " ) " 
			+ " VALUES ( :" + ApplicantToCategoryTableConstants.APPLICANT_ID + " , :" + ApplicantToCategoryTableConstants.CATEGORY_ID + " )";

	public static final String addApplicantData_Query = "INSERT INTO " + ApplicantDataTableConstants.TABLE_NAME + " ( "
			+ ApplicantDataTableConstants.APPLICANT_ID + " , " + ApplicantDataTableConstants.CONTACT + " , " + ApplicantDataTableConstants.GENDER 
			+ " , " + ApplicantDataTableConstants.EXPERIENCE + " , " + ApplicantDataTableConstants.EDUCATION + " , " + ApplicantDataTableConstants.LOCATION + " ) " 
			+ " VALUES ( :" + ApplicantDataTableConstants.APPLICANT_ID + " , :" + ApplicantDataTableConstants.CONTACT + " , :" 
			+ ApplicantDataTableConstants.GENDER + " , :" + ApplicantDataTableConstants.EXPERIENCE + " , :" + ApplicantDataTableConstants.EDUCATION 
			+ " , :" + ApplicantDataTableConstants.LOCATION + " )";
	
	public static final String getApplicantData_Query = "SELECT * FROM " + ApplicantDataTableConstants.TABLE_NAME 
			+ " WHERE " + ApplicantDataTableConstants.APPLICANT_ID + " = :" + ApplicantDataTableConstants.APPLICANT_ID;
	
}
