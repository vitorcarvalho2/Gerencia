package j.user.infra.controllers.user.dtos.add;

public record AddUserRequestDto(
		String name,
		String email,
		String password,
		String nickname) {
}
