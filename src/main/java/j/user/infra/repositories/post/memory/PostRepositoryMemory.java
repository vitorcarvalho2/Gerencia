package j.user.infra.repositories.post.memory;

import java.util.HashMap;
import java.util.List;

import j.user.domain.post.entities.Post;
import j.user.domain.post.gateway.PostGateway;

public class PostRepositoryMemory implements PostGateway {

	private HashMap<String, Post> posts;

	private PostRepositoryMemory() {
		super();
		this.posts = new HashMap<>();
	}

	public static PostRepositoryMemory create() {
		return new PostRepositoryMemory();
	}

	@Override
	public void save(final Post aPost) {
		this.posts.put(aPost.getId(), aPost);
	}

	@Override
	public Post find(String anId) {
		return this.posts.get(anId);
	}

	@Override
	public List<Post> list() {
		return List.copyOf(this.posts.values());
	}

}
