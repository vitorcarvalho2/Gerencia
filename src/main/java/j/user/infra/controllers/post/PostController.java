package j.user.infra.controllers.post;


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
import j.user.infra.controllers.post.dtos.add.AddPostRequestDto;
import j.user.infra.controllers.post.dtos.add.AddPostResponseDto;
import j.user.infra.controllers.post.dtos.find.FindPostResponseDto;
import j.user.infra.controllers.post.dtos.list.ListPostResponseDto;
import j.user.infra.repositories.user.memory.UserRepositoryMemory;
import j.user.usecases.post.add.AddPostInputDto;
import j.user.usecases.post.add.AddPostUsecase;
import j.user.usecases.post.find.FindPostInputDto;
import j.user.usecases.post.find.FindPostUsecase;
import j.user.usecases.post.list.ListPostInputDto;
import j.user.usecases.post.list.ListPostUsecase;
import j.user.usecases.user.list.PostDto;
import j.user.utils.PostRepositoryUtils;


@RestController
@RequestMapping("gerencia/posts")
public class PostController {

	private PostGateway postGateway;
	private UserGateway userGateway;
	
	public PostController() {
		super();
		this.postGateway = PostRepositoryUtils.getPostGateway();
		this.userGateway = UserRepositoryMemory.create();
	}
	
    @PostMapping("/add")
	public AddPostResponseDto add(@RequestBody AddPostRequestDto aRequest) {
    	final var input = new AddPostInputDto(
    			aRequest.title(),
    			aRequest.content(),
    			aRequest.author(),
    			aRequest.date()
    	);
    	
    	 final var aResult = AddPostUsecase.create(postGateway,userGateway).execute(input);
    	 final var aResponse = new AddPostResponseDto(
    			 aResult.id(),
    			 aResult.title(),
    			 aResult.content(),
    			 aResult.author(),
    			 aResult.date());
    
    	 return aResponse;

	}
    
    @GetMapping("/id/{id}")
    public FindPostResponseDto findPost(@PathVariable String id) {
    	final var anInput = new FindPostInputDto(id);
    	final var aResult = FindPostUsecase.create(postGateway).execute(anInput);
    	final var aResponse = new FindPostResponseDto(
    			aResult.id(),
                aResult.title(),
                aResult.content(),
                aResult.author(),
                aResult.date());
    	 return aResponse;
    }
    
    @GetMapping("/list")
    public ListPostResponseDto listPosts() {
    	final var anInput = new ListPostInputDto();
    	final var aResult = ListPostUsecase.create(postGateway).execute(anInput);
		final List<PostDto> thePosts = new ArrayList<>();
    	
		aResult.posts().forEach(post ->{
			 final var aPostDto = new PostDto(
    				 post.id(),
    				 post.title(),
    				 post.content(),
    				 post.author(),
    				 post.date());
 
			 thePosts.add(aPostDto);
		});
    	final var aResponse = new ListPostResponseDto(thePosts);
    	return aResponse;
    	
    }
   
}
