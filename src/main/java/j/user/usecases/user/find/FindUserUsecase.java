package j.user.usecases.user.find;

import j.user.domain.user.gateway.UserGateway;
import j.user.usecases.Usecase;
import j.user.usecases.exceptions.BusinessException;

public class FindUserUsecase implements Usecase<FindUserInputDto, FindUserOutputDto> {
	private UserGateway userGateway;
	
	private FindUserUsecase( UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public static FindUserUsecase create(final UserGateway aGateway) {
		return new FindUserUsecase(aGateway);
	}
	
	@Override
	public FindUserOutputDto execute(FindUserInputDto input) {
		var aUser = userGateway.find(input.id());
		if(aUser == null) {
		 throw new BusinessException("user not found");
	}
	return new FindUserOutputDto(aUser.getId(),aUser.getName(),aUser.getEmail(),aUser.getNickname(),aUser.getPassword(),aUser.getPosts());
	}
}
