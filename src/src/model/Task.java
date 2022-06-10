package model;

import java.io.Serializable;

public class Task implements Serializable {
	private Integer taskid;
	private String userid;

	public Task(Integer taskid, String userid) {
		this.taskid = taskid;
		this.userid = userid;
	}

	//引数がないコンストラクタ
	public Task() {
		super();
		this.taskid = null;
		this.userid = "";
	}

	public Integer getTaskid() {
		return taskid;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
