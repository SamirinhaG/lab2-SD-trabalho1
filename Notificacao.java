public class Notificacao {

    private String tipo;
    private String mensagem;
    private long timestamp;

    public Notificacao(String tipo, String mensagem, long timestamp) {
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String toJson() {
        return "{"
                + "\"tipo\":\"" + tipo + "\","
                + "\"mensagem\":\"" + mensagem + "\","
                + "\"timestamp\":" + timestamp
                + "}";
    }

    public static Notificacao fromJson(String json) {
        String tipo = extrairCampoString(json, "tipo");
        String mensagem = extrairCampoString(json, "mensagem");
        long timestamp = Long.parseLong(extrairCampoNumero(json, "timestamp"));
        return new Notificacao(tipo, mensagem, timestamp);
    }

    private static String extrairCampoString(String json, String campo) {
        String chave = "\"" + campo + "\":\"";
        int inicio = json.indexOf(chave) + chave.length();
        int fim = json.indexOf("\"", inicio);
        return json.substring(inicio, fim);
    }

    private static String extrairCampoNumero(String json, String campo) {
        String chave = "\"" + campo + "\":";
        int inicio = json.indexOf(chave) + chave.length();
        int fimChave = json.indexOf("}", inicio);
        int fimVirgula = json.indexOf(",", inicio);
        if (fimVirgula != -1 && fimVirgula < fimChave) {
            fimChave = fimVirgula;
        }
        return json.substring(inicio, fimChave);
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + mensagem + " (timestamp=" + timestamp + ")";
    }
}
