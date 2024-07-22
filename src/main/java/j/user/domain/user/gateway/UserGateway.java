package j.user.domain.user.gateway;

import java.util.List;

import j.user.domain.user.entities.User;

public interface UserGateway {
	void save(final User user);
	User find(final String anID);
	User findNickname(final String aNickname);
	List<User> list();
}
