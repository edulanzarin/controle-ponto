package repository;

import java.time.LocalDate;
import java.time.LocalTime;

import model.RegistroPonto;
import model.TipoRegistroPonto;

public class RegistroPontoCsvRepository {

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
