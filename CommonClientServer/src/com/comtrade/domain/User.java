package com.comtrade.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	
	private int id_user;
	private String first_name;
	private String username;
	private String password;
	private int status;
	private Set<Role> setRoleUser = new HashSet<>();
	private int idRoleUser;
	private String companyName;
	private int idCompany;
	private String roleName;

	public User(String username, String first_name, int idRoleUser, String companyName, int idCompany, String roleName) {
		super();
		this.username = username;
		this.first_name = first_name;
		this.idRoleUser = idRoleUser;
		this.companyName = companyName;
		this.idCompany = idCompany;
		this.roleName = roleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public User(int id_user, String first_name, String username, String password, int status, Set<Role> setRoleUser,
			int idRoleUser) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoleUser = setRoleUser;
		this.idRoleUser = idRoleUser;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdRoleUser() {
		return idRoleUser;
	}
	public void setIdRoleUser(int idRoleUser) {
		this.idRoleUser = idRoleUser;
	}
	public Set<Role> getSetRoleUser() {
		return setRoleUser;
	}
	public void setSetRoleUser(Set<Role> setRoleUser) {
		this.setRoleUser = setRoleUser;
	}
	public User() {
		
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	 @Override
	public String toString() {
		return "User [id_user=" + id_user + ", first_name=" + first_name + ", username=" + username + ", password="
				+ password + ", status=" + status + ", setRoleUser=" + setRoleUser + "]";
	}
	public User(int id_user, String first_name, String username, String password, int status, Set<Role> setRoleUser) {
		super();
		this.id_user = id_user;
		this.first_name = first_name;
		this.username = username;
		this.password = password;
		this.status = status;
		this.setRoleUser = setRoleUser;
	}
	
	public User(String firstName, String userName2, String password2, String role, int restaurantNumber) {
		this.first_name = firstName;
		this.username = userName2;
		this.password = password2;
		this.roleName = role;
		this.idCompany = restaurantNumber;
	}
	}