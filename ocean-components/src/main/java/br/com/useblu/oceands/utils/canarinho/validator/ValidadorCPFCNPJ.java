package br.com.useblu.oceands.utils.canarinho.validator;

import android.text.Editable;

import br.com.useblu.oceands.utils.canarinho.validator.Validador;
import br.com.useblu.oceands.utils.canarinho.validator.ValidadorCNPJ;
import br.com.useblu.oceands.utils.canarinho.validator.ValidadorCPF;
import br.com.useblu.oceands.utils.canarinho.formatador.Formatador;

public final class ValidadorCPFCNPJ implements Validador {

    // No instance creation
    private ValidadorCPFCNPJ() {
    }

    public static ValidadorCPFCNPJ getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean ehValido(final String valor) {
        if (valor == null || (valor.length() != 11 && valor.length() != 14)) {
            return false;
        }

        if (ehCpf(valor)) {
            return br.com.useblu.oceands.utils.canarinho.validator.ValidadorCPF.getInstance().ehValido(valor);
        }

        return br.com.useblu.oceands.utils.canarinho.validator.ValidadorCNPJ.getInstance().ehValido(valor);
    }

    @Override
    public ResultadoParcial ehValido(final Editable valor, final ResultadoParcial resultadoParcial) {
        if (resultadoParcial == null || valor == null) {
            throw new IllegalArgumentException("Valores não podem ser nulos");
        }

        if (ehCpf(valor.toString())) {
            return ValidadorCPF.getInstance().ehValido(valor, resultadoParcial);
        }

        return ValidadorCNPJ.getInstance().ehValido(valor, resultadoParcial);
    }

    private boolean ehCpf(String valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }

        final String desformatado = Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(valor).replaceAll("");
        return desformatado.length() < 12;
    }

    private static class SingletonHolder {
        private static final ValidadorCPFCNPJ INSTANCE = new ValidadorCPFCNPJ();
    }
}
