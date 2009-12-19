package ua.kiev.kpi.comsys.hp.model.security;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

public class User extends org.springframework.security.userdetails.User{
	public User(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, GrantedAuthority[] authorities, int userId)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userId = userId;
		// TODO Auto-generated constructor stub
	}

	public User(String username, UserDetails user, GrantedAuthority[] arrayAuths) {
		super(username, user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(),
				user.isAccountNonLocked(), arrayAuths);
		this.userId = ((User)user).getUserId();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
