package tech.devinhouse.aviacao.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String nomeRecurso, Long id) {
        super(nomeRecurso + " com identificador " + id + " não encontrado!");
    }

    public RegistroNaoEncontradoException(String nomeRecurso, String id) {
        super(nomeRecurso + " com identificador " + id + " não encontrado!");
    }

}
