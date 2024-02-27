package java.user.domain.post.gateway;

import java.user.domain.post.entities.Post;

public interface PostGateway {
    void save(Post post);
    Post find(String anId);
}
