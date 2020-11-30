package lsq.Bryan.domain;

import java.util.List;

public class QueryResult {//∆’Õ®JavaBean
	private List<Good> list;
	private int totalrecord;
	public List<Good> getList() {
		return list;
	}
	public void setList(List<Good> list) {
		this.list = list;
	}
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
}
