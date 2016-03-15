package com.lidong.demo.shuffling_pages;

public class AdDomain {
	private String id; // 广告id
	private String date; // 日期
	private String title; // 标题
	private String topicFrom; //选题来自
	private String topic; // 选题
	private String imgUrl; // 图片url
	private boolean isAd; // 是否为广告
	private String startTime; // 广告开始时间
	private String endTime; // 广告结束时间
	private String targetUrl; // 目标url
	private int width; // 宽
	private int height; // 高
	private boolean available; // 是否可用
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isAd() {
		return isAd;
	}

	public void setAd(boolean isAd) {
		this.isAd = isAd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopicFrom() {
		return topicFrom;
	}

	public void setTopicFrom(String topicFrom) {
		this.topicFrom = topicFrom;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	

}
