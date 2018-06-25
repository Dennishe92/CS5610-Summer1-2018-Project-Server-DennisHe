//package com.example.myproject3.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
////@Entity
//public class Payment {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int id;
//	private String cardType;
//	private int cardNumber;
//	
//	@ManyToOne()
//	@JsonIgnore
//	private Customer owner;
//	
//	public void setOwner(Customer customer) {
//		this.owner = customer;
//		if(!owner.getOwnedPayment().contains(this)) {
//			owner.getOwnedPayment().add(this);
//		}
//	}
//	
//	public Customer getOwner() {
//		return this.owner;
//	}
//	
//}
