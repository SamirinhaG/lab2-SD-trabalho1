import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class LivroInputStream extends InputStream {

    private InputStream origem;

    public LivroInputStream(InputStream origem) {
        this.origem = origem;
    }

    @Override
    public int read() throws IOException {
        return origem.read();
    }

    public Livro[] lerLivros() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(origem);
        int quantidade = ois.readInt();
        Livro[] livros = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            livros[i] = (Livro) ois.readObject();
        }
        return livros;
    }
}
