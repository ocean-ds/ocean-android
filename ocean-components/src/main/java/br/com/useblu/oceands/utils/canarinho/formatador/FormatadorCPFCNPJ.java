package br.com.useblu.oceands.utils.canarinho.formatador;

import br.com.useblu.oceands.utils.canarinho.formatador.Formatador;

/**
 * Formatador para CPF e CNPJ no mesmo campo. Formata como CPF até 11 dígitos numéricos. Depois
 * formata como CNPJ.
 */
public final class FormatadorCPFCNPJ implements br.com.useblu.oceands.utils.canarinho.formatador.Formatador {

    private FormatadorCPFCNPJ() {
    }

    static FormatadorCPFCNPJ getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String formata(final String value) {
        if (ehCpf(value)) {
            return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CPF.formata(value);
        }

        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CNPJ.formata(value);
    }

    @Override
    public String desformata(final String value) {
        if (ehCpf(value)) {
            return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CPF.desformata(value);
        }

        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CNPJ.desformata(value);
    }

    @Override
    public boolean estaFormatado(final String value) {
        if (ehCpf(value)) {
            return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CPF.estaFormatado(value);
        }

        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CNPJ.estaFormatado(value);
    }

    @Override
    public boolean podeSerFormatado(final String value) {
        if (value == null) {
            return false;
        }

        if (ehCpf(value)) {
            return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CPF.podeSerFormatado(value);
        }

        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CNPJ.podeSerFormatado(value);
    }

    private boolean ehCpf(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo");
        }

        final String desformatado = Formatador.Padroes.PADRAO_SOMENTE_NUMEROS.matcher(value)
                .replaceAll("");
        return desformatado.length() < 12;
    }

    private static class SingletonHolder {
        private static final FormatadorCPFCNPJ INSTANCE = new FormatadorCPFCNPJ();
    }
}
