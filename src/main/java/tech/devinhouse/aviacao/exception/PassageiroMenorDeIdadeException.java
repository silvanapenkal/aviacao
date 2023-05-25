package tech.devinhouse.aviacao.exception;

public class PassageiroMenorDeIdadeException extends RuntimeException{

    public PassageiroMenorDeIdadeException(Long cpf) {
        super("O passageiro de cpf " + cpf + "é menor de idade, por isso não pode sentar nos assentos próximos a saída de emergência");
    }

}
