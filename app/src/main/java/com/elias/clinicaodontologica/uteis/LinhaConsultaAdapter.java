package com.elias.clinicaodontologica.uteis;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elias.clinicaodontologica.AgendaDia;//teste elias 27/09/2019
import com.elias.clinicaodontologica.AgendamentosDiaHora;
import com.elias.clinicaodontologica.R;

import java.util.List;


//classe criada por elias para teste 11/09/2019
public class LinhaConsultaAdapter extends BaseAdapter {

    private final Activity actClasse;
    private final List<AgendamentosDiaHora> agendamentosClasse;

    // private AgendaDia agendaDia;//teste elias 27/09/2019


    public LinhaConsultaAdapter(List<AgendamentosDiaHora> agendamentos, Activity act) {

        this.actClasse = act;
        this.agendamentosClasse = agendamentos;
    }


//    //teste meu construtor//teste elias 27/09/2019
//    public LinhaConsultaAdapter(List<AgendamentosDiaHora> agendamentos, Activity act, AgendaDia teste) {
//        this.actClasse = act;
//        this.agendamentosClasse = agendamentos;
//        this.agendaDia = teste;
//    }


    @Override
    public int getCount() {
        return agendamentosClasse.size();
    }

    @Override
    public Object getItem(int position) {
        return agendamentosClasse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = actClasse.getLayoutInflater().inflate(R.layout.list_itemdia, parent, false);

        AgendamentosDiaHora agendamentosDiaHora = agendamentosClasse.get(position);

        TextView nome = (TextView) view.findViewById(R.id.txtdia);
        TextView descricao = (TextView) view.findViewById(R.id.txtdata);
        TextView atende = (TextView) view.findViewById(R.id.txtatende);

        nome.setText(agendamentosDiaHora.getNome());
        descricao.setText(agendamentosDiaHora.getDescricao());
        atende.setText(agendamentosDiaHora.getAtendimento());

        return view;
    }
}
