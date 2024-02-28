package j.user.infra.controllers.user.dtos.add;

public record AddUserResponseDto(
		String id,
		String name,
		String email,
		String password,
		String nickname) {
}
