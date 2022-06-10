package model;


public class Tasks {
  private Integer taskId;
  private String taskName;
  private String taskLimit;
  private Integer stateFlag;
  private Integer userId;

public Tasks(Integer taskId, String taskName, String taskLimit,
		Integer stateFlag, Integer userId) {
	super();
	this.taskId = taskId;
	this.taskName = taskName;
	this.taskLimit = taskLimit;
	this.stateFlag = stateFlag;
	this.userId = userId;
}
//引数がないコンストラクタ
public Tasks() {
	super();
	this.taskId = null;
	this.taskName = "";
	this.taskLimit = "";
	this.stateFlag = null;
	this.userId = null;
}

//getterとsetter
public Integer getTaskId() {
	return taskId;
}
public void setTaskId(Integer taskId) {
	this.taskId = taskId;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public String getTaskLimit() {

	return taskLimit;
}
public void setTaskLimit(String taskLimit) {
	this.taskLimit = taskLimit;
}
public Integer getStateFlag() {
	return stateFlag;
}
public void setStateFlag(Integer stateFlag) {
	this.stateFlag = stateFlag;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
}
