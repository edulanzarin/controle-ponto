import model.RegistroPonto;
import model.TipoRegistroPonto;
import repository.RegistroPontoCsvRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class App {

    public static void main(String[] args) throws Exception {
        // Criando um RegistroPonto para ENTRADA com data e hora atuais
        RegistroPonto registroPonto = new RegistroPonto(
                TipoRegistroPonto.ENTRADA,
                LocalDate.now(),
                LocalTime.now());

        RegistroPontoCsvRepository RegistroPontoCsvRepository = new repository.RegistroPontoCsvRepository();

        RegistroPontoCsvRepository.adicionarRegistroPonto(registroPonto);
    }
}
