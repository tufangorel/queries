package com.polygons.queries.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TimedOptionDTO {

	@SerializedName("key")
	private String key;
	
	@SerializedName("changesOverTime")
	private List<List<Integer>> changesOverTime = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<List<Integer>> getChangesOverTime() {
		return changesOverTime;
	}

	public void setChangesOverTime(List<List<Integer>> changesOverTime) {
		this.changesOverTime = changesOverTime;
	}

}