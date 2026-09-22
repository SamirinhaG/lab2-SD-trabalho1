import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSerializacao {

    private static LivroService livroService = new LivroService();

    public static void main(String[] args) throws Exception {
        int porta = 5001;
        try (ServerSocket servidor = new ServerSocket(porta)) {
            System.out.println("Servidor de serialização aguardando na porta " + porta + "...");

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(() -> atender(cliente)).start();
            }
        }
    }

    private static void atender(Socket cliente) {
        try {
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            Mensagem request = (Mensagem) entrada.readObject();
            System.out.println("Requisição recebida: " + request);

            Mensagem reply;

            switch (request.getOperacao()) {
                case "CADASTRAR":
                    livroService.cadastrar(request.getLivro());
                    reply = new Mensagem("REPLY", "CADASTRAR", request.getLivro(), "OK",
                            "Livro cadastrado com sucesso");
                    break;
                case "BUSCAR":
                    Livro encontrado = livroService.buscarPorId(request.getLivro().getId());
                    if (encontrado != null) {
                        reply = new Mensagem("REPLY", "BUSCAR", encontrado, "OK", "Livro encontrado");
                    } else {
                        reply = new Mensagem("REPLY", "BUSCAR", null, "ERRO", "Livro não encontrado");
                    }
                    break;
                default:
                    reply = new Mensagem("REPLY", request.getOperacao(), null, "ERRO", "Operação desconhecida");
            }

            saida.writeObject(reply);
            saida.flush();

            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
