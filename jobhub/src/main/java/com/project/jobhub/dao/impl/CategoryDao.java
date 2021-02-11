package com.project.jobhub.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.jobhub.dao.ICategoryDao;
import com.project.jobhub.entities.Category;
import com.project.jobhub.queries.CategoryQueries;
import com.project.jobhub.tableConstants.CategoryTableConstants;

@Repository
public class CategoryDao implements ICategoryDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Category> getCategoryList() {
		return namedParameterJdbcTemplate.query(CategoryQueries.getCategoryList_Query, (resultSet, rowNum) -> {
			return mapCategory(resultSet);
		});
	}
	
	public Category mapCategory(ResultSet resultSet) throws SQLException {
		Category category = new Category();
		category.setId(resultSet.getInt(CategoryTableConstants.ID));
		category.setName(resultSet.getString(CategoryTableConstants.NAME));
		return category;
	}
	
}
