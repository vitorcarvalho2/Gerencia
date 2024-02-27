package java.user.usecases.user.add;

import java.user.domain.post.entities.Post;
import java.util.List;

public record AddUserOutputDto(String id,String name,String email,String password,String nickname, List<Post> posts) {

}

