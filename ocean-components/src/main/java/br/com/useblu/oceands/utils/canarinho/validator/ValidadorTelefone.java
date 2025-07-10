package br.com.useblu.oceands.utils.canarinho.validator;

import android.text.Editable;

import br.com.useblu.oceands.utils.canarinho.validator.Validador;
import br.com.useblu.oceands.utils.canarinho.formatador.Formatador;

public final class ValidadorTelefone implements Validador {

    // No instance creation
    private ValidadorTelefone() {
    }

    public static ValidadorTelefone getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean ehValido(String valor) {
        if (valor == null || valor.length() < 10) {
            return false;
        }

        final String desformatado = Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(valor).replaceAll("");

        return desformatado.length() == 10 || desformatado.length() == 11;
    }

    @Override
    public ResultadoParcial ehValido(Editable valor, ResultadoParcial resultadoParcial) {
        if (resultadoParcial == null || valor == null) {
            throw new IllegalArgumentException("Valores não podem ser nulos");
        }

        final String desformatado = Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(valor).replaceAll("");

        if (!ehValido(desformatado)) {
            return resultadoParcial
                    .parcialmenteValido(desformatado.length() < 11)
                    .mensagem("Telefone inválido")
                    .totalmenteValido(false);
        }

        return resultadoParcial
                .parcialmenteValido(true)
                .totalmenteValido(true);
    }

    private static class SingletonHolder {
        private static final ValidadorTelefone INSTANCE = new ValidadorTelefone();
    }
}
