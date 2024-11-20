package vn.iotstar.Demo2.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iotstar.Demo2.entity.UserInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserInfoUserDetails(UserInfo userInfo) {
		name = userInfo.getName();
		password = userInfo.getPassword();
		authorities = Arrays.stream(userInfo.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}
	
	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnables() {
		return true;
	}
}