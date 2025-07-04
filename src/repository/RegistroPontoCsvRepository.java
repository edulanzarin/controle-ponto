package repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.RegistroPonto;
import model.TipoRegistroPonto;
import util.CsvUtil;
import util.FormatUtil;

/*
 * classe para agrupar as funções do repositório CSV
 */
public class RegistroPontoCsvRepository {

    protected String caminhoCsv;

    /*
     * obtém o sistema operacional do usuário e criar um caminho para o arquivo CSV
     * baseado nisso
     */
    private String obterCaminhoCsv() {
        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        boolean ehLinux = sistemaOperacional.contains("linux");

        String caminhoDiretorio = ehLinux
                ? System.getProperty("user.home") + "/applications/controle-ponto"
                : "C:\\applications\\controle-ponto";

        return caminhoDiretorio + (ehLinux ? "/registros.csv" : "\\registros.csv");
    }

    /*
     * verifica se o arquivo CSV já existe no computador e se não existir cria ele a
     * partir do caminho obtido em obterCaminhoCsv()
     */
    public void criarCsv() throws IOException {
        /*
         * chama o método obterCaminhoCsv() para obter o caminho completo do arquivo CSV
         * no linux: /home/usuario/applications/controle-ponto/registros.csv
         * no windows: C:\applications\controle-ponto\registros.csv
         */
        this.caminhoCsv = obterCaminhoCsv();

        /*
         * cria o o objeto File arquivo representando o arquivo CSV que será
         * verificado/criado
         */
        File arquivo = new File(caminhoCsv);

        /*
         * obtém o diretório pai do arquivo verificado com a função getParentFile()
         * é necessário para garantir que o diretório exista antes de criar o arquivo
         * se caminhoCsv = /home/usuario/applications/controle-ponto/registros.csv
         * então diretorio = /home/usuario/applications/controle-ponto
         */
        File diretorio = arquivo.getParentFile();

        /*
         * verifica se o diretório do arquivo CSV já existe
         * se não existir, tenta criar com mkdirs()
         * retorna uma IOException caso occora algum erro.
         */
        if (!diretorio.exists()) {
            boolean criado = diretorio.mkdirs();
            if (!criado) {
                throw new IOException("Erro ao criar diretório: " + diretorio.getAbsolutePath());
            }
        }

        /*
         * se o diretório do arquivo CSV for criado com sucesso, então tenta criar o
         * arquivo com createNewFile()
         * retorna uma IOException caso occora algum erro.
         */
        if (!arquivo.exists()) {
            boolean criado = arquivo.createNewFile();
            if (!criado) {
                throw new IOException("Não foi possível criar o arquivo CSV.");
            }
        }
    }

    /*
     * método responsável por listar todos os registros de ponto
     * armazenados no arquivo CSV
     * retorna um array de RegistroPonto
     */
    public List<RegistroPonto> listarRegistrosPonto() {
        List<String[]> linhas = CsvUtil.lerLinhas(caminhoCsv);
        List<RegistroPonto> registros = new ArrayList<>();

        for (String[] linha : linhas) {
            if (linha.length > 0) {
                registros.add(FormatUtil.csvParaRegistroPonto(String.join(";", linha)));
            }
        }
        return registros;
    }

    /*
     * adiciona um registroPonto no arquivo CSV
     */
    public void adicionarRegistroPonto(RegistroPonto registroPonto) {
        /*
         * verifica se o caminhoCsv não foi declarado ou se está vazio, caso seja "null"
         * chama obterCaminhoCsv() para definir o caminho do arquivo CSV
         */
        if (caminhoCsv == null || caminhoCsv.isEmpty()) {
            this.caminhoCsv = obterCaminhoCsv();
        }

        /*
         * usa o método registroPontoParCsv() da classe utilitária FormatUtil para
         * converter o objeto registroPonto em uma String formatada para CSV
         */
        String linhaCsv = FormatUtil.registroPontoParaCsv(registroPonto);

        /*
         * cria uma instância da classe utilitária CsvUtil e chama o método
         * escreverLinha() e adiciona o registroPonto
         */
        CsvUtil.escreverLinha(caminhoCsv, linhaCsv);
    }

    /*
     * método responsável por fazer a exclusão de um registro de ponto específico
     * através do id
     */
    public void removerRegistroPonto(String idRegistroPonto) {
        List<String[]> linhas = CsvUtil.lerLinhas(caminhoCsv);
        List<String> novasLinhas = new ArrayList<>();

        for (String[] linha : linhas) {
            if (!linha[0].equals(idRegistroPonto)) {
                novasLinhas.add(String.join(";", linha));
            }
        }

        CsvUtil.sobrescreverArquivo(caminhoCsv, novasLinhas);
    }

    /*
     * método responsável por fazer a edição de um registro de ponto escpecífico
     * através do id
     */
    public void editarRegistroPonto(String idRegistroPonto, RegistroPonto registroPonto) {
        List<String[]> linhas = CsvUtil.lerLinhas(caminhoCsv);
        List<String> novasLinhas = new ArrayList<>();

        for (String[] linha : linhas) {
            if (linha[0].equals(idRegistroPonto)) {
                /*
                 * se encontrar o registro com o idRegistroPonto, atualiza os dados
                 * do registroPonto com os novos dados fornecidos
                 * e converte novamente para o formato CSV
                 */
                String linhaAtualizada = FormatUtil.registroPontoParaCsv(registroPonto);
                novasLinhas.add(linhaAtualizada);
            } else {
                /*
                 * caso não encontre o registro com o idRegistroPonto,
                 * adiciona a linha original sem alterações
                 */
                novasLinhas.add(String.join(";", linha));
            }
        }
    }

    /*
     * método responsável por buscar um registro de ponto específico
     * através do id
     */
    public RegistroPonto buscarRegistroPontoPorId(int id) {
        List<String[]> linhas = CsvUtil.lerLinhas(caminhoCsv);

        for (String[] linha : linhas) {
            if (linha.length > 0 && linha[0].equals(String.valueOf(id))) {
                return FormatUtil.csvParaRegistroPonto(String.join(";", linha));
            }
        }

        return null; /* retorna null se não encontrar */
    }

    /*
     * função para obter todos os registros de ponto entre duas datas
     */
    private List<RegistroPonto> filtrarPorData(List<RegistroPonto> registros, LocalDate inicio, LocalDate fim) {

        /*
         * se não for informado data inicio e fim, ignora esse filtro
         */
        if (inicio == null || fim == null)
            return registros;

        List<RegistroPonto> filtrados = new ArrayList<>();
        for (RegistroPonto registro : registros) {
            if ((registro.getData().isEqual(inicio) || registro.getData().isAfter(inicio)) &&
                    (registro.getData().isEqual(fim) || registro.getData().isBefore(fim))) {
                filtrados.add(registro);
            }
        }

        return filtrados;
    }

    /*
     * função para obter todos os registros de ponto entre duas horas
     */
    private List<RegistroPonto> filtrarPorHora(List<RegistroPonto> registros, LocalTime inicio, LocalTime fim) {

        /*
         * se não for informado hora inicio e fim, ignora esse filtro
         */
        if (inicio == null || fim == null)
            return registros;

        List<RegistroPonto> filtrados = new ArrayList<>();
        for (RegistroPonto registro : registros) {
            if ((registro.getHora().equals(inicio) || registro.getHora().isAfter(inicio)) &&
                    (registro.getHora().equals(fim) || registro.getHora().isBefore(fim))) {
                filtrados.add(registro);
            }
        }

        return filtrados;
    }

    /*
     * função para obter todos os registros de ponto de um tipo específico
     */
    private List<RegistroPonto> filtrarPorTipo(List<RegistroPonto> registros, TipoRegistroPonto tipo) {

        /*
         * se não for informado tipo de registro de ponto, ignora esse filtro
         */
        if (tipo == null)
            return registros;

        List<RegistroPonto> filtrados = new ArrayList<>();
        for (RegistroPonto registro : registros) {
            if (registro.getTipoRegistro().equals(tipo)) {
                filtrados.add(registro);
            }
        }

        return filtrados;
    }

    /*
     * função geral para aplicar todos os filtros combinados
     */
    public RegistroPonto[] buscarRegistrosPontoPorFiltros(LocalDate dataInicio, LocalDate dataFim,
            LocalTime horaInicio, LocalTime horaFim,
            TipoRegistroPonto tipo) {
        List<String[]> linhasCsv = CsvUtil.lerLinhas(caminhoCsv);
        List<RegistroPonto> registros = new ArrayList<>();

        for (String[] linha : linhasCsv) {
            if (linha.length > 0) {
                registros.add(FormatUtil.csvParaRegistroPonto(String.join(";", linha)));
            }
        }

        /*
         * cada filtro recebe List<RegistroPonto> retornado do filtro anterior e aplica
         * o novo filtro
         */
        registros = filtrarPorData(registros, dataInicio, dataFim);
        registros = filtrarPorHora(registros, horaInicio, horaFim);
        registros = filtrarPorTipo(registros, tipo);

        return registros.toArray(new RegistroPonto[0]);
    }
}