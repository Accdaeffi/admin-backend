package ru.ifmo.mpi.magichospital.admin.domain.dao;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.SocialStatus;

@Entity
@Data
@Table(name = "administrator")
public class Administrator {
	
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "is_male")
    private boolean isMale;
    
    @Column(name = "work_start_date")
    private LocalDate workStartDate;
    
    @ManyToOne(targetEntity = SocialStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "social_status")
    private SocialStatus socialStatus;
    
	@Column(name = "login")
	private String login;

}
