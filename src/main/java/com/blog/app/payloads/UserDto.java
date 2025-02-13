package com.blog.app.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=4,message="Username must be min of 4 characters")
	private String name;

	@jakarta.validation.constraints.Email(message="Email address must be valid")
	private String email;

	@NotEmpty
	@Size(min=4,max=10,message="Password must be min of 4 and max of 10 chars !")
	private String password;

	
	private Set<RoleDto> roleDto=new HashSet<>();
	@NotEmpty
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<RoleDto> getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(Set<RoleDto> roleDto) {
		this.roleDto = roleDto;
	}
	
	

}
