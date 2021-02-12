package com.project.jobhub.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.jobhub.dao.IAdminDao;
import com.project.jobhub.queries.AdminQueries;
import com.project.jobhub.tableConstants.CategoryTableConstants;

@Repository
public class AdminDao implements IAdminDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 

	// function to add a new category to category list ... only by admin .. yet to be implemented in UI
	@Override
	public void addCategory(String category_name) {
		final KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource srcMap = new MapSqlParameterSource();
		srcMap.addValue(CategoryTableConstants.NAME, category_name);
		namedParameterJdbcTemplate.update(AdminQueries.AddCategory_Query, srcMap, holder, new String[] { CategoryTableConstants.ID });
	}

}
