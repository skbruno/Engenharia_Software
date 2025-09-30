package com.contatamento.contatos_api.Models;

public class FabricaAgenda {
    public static final int AGENDAMAP = 0;
    public static final int AGENDALIST = 1;

    private static FabricaAgenda fabricaAgenda = new FabricaAgenda();

    private FabricaAgenda() {}

    public static FabricaAgenda getInstancia() {
        return fabricaAgenda;
    }

    public Object criaAgenda(int tipo) {
        if (tipo == AGENDAMAP) {
            return new AgendaMap();
        } else if (tipo == AGENDALIST) {
            return new AgendaList();
        }
        return null;
    }
}
