import model.RegistroPonto;
import model.TipoRegistroPonto;
import repository.RegistroPontoCsvRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class App {

    public static void main(String[] args) throws Exception {

        String id1 = UUID.randomUUID().toString();
        // Criando um RegistroPonto para ENTRADA com data e hora atuais
        RegistroPonto registroPonto = new RegistroPonto(
                id1,
                TipoRegistroPonto.ENTRADA,
                LocalDate.now(),
                LocalTime.now());

        RegistroPontoCsvRepository registroPontoCsvRepository = new RegistroPontoCsvRepository();

        registroPontoCsvRepository.adicionarRegistroPonto(registroPonto);

        String registroPontoId = registroPonto.getId().toString();

        registroPontoCsvRepository.editarRegistroPonto(registroPontoId,
                new RegistroPonto(id1, TipoRegistroPonto.SAIDA, LocalDate.now(), LocalTime.now()));
    }
}
