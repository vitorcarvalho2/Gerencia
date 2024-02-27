package java.user.usecases.post.find;

import java.user.domain.post.gateway.PostGateway;
import java.user.usecases.Usecase;
import java.user.usecases.exceptions.NotFoundException;

public class FindPostUsecase implements Usecase<FindPostInputDto, FindPostOutputDto> {
    private PostGateway postGateway;
    
    private FindPostUsecase(PostGateway postGateway) {
    	this.postGateway = postGateway;
    }
    
    public static FindPostUsecase create(final PostGateway aGateway) {
    	return new FindPostUsecase(aGateway);
    }
	
	@Override
	public FindPostOutputDto execute(FindPostInputDto input) {
		var aPost = postGateway.find(input.id());
		 if(aPost == null) {
	            throw new NotFoundException("post not found");
	        }
		 return new FindPostOutputDto(aPost.getId(),aPost.getTitle(),aPost.getContent(),aPost.getAuthor(),aPost.getDate());
	}

}
