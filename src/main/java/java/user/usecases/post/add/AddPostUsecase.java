package java.user.usecases.post.add;

import java.user.domain.post.entities.Post;
import java.user.domain.post.gateway.PostGateway;
import java.user.usecases.Usecase;

public class AddPostUsecase implements Usecase<AddPostInputDto, AddPostOutputDto> {
	private PostGateway postGateway;

	private AddPostUsecase(PostGateway postGateway) {
		this.postGateway = postGateway;
	}

	public static AddPostUsecase create(final PostGateway aGateway) {
		return new AddPostUsecase(aGateway);
	}

	@Override
	public AddPostOutputDto execute(final AddPostInputDto input) {
		final var aPost = Post.create(input.title(), input.content(),input.author(),input.date());
		postGateway.save(aPost);

		return new AddPostOutputDto(aPost.getId(), aPost.getTitle(), aPost.getContent(),aPost.getAuthor(),aPost.getDate());
	}
}

