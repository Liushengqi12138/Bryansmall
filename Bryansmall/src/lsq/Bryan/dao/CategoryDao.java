package lsq.Bryan.dao;

import java.util.List;

import lsq.Bryan.domain.Category;

public interface CategoryDao {

	void add(Category c);
	
	void dele(String id);

	Category find(String id);

	List<Category> getAll();

}