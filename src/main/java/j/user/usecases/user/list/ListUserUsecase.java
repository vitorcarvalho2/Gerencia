package j.user.usecases.user.list;


import java.util.ArrayList;
import java.util.List;

import j.user.domain.post.entities.Post;
import j.user.domain.user.entities.User;
import j.user.domain.user.gateway.UserGateway;
import j.user.usecases.Usecase;

public class ListUserUsecase implements Usecase<ListUserInputDto, ListUserOutputDto >{

	private UserGateway userGateway;

	private ListUserUsecase(final UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public static ListUserUsecase create(final UserGateway userGateway) {
		return new ListUserUsecase(userGateway);
	}

	@Override
	public ListUserOutputDto execute(final ListUserInputDto input) {
		final var theUsers = this.userGateway.list();
		
		final var theUsersDto = this.createOutput(theUsers);
		
		final var anOutput = new ListUserOutputDto(theUsersDto);
		
		return anOutput;
	}
	
	private List<UserDto> createOutput(final List<User> users){
		List<UserDto> aUsersDto = new ArrayList<UserDto>();
		
		for(User u : users) {
			List<PostDto> aPostsDto = new ArrayList<PostDto>();
			
			for(Post p : u.getPosts()) {
				
				final var aPostDto = new PostDto(
						p.getId(),
						p.getTitle(),
						p.getContent(),
						p.getAuthor(),
						p.getDate());
				aPostsDto.add(aPostDto);
			}
			aUsersDto.add(new UserDto(u.getId(), u.getName(), u.getEmail(),u.getPassword(), u.getNickname(), aPostsDto));
		}
		return aUsersDto;
	}
}
