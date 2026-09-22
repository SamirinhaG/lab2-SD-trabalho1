import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorMulticast {

    private static final String IP_GRUPO = "230.0.0.1";
    private static final int PORTA_MULTICAST = 6789;
    private static final int PORTA_TCP_AUTH = 6000;

    public static void main(String[] args) throws Exception {
        new Thread(ServidorMulticast::rodarAutenticacao).start();
        rodarEnvioMulticast();
    }

    private static void rodarAutenticacao() {
        try {
            try (ServerSocket servidorTCP = new ServerSocket(PORTA_TCP_AUTH)) {
                System.out.println("Servidor de autenticação TCP na porta " + PORTA_TCP_AUTH);

                while (true) {
                    Socket cliente = servidorTCP.accept();
                    new Thread(() -> autenticar(cliente)).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void autenticar(Socket cliente) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            String login = entrada.readLine();
            System.out.println("Login recebido: " + login);

            if (login != null && !login.trim().isEmpty()) {
                saida.println("AUTENTICADO");
            } else {
                saida.println("REJEITADO");
            }

            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void rodarEnvioMulticast() throws Exception {
        MulticastSocket socket = new MulticastSocket();
        InetAddress grupo = InetAddress.getByName(IP_GRUPO);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Servidor multicast pronto.");
        System.out.println("Digite mensagens no formato TIPO;mensagem, ex:");
        System.out.println("  NOTIFICACAO;Novo dado disponível");
        System.out.println("Tipos sugeridos: NOTIFICACAO, ALERTA, ATUALIZACAO. Digite 'sair' para encerrar.");

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("sair")) {
                break;
            }

            String[] partes = linha.split(";", 2);
            String tipo = partes.length > 0 ? partes[0] : "NOTIFICACAO";
            String mensagem = partes.length > 1 ? partes[1] : linha;

            Notificacao notificacao = new Notificacao(tipo, mensagem, System.currentTimeMillis());
            byte[] dados = notificacao.toJson().getBytes();

            DatagramPacket pacote = new DatagramPacket(dados, dados.length, grupo, PORTA_MULTICAST);
            socket.send(pacote);

            System.out.println("Enviado: " + notificacao);
        }

        socket.close();
        scanner.close();
    }
}
