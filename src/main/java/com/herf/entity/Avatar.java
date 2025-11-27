package com.herf.entity;



import jakarta.persistence.*;

import jakarta.persistence.Lob;

@Entity
@Table(name = "hfimages")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;
    
    @Column(columnDefinition=  "TEXT")
    @Lob
    private String description;
    
    private Long uid;

    @Lob	
    @Column(name="data", length=100000)
    private byte[] data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public void setUId(Long uid) {
		this.uid = uid;
	}
	
	public Long getUId() {
		return uid;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


    // Constructors, getters, setters, etc.
}
