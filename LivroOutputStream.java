import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class LivroOutputStream extends OutputStream {

    private OutputStream destino;
    private Livro[] livros;
    private int quantidade;

    public LivroOutputStream(OutputStream destino, Livro[] livros, int quantidade) {
        this.destino = destino;
        this.livros = livros;
        this.quantidade = quantidade;
    }

    @Override
    public void write(int b) throws IOException {
        destino.write(b);
    }

    public void enviarLivros() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(destino);
        oos.writeInt(quantidade);
        for (int i = 0; i < quantidade; i++) {
            oos.writeObject(livros[i]);
        }
        oos.flush();
    }
}
