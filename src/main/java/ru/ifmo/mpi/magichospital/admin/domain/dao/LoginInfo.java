package ru.ifmo.mpi.magichospital.admin.domain.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.Role;

@Entity
@Data
@Table(name = "login_info", schema = "public")
public class LoginInfo {
	
    @Id
	@Column(name = "login")
	private String login;
	
    // admin_pass
	@Column(name = "password")
	private String password;
	
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
