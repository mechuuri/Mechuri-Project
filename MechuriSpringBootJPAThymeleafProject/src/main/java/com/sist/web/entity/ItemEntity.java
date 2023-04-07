package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/*
INO int 
IMAGE text 
NAME text 
DESCRIPTION text 
STATUS text 
BRAND text 
PRICE int 
SALE int 
STOCK int 
LIKE_CNT int 
JJIM_CNT int 
ICNO int
 */
@Entity
@Table(name="hc_item")
@Getter
@Setter
public class ItemEntity {
	@Id
	private int ino;
	private int icno,price,sale,stock,like_cnt,jjim_cnt;
	private String image,name,description,status,brand;
}

