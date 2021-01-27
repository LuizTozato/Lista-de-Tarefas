package com.ugps.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ugps.listadetarefas.model.Tarefa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {
//DATA ACESS OBJECT

    private SQLiteDatabase escreve;
    private static SQLiteDatabase le;


    //CONSTRUTOR
    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    //=============================================
    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put( "nome", tarefa.getNomeTarefa() );

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS,null,cv);
            Log.e("INFO","Tarefa salva com sucesso: ");
        } catch (Exception e){
            Log.e("INFO","Erro ao salvar tarefa: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try {
            String[] args =  {tarefa.getId().toString()};

            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.e("INFO","Tarefa atualizada com sucesso: ");
        } catch (Exception e){
            Log.e("INFO","Erro ao atualizar tarefa: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {

            String[] args = {tarefa.getId().toString()};
            escreve.delete(DbHelper.TABELA_TAREFAS,"id=?",args);
            Log.i("INFO","Tarefa removida com sucesso: ");

        } catch (Exception e){

            Log.e("INFO","Erro ao remover tarefa: " + e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = le.rawQuery(sql,null);

        //SE FOR POSSIVEL IR PRA LINHA DE BAIXO, RETORNA TRUE, SENÃO, RETORNA FALSE
        while( c.moveToNext() ){

            Tarefa tarefa = new Tarefa();

            //AQUI ELE LÊ O ID DAQUELA LINHA EM QUE ELE ESTÁ E O NOME DA COLUNA
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);

        }

        return tarefas;
    }




}
