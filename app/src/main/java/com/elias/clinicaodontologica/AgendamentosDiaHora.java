package com.elias.clinicaodontologica;


import android.view.LayoutInflater;

public class AgendamentosDiaHora {// no exemplo extende um baseadapter ??? 05/09/2019

    private String nome;


    private String descricao;
    private String atendimento;

    //criando obj layoutInflater para fazer link a nossa view
    //   private  static LayoutInflater layoutInflater = null;

    //criando um obj da classe que possui a lista


    //seguindo outro exemplo para lista dia data atende inicial
    public AgendamentosDiaHora(String nome, String descricao, String atendimento) {
        this.nome = nome;
        this.descricao = descricao;
        this.atendimento = atendimento;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

}
