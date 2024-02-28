package j.user.usecases.user.find;

import java.util.List;

import j.user.domain.post.entities.Post;

public record FindUserOutputDto(
		String id,
		String name,
		String email,
		String password,
		String nickname,
		List<Post> post) {
}
