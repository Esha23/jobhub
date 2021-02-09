package com.project.jobhub.queries;

import com.project.jobhub.tableConstants.UserDetailsTableConstants;

public class UserDetailsQueries {
	
	public static final String AddUser_Query = "INSERT INTO " + UserDetailsTableConstants.TABLE_NAME + " ( "
			+ UserDetailsTableConstants.ID + " , " + UserDetailsTableConstants.NAME + " , " + UserDetailsTableConstants.EMAIL + " , "
			+ UserDetailsTableConstants.TYPE + " ) "
			+ " VALUES ( :" + UserDetailsTableConstants.ID + " , :" + UserDetailsTableConstants.NAME +" , :"
			+ UserDetailsTableConstants.EMAIL + " , :" + UserDetailsTableConstants.TYPE + " )";
	
	public static final String GetUserType_Query = "SELECT * FROM " + UserDetailsTableConstants.TABLE_NAME + " WHERE "
			+ UserDetailsTableConstants.ID;

}
