package security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class GithubAuthentication implements Authentication {

	@Override
	public String getName() {
		return "Github user";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return "Github";
	}

	@Override
	public Object getPrincipal() {
		return "Github";
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		throw new IllegalArgumentException("Not supported");
	}

}
