package j.user.infra.repositories.user.memory;

import java.util.HashMap;
import java.util.List;

import j.user.domain.user.entities.User;
import j.user.domain.user.gateway.UserGateway;

public class UserRepositoryMemory implements UserGateway {
	private HashMap<String, User> users;

	private UserRepositoryMemory() {
		super();
		this.users = new HashMap<>();
	}

	public static UserRepositoryMemory create() {
		return new UserRepositoryMemory();
	}
	
	@Override
	public void save(final User aUser) {
		this.users.put(aUser.getId(), aUser);
	}
	
	@Override
	public User find(String anId) {
		return this.users.get(anId);
	}
	
	@Override
	public List<User> list(){
		return List.copyOf(this.users.values());
	}

	@Override
	public User findNickname(String nickname) {
		return this.users.get(nickname);
	}

	
}
