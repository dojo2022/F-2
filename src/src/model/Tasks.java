package model;
public class Tasks {
  private Integer taskid;
  private String taskname;
  private String tasklimit;
  private Integer stateflag;
  private String userid;
public Tasks(Integer taskid, String taskname, String tasklimit,
		Integer stateflag, String userid) {
	super();
	this.taskid = taskid;
	this.taskname = taskname;
	this.tasklimit = tasklimit;
	this.stateflag = stateflag;
	this.userid = userid;
}
//引数がないコンストラクタ
public Tasks() {
	super();
	this.taskid = null;
	this.taskname = "";
	this.tasklimit = "";
	this.stateflag = null;
	this.userid = null;
}
public Integer getTaskid() {
	return taskid;
}
public void setTaskid(Integer taskid) {
	this.taskid = taskid;
}
public String getTaskname() {
	return taskname;
}
public void setTaskname(String taskname) {
	this.taskname = taskname;
}
public String getTasklimit() {
	return tasklimit;
}
public void setTasklimit(String tasklimit) {
	this.tasklimit = tasklimit;
}
public Integer getStateflag() {
	return stateflag;
}
public void setStateflag(Integer stateflag) {
	this.stateflag = stateflag;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
}