package com.ylkj.xxb.util;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分页对象")
public class Pagination<T> {

	@ApiModelProperty(value = "页码")
	private int pageNum = 1;

	@ApiModelProperty(value = "每页数量")
	private int pageSize = 10;

	@ApiModelProperty(value = "总数量")
	private int total = 0;

	@ApiModelProperty(value = "总页数")
	private int pages = 1;

	private List<T> rows = new ArrayList<>();

	public Pagination() {
		
	}
	
	public Pagination(Page<T> page) {
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.total = (int) page.getTotal();
		this.pages = (int) Math.ceil((double) total / pageSize);
		
		if (this.pageNum < 1) {
			this.pageNum = 1;
		}
		if (this.pages < 1) {
			this.pages = 1;
		}
		this.rows = page.getResult();
	}

	public List<T> logicPagination(List<T> totalRows) {
		this.total = totalRows.size();
		this.pages = (int) Math.ceil((double) total / pageSize);
		if (this.pages < 1) {
			this.pages = 1;
		}
		int fromIndex = calculateOffset();
		int toIndex = fromIndex + pageSize;
		if (toIndex > total) {
			toIndex = total;
		}
		return totalRows.subList(fromIndex, toIndex);
	}

	private int calculateOffset() {
		int offset = 0;
		if (pageNum > (int) Math.ceil((double) total / pageSize)) {
			pageNum = (int) Math.ceil((double) total / pageSize);
		}
		if (pageNum < 1) {
			pageNum = 1;
		}
		offset = (pageNum - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return offset;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}