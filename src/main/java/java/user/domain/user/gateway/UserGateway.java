package java.user.domain.user.gateway;

import java.user.domain.user.entities.User;
import java.util.List;

public interface UserGateway {
	void save(final User user);
	User find(final String anID);
	List<User> list();
}
