package com.briup.woss.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
/**
 * BIDR表示一个用户的一次的上网记录。
 *  它包括了用户的登录名、分配的IP、上线时间、下线时间、上线时长、NAS的IP。
 * @author wang
 * @version new {@link Date}
 * @serial (or @serialField or @serialData)
 */
public class BIDR implements Serializable {
	private static final long serialVersionUID = 1L;
	private String AAA_login_name;
	private Timestamp login_date;
	private Timestamp logout_date;
	private String login_ip;
	private String NAS_ip;
	private Integer time_duration;
	
	public BIDR() {
	}

	

	public BIDR(String aAA_login_name, Timestamp login_date,
			Timestamp logout_date, String login_ip, String nAS_ip,
			Integer time_duration) {
		super();
		AAA_login_name = aAA_login_name;
		this.login_date = login_date;
		this.logout_date = logout_date;
		this.login_ip = login_ip;
		NAS_ip = nAS_ip;
		this.time_duration = time_duration;
	}



	public String getAAA_login_name() {
		return AAA_login_name;
	}

	public void setAAA_login_name(String aAA_login_name) {
		AAA_login_name = aAA_login_name;
	}

	public Timestamp getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Timestamp login_date) {
		this.login_date = login_date;
	}
    
	public Timestamp getLogout_date() {
		return logout_date;
	}

	public void setLogout_date(Timestamp logout_date) {
		this.logout_date = logout_date;
	}



	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public String getNAS_ip() {
		return NAS_ip;
	}

	public void setNAS_ip(String nAS_ip) {
		NAS_ip = nAS_ip;
	}

	public Integer getTime_duration() {
		return time_duration;
	}

	public void setTime_duration(Integer time_duration) {
		this.time_duration = time_duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "BIDR [AAA_login_name=" + AAA_login_name + ", login_date=" + login_date + ", logout_date=" + logout_date
				+ ", login_ip=" + login_ip + ", NAS_ip=" + NAS_ip + ", time_duration=" + time_duration + "]";
	}


	
}
