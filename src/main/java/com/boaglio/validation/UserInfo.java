package com.boaglio.validation;

import java.util.Date;

public class UserInfo {

	private String login;
	private String uuid;
	private Date dtStart;
	private Date dtLastUpdate;
	
	public UserInfo() {
		dtStart= new Date(); 
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String getUuid() {
		return uuid;
	}

	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDtStart() {
		return dtStart;
	}
	
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}
	
	public Date getDtLastUpdate() {
		return dtLastUpdate;
	}
	
	public void setDtLastUpdate(Date dtLastUpdate) {
		this.dtLastUpdate = dtLastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (login == null) ? 0 : login.hashCode());
		result = prime * result + ( (uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserInfo [login=" + login + ", uuid=" + uuid + "]";
	}
	
	
}
