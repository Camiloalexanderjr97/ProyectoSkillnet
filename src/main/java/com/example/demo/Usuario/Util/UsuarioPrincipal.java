package com.example.demo.Usuario.Util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Usuario.Entity.Usuario;

public class UsuarioPrincipal implements UserDetails{
	
	private long id;
	private String nombre;
	private String username;
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	
	public long getid() {
		return id;
	}





	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public UsuarioPrincipal(long id, String nombre, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UsuarioPrincipal build(Usuario user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(
					Collectors.toList());
		return new UsuarioPrincipal(user.getId(),user.getName(),user.getUsername(),user.getPassword(), authorities);
		
	}

}
