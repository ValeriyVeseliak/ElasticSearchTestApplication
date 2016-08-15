package com.example.domain;

import java.util.List;

public class SearchResponse {

	private int totalCount;
	private List<User> data;

	public SearchResponse(List<User> data) {
		this.totalCount = data.size();
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

}
