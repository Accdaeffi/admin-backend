package ru.ifmo.mpi.magichospital.admin.domain.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.ifmo.mpi.magichospital.admin.domain.dao.dict.SocialStatus;
import ru.ifmo.mpi.magichospital.admin.domain.dto.PatientLongDTO;

@Entity
@Data
@NoArgsConstructor
@Table(name = "patient")
public class Patient {

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
    
    @Column(name = "registration_time")
    private LocalDateTime registrationTime;
    
    @Column(name = "is_mage")
    private boolean isMage;
    
    @ManyToOne(targetEntity = SocialStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "social_status")
    private SocialStatus socialStatus;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "patient", targetEntity = DiseaseCase.class, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<DiseaseCase> diseaseCases;
    
    public Patient(PatientLongDTO patientDto, SocialStatus status) {
    	this.name = patientDto.getName();
    	this.surname = patientDto.getSurname();
    	this.isMale = patientDto.isMale();
    	this.isMage = patientDto.isMage();
    	this.registrationTime = patientDto.getRegistrationTime();
    	this.socialStatus = status;
	}

}
