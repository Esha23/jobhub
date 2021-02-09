package com.project.jobhub.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.jobhub.dao.IUserDetailsDao;
import com.project.jobhub.entities.UserDetails;
import com.project.jobhub.queries.UserDetailsQueries;
import com.project.jobhub.tableConstants.UserDetailsTableConstants;

@Repository
public class UserDetailsDao implements IUserDetailsDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(UserDetailsDao.class);

	@Override
	public UserDetails addUser(UserDetails userDetails) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(UserDetailsTableConstants.ID, userDetails.getId());
		srcMap.addValue(UserDetailsTableConstants.NAME, userDetails.getName());
		srcMap.addValue(UserDetailsTableConstants.EMAIL, userDetails.getEmail());
		srcMap.addValue(UserDetailsTableConstants.TYPE, userDetails.getType());
		namedParameterJdbcTemplate.update(UserDetailsQueries.AddUser_Query, srcMap, holder, new String[] { UserDetailsTableConstants.ID });
		userDetails.setId(userDetails.getId());
		return userDetails;
	}

	@Override
	public UserDetails getUserType(String user_id) {	
		StringBuilder sb = new StringBuilder(40);
		sb.append(UserDetailsQueries.GetUserType_Query).append("=\"").append(user_id).append("\"");
		List<UserDetails> userdetails = namedParameterJdbcTemplate.query(sb.toString(), (resultSet, rowNum) -> {
			return mapUserDetails(resultSet);
		});
		return userdetails.get(0);
	}
	
	private UserDetails mapUserDetails(ResultSet resultSet) throws SQLException {
		UserDetails userDetails = new UserDetails();
		userDetails.setId(resultSet.getString(UserDetailsTableConstants.ID));
		userDetails.setName(resultSet.getString(UserDetailsTableConstants.NAME));
		userDetails.setEmail(resultSet.getString(UserDetailsTableConstants.EMAIL));
		userDetails.setType(resultSet.getString(UserDetailsTableConstants.TYPE));
		return userDetails;
	}

}
