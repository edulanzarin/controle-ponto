package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import model.RegistroPonto;

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
     * transforma o objeto RegistroPonto em String com os dados separados por ";"
     * para armazenar em arquivo CSV
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
