package j.user.usecases.post.add;

import j.user.domain.post.entities.Post;
import j.user.domain.post.gateway.PostGateway;
import j.user.domain.user.gateway.UserGateway;
import j.user.usecases.Usecase;
import j.user.usecases.exceptions.BusinessException;

public class AddPostUsecase implements Usecase<AddPostInputDto, AddPostOutputDto> {

	private PostGateway postGateway;
	private UserGateway userGateway;

	private AddPostUsecase(PostGateway postGateway, UserGateway userGateway) {
		this.postGateway = postGateway;
		this.userGateway = userGateway;

	}

	public static AddPostUsecase create(final PostGateway pGateway, final UserGateway uGateway) {
		return new AddPostUsecase(pGateway, uGateway);
	}

	public AddPostOutputDto execute(final AddPostInputDto input) {
		final var aUser = this.userGateway.findNickname(input.author());
		if (aUser == null) {
			throw new BusinessException("User " + input.author() + " not found while adding Post.");
		}
		final var aPost = Post.create(input.title(), input.content(), input.author(), input.date());
		this.postGateway.save(aPost);
		aUser.addPost(aPost);
		final var anOutput = new AddPostOutputDto(aPost.getId(), aPost.getTitle(), aPost.getContent(), aPost.getAuthor(),
				aPost.getDate());
		return anOutput;+
	}
}
