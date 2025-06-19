package model;

/* 
 * classe enum para tipo de registro
 * ENTRADA           início da jornada de trabalho
 * INICIO_INTERVALO  ida para o intervalo
 * FIM_INTERVALO     volta do intervalo
 * SAIDA             fim da jornada de trabalho
 * OUTRO             registro imprevisto
 */
public enum TipoRegistroPonto {
    ENTRADA,
    INICIO_INTERVALO,
    FIM_INTERVALO,
    SAIDA,
    OUTRO
}
