package j.user.infra.controllers.post.dtos.list;

import java.util.List;

import j.user.usecases.user.list.PostDto;

public record ListPostResponseDto(List<PostDto> posts) {

}
