import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private List<Livro> livros = new ArrayList<>();

    public void cadastrar(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarPorId(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public List<Livro> listarTodos() {
        return livros;
    }

    public boolean remover(int id) {
        return livros.removeIf(l -> l.getId() == id);
    }
}
