package util;

import java.time.LocalDate;
import java.time.LocalTime;

import model.RegistroPonto;
import model.TipoRegistroPonto;

/*
 * classe responsável por agrupar funções que fazem a formatação do objeto RegistroPonto para outros tipos de arquivo
 * como CSV, JSON, etc.
 */
public class FormatUtil {

    /*
     * transforma o objeto RegistroPonto em String com os dados separados por ";"
     * para armazenar em arquivo CSV
     */
    public String registroPontoParaCsv(RegistroPonto registroPonto) {
        String idRegistroPonto = registroPonto.getId();
        TipoRegistroPonto tipoRegistroPonto = registroPonto.getTipoRegistro();
        LocalDate dataRegistroPonto = registroPonto.getData();
        LocalTime horaRegistroPonto = registroPonto.getHora();
        String observacaoRegistroPonto = registroPonto.getObservacao();

        return String.format("%s;%s;%s;%s;%s", idRegistroPonto, tipoRegistroPonto, dataRegistroPonto, horaRegistroPonto,
                observacaoRegistroPonto);
    }
}
