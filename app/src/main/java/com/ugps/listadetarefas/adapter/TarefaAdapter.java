package com.ugps.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ugps.listadetarefas.R;
import com.ugps.listadetarefas.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> listaTarefas;

    //===================== CONSTRUTORES ==============================

    public TarefaAdapter( List<Tarefa> lista ) {

        this.listaTarefas = lista;

    }

    //=================================================================
    //============ IMPLEMENTANDO MÉTODOS DA SUPERCLASSE ===============

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista =  LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.lista_tarefa_adapter,parent,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    //ELE QUE PEGA A LISTA DE TAREFAS E, BASEADO NA position, RETORNA A TAREFA
        Tarefa tarefa = listaTarefas.get(position);
        holder.tarefa.setText( tarefa.getNomeTarefa() );

    }

    @Override
    public int getItemCount() {
        return this.listaTarefas.size();
    }


    //=================================================================
    //=================== ESSA É UMA CLASSE INTERNA ===================
    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tarefa;

        //CONSTRUTOR
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textTarefa);

        }
    }
    //=================================================================



}
