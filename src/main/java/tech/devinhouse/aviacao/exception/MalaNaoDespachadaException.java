package tech.devinhouse.aviacao.exception;

public class MalaNaoDespachadaException extends RuntimeException {

    public MalaNaoDespachadaException(String assento) {
        super("Para selecionar o assento " + assento + " a mala precisará ser despachada");
    }


}
