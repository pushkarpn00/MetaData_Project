/*
 * Copyright (C)2019-2020 TVUNetworks, All Rights Reserved.
 * This source code and any compilation or derivative thereof is the proprietary
 * information of TVUNetworks and is confidential in nature.
 * Under no circumstances is this software to be exposed to or placed
 * under an Open Source License of any type without the expressed written
 * permission of TVUNetworks.
 */
package com.tvu.Metadata_BE.dto;
public class DownloadFileResponseDTO {
	
	private String cmdPath;
	private String placerootpath;
	private String username;
	private String sessionid;
	private String sourcename;
	private String downloadpath;
	private String filename;
	private String starttimestamp;
	private String endtimestamp;
	private String redisip;
	private String port;
	private String password;
	private String taskid;
	
	public String getCmdPath() {
		return cmdPath;
	}
	public void setCmdPath(String cmdPath) {
		this.cmdPath = cmdPath;
	}
	public String getPlacerootpath() {
		return placerootpath;
	}
	public void setPlacerootpath(String placerootpath) {
		this.placerootpath = placerootpath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public String getDownloadpath() {
		return downloadpath;
	}
	public void setDownloadpath(String downloadpath) {
		this.downloadpath = downloadpath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStarttimestamp() {
		return starttimestamp;
	}
	public void setStarttimestamp(String starttimestamp) {
		this.starttimestamp = starttimestamp;
	}
	public String getEndtimestamp() {
		return endtimestamp;
	}
	public void setEndtimestamp(String endtimestamp) {
		this.endtimestamp = endtimestamp;
	}
	public String getRedisip() {
		return redisip;
	}
	public void setRedisip(String redisip) {
		this.redisip = redisip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	
}
