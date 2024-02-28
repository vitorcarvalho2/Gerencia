package j.user.usecases.post.list;

import java.util.List;

import j.user.usecases.user.list.PostDto;

public record ListPostOutputDto(List<PostDto> posts) {


}
