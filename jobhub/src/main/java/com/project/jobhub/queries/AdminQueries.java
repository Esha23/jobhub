package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.CategoryTableConstants;

public class AdminQueries {
	
	public static final String AddCategory_Query = "INSERT INTO " + CategoryTableConstants.TABLE_NAME + " ( "
			+ CategoryTableConstants.NAME + " ) " + " VALUES ( :" + CategoryTableConstants.NAME + " )";

}
