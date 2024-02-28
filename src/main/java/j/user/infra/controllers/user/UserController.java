package j.user.infra.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import j.user.domain.post.gateway.PostGateway;
import j.user.domain.user.gateway.UserGateway;
import j.user.infra.controllers.user.dtos.add.AddUserRequestDto;
import j.user.infra.controllers.user.dtos.add.AddUserResponseDto;
import j.user.infra.controllers.user.dtos.find.FindUserResponseDto;
import j.user.infra.controllers.user.dtos.list.ListUserResponseDto;
import j.user.infra.controllers.user.dtos.list.UserDto;
import j.user.infra.repositories.user.memory.UserRepositoryMemory;
import j.user.usecases.user.add.AddUserInputDto;
import j.user.usecases.user.add.AddUserUsecase;
import j.user.usecases.user.find.FindUserInputDto;
import j.user.usecases.user.find.FindUserUsecase;
import j.user.usecases.user.list.ListUserInputDto;
import j.user.usecases.user.list.ListUserUsecase;
import j.user.usecases.user.list.PostDto;
import j.user.utils.PostRepositoryUtils;

@RestController
@RequestMapping("gerencia/users")
public class UserController {
	
	private UserGateway userGateway;
	private PostGateway postGateway;
	
	
	public UserController() {
		super();
		this.userGateway = UserRepositoryMemory.create();
		this.postGateway = PostRepositoryUtils.getPostGateway();
	}
	
	@PostMapping("/add")
	public AddUserResponseDto addUser(@RequestBody AddUserRequestDto request) {
		final var anInput = new AddUserInputDto(request.name(), request.email(), request.password(), request.nickname());
	
		final var aResult = AddUserUsecase.create(userGateway).execute(anInput);
	
		final var aResponse = new AddUserResponseDto(
				aResult.id(),
				aResult.name(),
				aResult.email(),
				aResult.nickname(),
				aResult.password());
		
		return aResponse;
	}
	
	@GetMapping("/id/{id}")
	public FindUserResponseDto findUser(@PathVariable String id) {
		final var anInput = new FindUserInputDto(id);
		final var aResult = FindUserUsecase.create(userGateway).execute(anInput);
		final List<PostDto> thePosts = new ArrayList<PostDto>();
		aResult.post().forEach(post ->{
			final var aPostDto = new PostDto(
					post.getId(), 
					post.getTitle(),
					post.getContent(),
					post.getAuthor(), 
					post.getDate());
			
			thePosts.add(aPostDto);
		});
		final var aResponse = new FindUserResponseDto(
				aResult.id(),
				aResult.name(), 
				aResult.email(),
				aResult.nickname(),
				aResult.password(),
				thePosts);
		
		return aResponse;
		
	}
	
	@GetMapping("/list")
	public ListUserResponseDto listUsers() {
		 final var anInput = new ListUserInputDto();

	     final var aResult = ListUserUsecase.create(userGateway).execute(anInput);

	     final List<UserDto> theUsers = new ArrayList<>();
	     
	     aResult.users().forEach(user ->{
	    	 final ArrayList<PostDto> thePosts = new ArrayList<PostDto>();
	    	 user.posts().forEach(post->{
	    		 final var aPostDto = new PostDto(
	    				 post.id(),
	    				 post.title(),
	    				 post.content(),
	    				 post.author(),
	    				 post.date());
	    		 
	    		 thePosts.add(aPostDto);
	    	 });
	    	 
	    	 final var aUserDto = new UserDto(
	    			 user.id(),
	    			 user.name(),
	    			 user.email(),
	    			 user.password(),
	    			 user.nickname(),
	    			 thePosts);
	    	 
	    	 theUsers.add(aUserDto);
	     });
	     
	    final var aResponse = new ListUserResponseDto(theUsers);
	    		
		return aResponse;
	}
}
