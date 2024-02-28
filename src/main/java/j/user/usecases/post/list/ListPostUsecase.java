package j.user.usecases.post.list;


import java.util.ArrayList;
import java.util.List;

import j.user.domain.post.entities.Post;
import j.user.domain.post.gateway.PostGateway;
import j.user.usecases.Usecase;
import j.user.usecases.user.list.PostDto;

public class ListPostUsecase implements Usecase<ListPostInputDto, ListPostOutputDto >{

	private PostGateway postGateway;

	private ListPostUsecase(final PostGateway postGateway) {
		this.postGateway = postGateway;
	}
	
	public static ListPostUsecase create(final PostGateway postGateway) {
		return new ListPostUsecase(postGateway);
	}

	@Override
	public ListPostOutputDto execute(final ListPostInputDto input) {
		final var thePosts = this.postGateway.list();
		
		final var thePostsDto = this.createOutput(thePosts);
		
		final var anOutput = new ListPostOutputDto(thePostsDto);
		
		return anOutput;
	}
	
	private List<PostDto> createOutput(final List<Post> posts){
		List<PostDto> thePostsDto = new ArrayList<PostDto>();
		
		for(Post p : posts) {		
			thePostsDto.add(new PostDto(p.getId(), p.getTitle(), p.getContent(),p.getAuthor(), p.getDate()));
		}
		return thePostsDto;
	}
}
