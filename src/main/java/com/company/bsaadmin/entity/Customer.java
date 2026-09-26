package com.company.bsaadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class Customer extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String phone;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Source source;
    
    public enum Source { ONLINE, OFFLINE, FIELD_WORK}

    @Column(length=2000)
    private String remarks;

    private String address;
    
    @Enumerated(EnumType.STRING)
    private Type type;
    
    public enum Type { BUY, SELL, BOTH }
    

}
