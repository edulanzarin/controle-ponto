package model;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * objeto Registro com a estrutura necessária para armazenar o ponto no arquivo .csv
 * 
 * id               id exclusivo gerado automaticamente
 * tipoRegistro     tipo de registro: ENTRADA, INICIO_INTERVALO, FIM_INTERVALO, SAIDA, OUTRO
 * data             data do registro
 * hora             hora do registro
 * observacao       alguma observacao sobre esse registro
 */
public class RegistroPonto {

    private String id;
    private TipoRegistroPonto tipoRegistro;
    private LocalDate data;
    private LocalTime hora;
    private String observacao;

    /*
     * não recebe id, pois está sendo gerado aleatoriamente com UUID.randomUUID()
     * também não recebe observacao, pois é uma informação opcional
     */
    public RegistroPonto(String id, TipoRegistroPonto tipoRegistro, LocalDate data, LocalTime hora, String observacao) {
        this.id = id;
        this.tipoRegistro = tipoRegistro;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao != null ? observacao : "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoRegistroPonto getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistroPonto tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Registro [id=" + id + ", tipoRegistro=" + tipoRegistro + ", data=" + data + ", hora=" + hora
                + ", observacao=" + observacao + "]";
    }
}
