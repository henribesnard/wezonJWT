package com.wezonjwtApi.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	@Size(max= 120)
	private String last_name;

	@Size(max= 120)
	private String first_name;
	
	@Size(max= 20)
	private String address_country_code;
	
	@Size(max= 120)
	private String state_or_province;
	
	@Size(max= 120)
	private String city;
	
	@Size(max= 120)
	private String postal_code;
	
	@Size(max= 120)
	private String address;
	
	@Size(max= 120)
	private String mobile_number;
	
	@Size(max= 120)
	private Date birth_date;
	
	@Size(max= 120)
	private String birth_place ;
	
	@Size(max= 120)
	private String birth_country_code;
	
	@Size(max= 120)
	private String bank_account_number;
	
	@Size(max= 120)
	private String bank_number;
	
	@Size(max= 120)
	private String bank_phone_number;
	
	@Size(max= 120)
	private String tax_id;
	
	@Size(max= 120)
	private String tax_id_name;
	
	@Size(max= 120)
	private String occupation;
	
	@Size(max= 120)
	private String employer_name;
	
	@Size(max= 120)
	private String employer_address;
	
	@Size(max= 120)
	private String language_code;
	
	@Size(max= 120)
	private String id_type;
	
	@Size(max= 120)
	private String id_country_code;
	
	@Size(max= 120)
	private Date id_issue_date;
	
	@Size(max= 120)
	private Date id_expiration_date;
	
	@Size(max= 120)
	private String id_number;
	
	@Size(max= 120)
	private String photo_id_front;
	
	@Size(max= 120)
	private String photo_id_back;
	
	@Size(max= 120)
	private String notary_approval_of_photo_id;
	
	@Size(max= 120)
	private String ip_address;
	
	@Size(max= 120)
	private String photo_proof_residence;
	
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User( ) {
	}

	public User(String username,
			String email, 
			String password,
			String last_name,
			String first_name,
			String address_country_code,
			String state_or_province,
			String city,
			String postal_code,
			String address,
			String mobile_number,
			Date birth_date,
			String birth_place,
			String birth_country_code,
			String bank_account_number,
			String bank_number,
			String bank_phone_number,
			String tax_id,
			String tax_id_name,
			String occupation,
			String employer_name,
			String employer_address,
			String language_code,
			String id_type,
			String id_country_code,
			Date id_issue_date,
			Date id_expiration_date,
			String id_number,
			String photo_id_front,
			String photo_id_back,
			String notary_approval_of_photo_id,
			String ip_address,
			String photo_proof_residence
			) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.last_name = last_name;
		this.first_name = first_name;
		this.address_country_code = address_country_code;
		this.state_or_province = state_or_province;
		this.state_or_province = state_or_province;
		this.city = city;
		this.postal_code = postal_code;
		this.address = address;
		this.mobile_number = mobile_number;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.birth_country_code = birth_country_code;
		this.bank_account_number = bank_account_number;
		this.bank_number = bank_number;
		this.bank_phone_number = bank_phone_number;
		this.tax_id = tax_id;
		this.tax_id_name = tax_id_name;
		this.occupation = occupation;
		this.employer_name = employer_name;
		this.employer_address = employer_address;
		this.language_code = language_code;
		this.id_type = id_type;
		this.id_country_code = id_country_code;
		this.id_issue_date = id_issue_date;
		this.id_expiration_date = id_expiration_date;
		this.id_number = id_number;
		this.photo_id_front = photo_id_front;
		this.photo_id_back = photo_id_back;
		this.notary_approval_of_photo_id = notary_approval_of_photo_id;
		this.ip_address = ip_address;
		this.photo_proof_residence = photo_proof_residence;
		
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getAddress_country_code() {
		return address_country_code;
	}

	public void setAddress_country_code(String address_country_code) {
		this.address_country_code = address_country_code;
	}

	public String getState_or_province() {
		return state_or_province;
	}

	public void setState_or_province(String state_or_province) {
		this.state_or_province = state_or_province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getBirth_country_code() {
		return birth_country_code;
	}

	public void setBirth_country_code(String birth_country_code) {
		this.birth_country_code = birth_country_code;
	}

	public String getBank_account_number() {
		return bank_account_number;
	}

	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}

	public String getBank_number() {
		return bank_number;
	}

	public void setBank_number(String bank_number) {
		this.bank_number = bank_number;
	}

	public String getBank_phone_number() {
		return bank_phone_number;
	}

	public void setBank_phone_number(String bank_phone_number) {
		this.bank_phone_number = bank_phone_number;
	}

	public String getTax_id() {
		return tax_id;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	public String getTax_id_name() {
		return tax_id_name;
	}

	public void setTax_id_name(String tax_id_name) {
		this.tax_id_name = tax_id_name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmployer_name() {
		return employer_name;
	}

	public void setEmployer_name(String employer_name) {
		this.employer_name = employer_name;
	}

	public String getEmployer_address() {
		return employer_address;
	}

	public void setEmployer_address(String employer_address) {
		this.employer_address = employer_address;
	}

	public String getLanguage_code() {
		return language_code;
	}

	public void setLanguage_code(String language_code) {
		this.language_code = language_code;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_country_code() {
		return id_country_code;
	}

	public void setId_country_code(String id_country_code) {
		this.id_country_code = id_country_code;
	}

	public Date getId_issue_date() {
		return id_issue_date;
	}

	public void setId_issue_date(Date id_issue_date) {
		this.id_issue_date = id_issue_date;
	}

	public Date getId_expiration_date() {
		return id_expiration_date;
	}

	public void setId_expiration_date(Date id_expiration_date) {
		this.id_expiration_date = id_expiration_date;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getPhoto_id_front() {
		return photo_id_front;
	}

	public void setPhoto_id_front(String photo_id_front) {
		this.photo_id_front = photo_id_front;
	}

	public String getPhoto_id_back() {
		return photo_id_back;
	}

	public void setPhoto_id_back(String photo_id_back) {
		this.photo_id_back = photo_id_back;
	}

	public String getNotary_approval_of_photo_id() {
		return notary_approval_of_photo_id;
	}

	public void setNotary_approval_of_photo_id(String notary_approval_of_photo_id) {
		this.notary_approval_of_photo_id = notary_approval_of_photo_id;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getPhoto_proof_residence() {
		return photo_proof_residence;
	}

	public void setPhoto_proof_residence(String photo_proof_residence) {
		this.photo_proof_residence = photo_proof_residence;
	}
}