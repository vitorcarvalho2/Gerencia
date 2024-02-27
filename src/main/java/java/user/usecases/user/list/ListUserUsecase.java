package java.user.usecases.user.list;


import java.user.domain.post.entities.Post;
import java.user.domain.user.entities.User;
import java.user.domain.user.gateway.UserGateway;
import java.user.usecases.Usecase;
import java.util.ArrayList;
import java.util.List;

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
		final var aUsers = this.userGateway.list();
		
		final var aUsersDto = this.createOutput(aUsers);
		
		final var anOutput = new ListUserOutputDto(aUsersDto);
		
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
