package com.example.tranvanmanh.sqlitedatabase;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  Database database;
    private ListView listView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_task);

        taskList = new ArrayList<>();
        adapter = new TaskAdapter(this, R.layout.task_todolist, taskList);
        listView.setAdapter(adapter);

        // init Database
        database = new Database(this, "todolist.sqlite", null, 1);

        // Create data
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec( Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(300))");

        //Insert data
       // database.QueryData("INSERT INTO CongViec VALUES(null,'do Java exercise')");

        //Select data
       GetTask();

    }

    private void GetTask(){
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");
        taskList.clear();
        while (dataCongViec.moveToNext()){
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            taskList.add(new Task(id, ten));
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menuAdd)
        {
            AddTask();
        }
        return super.onOptionsItemSelected(item);
    }

    private  void AddTask(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diaglog_add);
        dialog.show();

        final EditText edtAdd = (EditText) dialog.findViewById(R.id.editAdd);
        Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        Button btnCancel = (Button) dialog.findViewById(R.id.btncancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTask = edtAdd.getText().toString();
                if(nameTask.equals("")){
                    Toast.makeText(MainActivity.this, "please type your task", Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO CongViec VALUES(null,'"+nameTask+"')");
                    dialog.dismiss();
                    GetTask();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void DialogEdit(final String nameEdit, final int id){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.show();

        final EditText edtEdit = (EditText) dialog.findViewById(R.id.editAddEdit);
        edtEdit.setText(nameEdit);
        Button btnEdit = (Button) dialog.findViewById(R.id.btnAddEdit);
        Button btnCancelEdit = (Button) dialog.findViewById(R.id.btncancelEdit);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.QueryData("UPDATE CongViec SET TenCV = '"+edtEdit.getText().toString().trim()+"' WHERE Id ='"+id+"'");
                dialog.dismiss();
                GetTask();
            }
        });

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    public  void DialogDelete(String nameTask, final int id){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Delete this task?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM CongViec WHERE Id = '"+id+"'");
                GetTask();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
