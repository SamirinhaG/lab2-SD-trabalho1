import java.io.Serializable;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private double preco;

    public Livro() {
    }

    public Livro(int id, String titulo, String autor, int ano, double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{id=" + id + ", titulo='" + titulo + "', autor='" + autor +
                "', ano=" + ano + ", preco=" + preco + "}";
    }
}
