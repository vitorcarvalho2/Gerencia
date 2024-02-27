package java.user.usecases.user.addposttouser;

import java.user.domain.post.entities.Post;
import java.user.domain.post.gateway.PostGateway;
import java.user.domain.user.gateway.UserGateway;
import java.user.usecases.Usecase;
import java.user.usecases.exceptions.NotFoundException;

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
		
		final var aUser = this.userGateway.find(input.userId());
		
		if( aUser == null) {
			throw new NotFoundException("User"+ input.postId()+"not found while adding Post.");
		}
		
		final var aPost = this.postGateway.find(input.postId());
		
		if( aPost == null) {
			throw new NotFoundException("Post"+ input.postId()+"not found while adding to User.");
		}
		
		final var aUserPost = Post.with(aPost.getId(),aPost.getTitle(),aPost.getContent(),aPost.getAuthor(),aPost.getDate());
		aUser.addPost(aUserPost);
		this.userGateway.save(aUser);
		
		final var anOutput = new AddPostOnUserOutputDto(aUser.getId(),aUser.getNickname());
		return anOutput;
	}
}
