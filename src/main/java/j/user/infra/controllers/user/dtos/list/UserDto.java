package j.user.infra.controllers.user.dtos.list;

import java.util.List;

import j.user.usecases.user.list.PostDto;

public record UserDto(
		String id,
		String name,
		String email,
		String password,
		String nickname,
		List<PostDto> posts) {

}
