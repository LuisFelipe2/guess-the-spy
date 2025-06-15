package luis_auth.guess_the_spy.repository;

import luis_auth.guess_the_spy.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CategoryRepository {
	List<Category> categories = new ArrayList<>();

	public CategoryRepository() {
		categories.add(new Category("Cidade", List.of(
			"escola", "hospital", "cinema", "embaixada", "aquário",
			"shopping", "praça", "igreja", "estádio", "mercado",
			"biblioteca", "museu", "aeroporto", "porto", "prefeitura",
			"parque", "zoológico", "teatro", "delegacia", "hotel"
		)));
		categories.add(new Category("Comida", List.of(
				"pizza", "hambúrguer", "sushi", "lasanha", "feijoada",
				"macarrão", "churrasco", "taco", "pastel", "risoto",
				"sopa", "cuscuz", "cachorro-quente", "salada", "moqueca",
				"carne de sol", "esfiha", "kibe", "pão de queijo", "torta"
			)));
		categories.add(new Category("Filme", List.of(
				"Titanic", "Avatar", "Matrix", "Gladiador", "Interestelar",
				"Corra", "A Origem", "O Iluminado", "Pulp Fiction", "Clube da Luta",
				"Senhor dos Anéis", "Harry Potter", "Vingadores", "Pantera Negra", "Frozen",
				"O Rei Leão", "Toy Story", "Forrest Gump", "Coringa", "Batman"
			)));
		categories.add(new Category("País", List.of(
				"Brasil", "Argentina", "França", "Alemanha", "Itália",
				"Espanha", "Portugal", "Canadá", "China", "Japão",
				"Coreia do Sul", "Austrália", "Egito", "Rússia", "Índia",
				"Grécia", "Turquia", "México", "Suécia", "Noruega"
			)));
		categories.add(new Category("Animal", List.of(
				"cachorro", "gato", "tigre", "leão", "girafa",
				"zebra", "elefante", "urso", "pinguim", "lobo",
				"águia", "cavalo", "camelo", "jacaré", "tubarão",
				"golfinho", "macaco", "coruja", "papagaio", "baleia"
			)));
		categories.add(new Category("Profissão", List.of(
				"médico", "professor", "engenheiro", "advogado", "policial",
				"bombeiro", "piloto", "cozinheiro", "motorista", "pintor",
				"ator", "cantor", "cientista", "jornalista", "arquiteto",
				"fotógrafo", "designer", "veterinário", "psicólogo", "juiz"
			)));
		categories.add(new Category("Objeto", List.of(
				"cadeira", "mesa", "garfo", "faca", "caneta",
				"lápis", "livro", "celular", "computador", "óculos",
				"relógio", "mochila", "televisão", "copo", "espelho",
				"sapato", "ventilador", "sofá", "escova", "guarda-chuva"
			)));
		categories.add(new Category("Esporte", List.of(
				"futebol", "basquete", "vôlei", "tênis", "natação",
				"ciclismo", "boxe", "judô", "surfe", "corrida",
				"golf", "esgrima", "handebol", "karatê", "automobilismo",
				"rugby", "beisebol", "ginástica", "remo", "xadrez"
			)));
		categories.add(new Category("Cor", List.of(
				"vermelho", "azul", "verde", "amarelo", "roxo",
				"preto", "branco", "cinza", "marrom", "rosa",
				"bege", "dourado", "prata", "turquesa", "lilás",
				"vinho", "laranja", "ocre", "azul-marinho", "caramelo"
			)));
		categories.add(new Category("Instrumento musical", List.of(
				"violão", "guitarra", "piano", "bateria", "flauta",
				"saxofone", "violino", "trompete", "teclado", "harpa",
				"acordeão", "contrabaixo", "cavaquinho", "ukulele", "clarinete",
				"oboé", "tamborim", "triângulo", "gaita", "tuba"
			)));
	}

	public Category findByName(String categoryName) {
		return categories.stream()
			.filter(c -> Objects.equals(c.getName(), categoryName))
			.findFirst()
			.orElse(null);
	}

	public Category create(Category category) {
		categories.add(category);
		return category;
	}

	public List<Category> getAll() {
		return categories;
	}
}
