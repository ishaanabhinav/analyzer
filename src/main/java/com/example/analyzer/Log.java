package com.example.analyzer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity // This tells Hibernate to make a table out of this class
@Data
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String ipAddress;

	private String userAgent;

	private Long statusCode;

	private String requestType;

	private String api;

	private String userLogIn;

	private String userName;

	private Long enterpriseId;

	private String enterpriseName;

	private LocalDateTime date;

	private String parsedDate;

	@PrePersist
	protected void onCreate() {
		Date curDateTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		parsedDate = formatter.format(curDateTime);
	}
}