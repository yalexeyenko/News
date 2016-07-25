package com.epam.yalexeyenko.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BaseEntity {
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "BLOCKNEWS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	private Integer id;

	public BaseEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
