package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.RegistroPonto;
import model.TipoRegistroPonto;

/*
 * classe de serviço responsável por agrupar as funções responsáveis pela formatação de dados.
 */
public class FormatUtil {

    /*
     * variáveis final para formatar a data e a hora do registroPonto para o formato
     * utilizado no Brasil
     */
    private static final DateTimeFormatter DATA_FORMATADA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter HORA_FORMATADA = DateTimeFormatter.ofPattern("HH:mm");

    /*
     * método static para converter um objeto RegistroPonto em uma String
     * formatada para CSV. A String resultante terá os campos separados por ";"
     * e conterá os seguintes campos: id, tipo, data, hora e observação
     */
    public static String registroPontoParaCsv(RegistroPonto registroPonto) {
        String idRegistroPonto = registroPonto.getId();
        String tipoRegistroPonto = registroPonto.getTipoRegistro().toString();
        String dataRegistroPonto = formatarData(registroPonto.getData());
        String horaRegistroPonto = formatarHora(registroPonto.getHora());
        String observacaoRegistroPonto = registroPonto.getObservacao();
        if (observacaoRegistroPonto == null) {
            observacaoRegistroPonto = "";
        }

        return String.format("%s;%s;%s;%s;%s", idRegistroPonto, tipoRegistroPonto, dataRegistroPonto, horaRegistroPonto,
                observacaoRegistroPonto);
    }

    /*
     * método static para converter uma linha CSV em um objeto RegistroPonto
     * a linha CSV deve ter os campos separados por ";". O método espera que a linha
     * CSV tenha pelo menos 4 campos: id, tipo, data e hora.
     */
    public static RegistroPonto csvParaRegistroPonto(String linhaCsv) {
        String[] campos = linhaCsv.split(";");
        String idRegistroPonto = campos[0];
        TipoRegistroPonto tipoRegistroPonto = TipoRegistroPonto.valueOf(campos[1]);
        LocalDate dataRegistroPonto = LocalDate.parse(campos[2], DATA_FORMATADA);
        LocalTime horaRegistroPonto = LocalTime.parse(campos[3], HORA_FORMATADA);
        String observacaoRegistroPonto = campos.length > 4 ? campos[4] : null;

        return new RegistroPonto(idRegistroPonto, tipoRegistroPonto, dataRegistroPonto, horaRegistroPonto,
                observacaoRegistroPonto);
    }

    /*
     * método static para formatar a data para o estilo dd/MM/yyyy
     */
    public static String formatarData(LocalDate dataRegistroPonto) {
        String dataRegistroPontoFormatada = dataRegistroPonto.format(DATA_FORMATADA);
        return dataRegistroPontoFormatada;
    }

    /*
     * método static para formatar a hora para o estilo HH:mm
     */
    public static String formatarHora(LocalTime horaRegistroPonto) {
        String horaRegistroPontoFormatada = horaRegistroPonto.format(HORA_FORMATADA);
        return horaRegistroPontoFormatada;
    }
}
