package j.user.infra.controllers.user.dtos.find;

import java.util.List;

import j.user.usecases.user.list.PostDto;

public record FindUserResponseDto(
		String id,
		 String name,
		 String email,
		 String password,
		 String nickname,
		 List<PostDto> posts){

}
