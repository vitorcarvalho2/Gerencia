package j.user.usecases.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError {

	private Integer codigo;
	private LocalDateTime momento;
	private String descricao;
	private List<Fields> campos;

	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	public static class Fields {
		private String nome;
		private String mensagem;
	}
}
