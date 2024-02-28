package j.user.usecases.user.list;

import java.util.List;

public record UserDto(
		String id,
		String name,
		String email,
		String password,
		String nickname,
		List<PostDto> posts)  {
	

}
