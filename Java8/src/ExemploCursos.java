import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Curso {
	private String nome;
	private int alunos;

	public Curso(String nome, int alunos) {
		this.nome = nome;
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public int getAlunos() {
		return alunos;
	}
}

public class ExemploCursos {
	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));

		cursos.sort(Comparator.comparingInt(Curso::getAlunos));

		Stream<Curso> streamDeCurso = cursos.stream().filter(c -> c.getAlunos() > 100);
		streamDeCurso.forEach(c -> System.out.println(c.getNome()));
		
		cursos.stream()
		   .filter(c -> c.getAlunos() > 100)
		   .forEach(c -> System.out.println(c.getNome()));
		
		cursos.stream()
		   .filter(c -> c.getAlunos() > 100)
		   .map(Curso::getAlunos)
		   .forEach(System.out::println);
		
		cursos = cursos.stream()
				   .filter(c -> c.getAlunos() > 100)
				   .collect(Collectors.toList());
		
		Map mapa = cursos 
				.stream() 
				.filter(c -> c.getAlunos() > 100) 
				.collect(Collectors.toMap(c -> c.getNome(), c -> c.getAlunos()));
		
		int soma = cursos.stream()
				   .filter(c -> c.getAlunos() > 100)
				   .mapToInt(Curso::getAlunos)
				   .sum();
		
		cursos.stream()
		   .filter(c -> c.getAlunos() > 100)
		   .findAny()
		   .ifPresent(c -> System.out.println(c.getNome()));
	}
}
