package com.citadini.ourcity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

public class UserSS implements UserDetails {
	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String password;
	
	public UserSS() {
		
	}
	
	public UserSS(Long id, String email, String senha, String userName) {
		super();
		this.id = id;
		this.email = email;
		this.password = senha;
		this.authorizes = List.of(new SimpleGrantedAuthority("HOLE_CLIENTE"));
	}

	private Collection<? extends GrantedAuthority> authorizes;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorizes;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return email;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
