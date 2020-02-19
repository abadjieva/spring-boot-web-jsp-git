package com.mkyong.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Index;

/**
 * AVS_MAPPIS<br>
 * de.avs.mappis.domainmodel.entities User.java <br>
 * Entity zur Tabelle USER
 * 
 * @author Rene Fuessel, AVS GmbH, Maerz 2012<br>
 * @version 1.0<br>
 */

@Entity
// @Table(name = Constants.TABLE_NAME_USER, catalog = Constants.DATABASE_SCHEMA, uniqueConstraints =
// {@UniqueConstraint(columnNames = {"USERNAME"})})
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USERNAME"})})
public class UserEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	private long userId;
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private boolean delete;
	private String firstName;
	private String lastName;
	private Date geaendertAm;
	private Long geaendertVon;


	public UserEntity(){
	}

	public UserEntity(long userId, String username, String password, boolean enabled, boolean delete, String firstName, String lastName,
			Date geaendertAm, Long geaendertVon){
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.delete = delete;
		this.firstName = firstName;
		this.lastName = lastName;
		this.geaendertAm = geaendertAm;
		this.geaendertVon = geaendertVon;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getUserId(){
		return this.userId;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	@Index(name = "IDX_USER_USERNAME")
	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	public String getUsername(){
		return this.username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword(){
		return this.password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	@Column(name = "EMAIL", length = 250)
	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	/*
	 * @Column(name = "USERDELETE",
	 * nullable = false,
	 * precision = 1,
	 * scale = 0)
	 */
	@Column(name = "USERDELETE", nullable = false, columnDefinition = "BIT", length = 1)
	public boolean isDelete(){
		return this.delete;
	}

	public void setDelete(boolean delete){
		this.delete = delete;
	}

	/*
	 * @Column(name = "ENABLED",
	 * nullable = false,
	 * precision = 1,
	 * scale = 0)
	 */
	@Column(name = "ENABLED", nullable = false, columnDefinition = "BIT", length = 1)
	public boolean isEnabled(){
		return this.enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	@Column(name = "FIRST_NAME", length = 200)
	public String getFirstName(){
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", length = 200)
	public String getLastName(){
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GEAENDERT_AM", length = 10)
	public Date getGeaendertAm(){
		return this.geaendertAm;
	}

	public void setGeaendertAm(Date geaendertAm){
		this.geaendertAm = geaendertAm;
	}

	@Column(name = "GEAENDERT_VON", length = 20)
	public Long getGeaendertVon(){
		return this.geaendertVon;
	}

	public void setGeaendertVon(Long geaendertVon){
		this.geaendertVon = geaendertVon;
	}

/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANDANT_ROLE_ID", nullable = false)
	public Role getUserRole(){
		return this.userRole;
	}

	public void setUserRole(Role userRole){
		this.userRole = userRole;
	}
*/

}