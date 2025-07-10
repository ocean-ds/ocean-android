package br.com.useblu.oceands.utils.canarinho.formatador;

import br.com.useblu.oceands.utils.canarinho.formatador.Formatador;

/**
 * Formatador para CEP. Segue o padrão 99999-999.
 */
public final class FormatadorCEP implements br.com.useblu.oceands.utils.canarinho.formatador.Formatador {

    private FormatadorCEP() {
    }

    static FormatadorCEP getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String formata(final String value) {
        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CEP.formata(value);
    }

    @Override
    public String desformata(final String value) {
        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CEP.desformata(value);
    }

    @Override
    public boolean estaFormatado(final String value) {
        return br.com.useblu.oceands.utils.canarinho.formatador.Formatador.CEP.estaFormatado(value);
    }

    @Override
    public boolean podeSerFormatado(final String value) {
        if (value == null) {
            return false;
        }

        return Formatador.CEP.podeSerFormatado(value);
    }

    private static class SingletonHolder {
        private static final FormatadorCEP INSTANCE = new FormatadorCEP();
    }
}
