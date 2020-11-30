package lsq.Bryan.domain;

//封装查询信息
public class QueryInfo {
	
	private int currentpage = 1;
	private int pagesize = 4;
	private int startindex;
	
	private String queryname;//带查询条件过来，查看某个分类下面的分页数据，例如category_id
	private String queryvalue;//查询条件的值，查看3号分类下面的分页数据，例如category_id=3
	private String where;//使用上面两个带过来的东西组装成一个查询条件，例如where = "where category_id = ?"
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStartindex() {
		this.startindex = (this.currentpage - 1) * this.pagesize;
		return startindex;
	}
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getQueryvalue() {
		return queryvalue;
	}
	public void setQueryvalue(String queryvalue) {
		this.queryvalue = queryvalue;
	}
	public String getWhere() {
		if (this.queryname == null || this.queryname.trim().equals("")) {
			return "";
		} else {
			//this.where = "where category_id=?"
			this.where = "where " + this.queryname + "=?";
		}
		return where;
	}
	
}
