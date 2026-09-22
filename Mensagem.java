import java.io.Serializable;

public class Mensagem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipo;
    private String operacao;
    private Livro livro;
    private String status;
    private String detalhes;

    public Mensagem() {
    }

    public Mensagem(String tipo, String operacao, Livro livro, String status, String detalhes) {
        this.tipo = tipo;
        this.operacao = operacao;
        this.livro = livro;
        this.status = status;
        this.detalhes = detalhes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return "Mensagem{tipo='" + tipo + "', operacao='" + operacao + "', livro=" + livro +
                ", status='" + status + "', detalhes='" + detalhes + "'}";
    }
}
