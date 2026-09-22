import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClienteMulticast {

    private static final String IP_GRUPO = "230.0.0.1";
    private static final int PORTA_MULTICAST = 6789;
    private static final String HOST_SERVIDOR = "localhost";
    private static final int PORTA_TCP_AUTH = 6000;

    private static volatile boolean escutando = true;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu login: ");
        String login = scanner.nextLine();

        if (!autenticar(login)) {
            System.out.println("Login rejeitado. Encerrando.");
            scanner.close();
            return;
        }
        System.out.println("Login aceito!");

        InetAddress grupo = InetAddress.getByName(IP_GRUPO);
        NetworkInterface interfaceRede = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        SocketAddress enderecoGrupo = new InetSocketAddress(grupo, PORTA_MULTICAST);

        MulticastSocket socket = new MulticastSocket(PORTA_MULTICAST);
        socket.joinGroup(enderecoGrupo, interfaceRede);
        System.out.println("Entrou no grupo multicast " + IP_GRUPO + ":" + PORTA_MULTICAST);

        Thread threadEscuta = new Thread(() -> escutarMulticast(socket));
        threadEscuta.start();

        System.out.println("Digite 'sair' a qualquer momento para deixar o grupo e encerrar.");
        while (true) {
            String comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("sair")) {
                escutando = false;
                socket.leaveGroup(enderecoGrupo, interfaceRede);
                socket.close();
                System.out.println("Saiu do grupo multicast.");
                break;
            }
        }

        scanner.close();
    }

    private static boolean autenticar(String login) {
        try {
            Socket socket = new Socket(HOST_SERVIDOR, PORTA_TCP_AUTH);
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            saida.println(login);
            String resposta = entrada.readLine();

            socket.close();
            return "AUTENTICADO".equals(resposta);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void escutarMulticast(MulticastSocket socket) {
        byte[] buffer = new byte[2048];
        while (escutando) {
            try {
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);
                String json = new String(pacote.getData(), 0, pacote.getLength());
                Notificacao notificacao = Notificacao.fromJson(json);
                System.out.println("\n[Recebido] " + notificacao);
            } catch (Exception e) {
                if (escutando) {
                    e.printStackTrace();
                }
            }
        }
    }
}
