package java.user.usecases.user.add;

import java.user.domain.user.entities.User;
import java.user.domain.user.gateway.UserGateway;
import java.user.usecases.Usecase;

public class AddUserUsecase implements Usecase<AddUserInputDto,AddUserOutputDto> {
	private UserGateway userGateway;
	
	private AddUserUsecase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public static AddUserUsecase create(final UserGateway aGateway) {
		return new AddUserUsecase(aGateway);
	}
	
	@Override
	public AddUserOutputDto execute(final AddUserInputDto input) {
		final var aUser = User.create(input.name(), input.email(),input.password(),input.nickname());
		userGateway.save(aUser);

	 return new AddUserOutputDto(aUser.getId(),aUser.getName(), aUser.getEmail(), aUser.getPassword(),aUser.getNickname(),aUser.getPosts());
	}
}
