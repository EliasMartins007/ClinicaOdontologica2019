package com.elias.clinicaodontologica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.elias.clinicaodontologica.uteis.LinhaConsultaAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AgendaDia extends AppCompatActivity {

    private ListView listViewOpcoesDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdia);

        //recuperar lista
        listViewOpcoesDia = (ListView) findViewById(R.id.listDia);

        //inicio click na lista
        listViewOpcoesDia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //seu codigo
                Intent intent = new Intent(getApplicationContext(), Sobre.class);
                startActivity(intent);
            }
        });
        //fim click 27/09/2019 elias

        List<AgendamentosDiaHora> agendamentosDiaHora = diasSemana();

 //       LinhaConsultaAdapter adapteragendamento = new AgendamentosDiaHora();//(agendamentosDiaHora, null);//duvida quem é act 27/09/2019 elias
     //    listViewOpcoesDia.setAdapter(new LinhaConsultaAdapter(this , agendamentosDia));
//           listViewOpcoesDia.setAdapter();
 //       listViewOpcoesDia.setAdapter(adapteragendamento);
    }

    private List<AgendamentosDiaHora> diasSemana() {

        return new ArrayList<>(Arrays.asList(

                new AgendamentosDiaHora("SEG", "30/09/2019", "atende "),
                new AgendamentosDiaHora("TER", "01/10/2019", "atende "),
                new AgendamentosDiaHora("QUA", "02/10/2019", "não atende"),
                new AgendamentosDiaHora("QUI", "03/10/2019", "atende "),
                new AgendamentosDiaHora("SEX", "04/10/2019", "atende "),
                new AgendamentosDiaHora("SAB", "05/10/2019", "não atende")));


    }

}
