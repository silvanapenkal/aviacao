package tech.devinhouse.aviacao.exception;

public class RegistroEncontradoException extends RuntimeException{

    public RegistroEncontradoException(Long cpf) {
        super("Checkin realizado para o cpf: "+cpf);
    }

    public RegistroEncontradoException(String assento) {
        super("O assento " + assento + " já está ocupado");
    }

}
