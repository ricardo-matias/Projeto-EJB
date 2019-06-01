package com.FazTudo2.ejb.Entidade;

public enum Nivel {
    INICIANTE(1), INTERMEDIARIO(2), AVANCADO(3), VIP(4), MESTRE(5);
    
    public int valorNivel;
    Nivel(int valor) {
        valorNivel = valor;
    }
    
    public int getValorNivel() {
        return valorNivel;
    }
}
