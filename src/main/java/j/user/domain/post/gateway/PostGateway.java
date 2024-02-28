package j.user.domain.post.gateway;

import java.util.List;

import j.user.domain.post.entities.Post;

public interface PostGateway {
    void save(Post post);
    Post find(String anId);
	List<Post> list();
}
