package com.company.bsaadmin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
public class InventoryMedia extends Base {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="inventory_id", nullable=false)
	private Inventory inventory;

	private String fileName;
	private String fileUrl;
	private String contentType;

	@Enumerated(EnumType.STRING)
	private MediaType mediaType;

	public enum MediaType { IMAGE, VIDEO }



}
