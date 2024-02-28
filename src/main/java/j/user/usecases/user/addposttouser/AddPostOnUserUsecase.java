package j.user.usecases.user.addposttouser;

import j.user.domain.post.entities.Post;
import j.user.domain.post.gateway.PostGateway;
import j.user.domain.user.gateway.UserGateway;
import j.user.usecases.Usecase;
import j.user.usecases.exceptions.BusinessException;

public class AddPostOnUserUsecase implements Usecase<AddPostOnUserInputDto,AddPostOnUserOutputDto> {

	private UserGateway userGateway;
	private PostGateway postGateway;
	
	private AddPostOnUserUsecase(UserGateway usergateway,PostGateway postGateway) {
		this.postGateway = postGateway;
		this.userGateway = usergateway;
	}
	
	public AddPostOnUserUsecase create(UserGateway userGateway,PostGateway postGateway) {
		return new AddPostOnUserUsecase(userGateway,postGateway);
	}
	
	public AddPostOnUserOutputDto execute(AddPostOnUserInputDto input) {
		
		final var aPost = this.postGateway.find(input.postId());
		
		if( aPost == null) {
			throw new BusinessException("Post"+ input.postId()+"not found while adding Post.");
		}
		
		final var aUser = this.userGateway.find(input.userId());
		
		if( aUser == null) {
			throw new BusinessException("user"+ input.userId()+"not found while adding to User.");
		}
		
		final var aUserPost = Post.with(aPost.getId(),aPost.getTitle(),aPost.getContent(),aPost.getAuthor(),aPost.getDate());
		aUser.addPost(aUserPost);
		this.userGateway.save(aUser);
		
		final var anOutput = new AddPostOnUserOutputDto(aUser.getId(),aUser.getNickname(), aPost.getAuthor());
		return anOutput;
	}
}
