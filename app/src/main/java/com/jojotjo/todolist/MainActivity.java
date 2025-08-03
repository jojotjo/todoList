package com.jojotjo.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    AppDataBase appDataBase;
    ToDoListAdapter toDoListAdapter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recycler_view_main);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);


            }
        });

        toDoListAdapter = new ToDoListAdapter(this);
        appDataBase = AppDataBase.getsInstance(getApplicationContext());

        recyclerView.setAdapter(toDoListAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return 0;
//            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipDir) {
                final int position = viewHolder.getAbsoluteAdapterPosition();
                final List<Task> tasks = toDoListAdapter.getTasks();

                AppExecutor.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDataBase.taskDao().deleteTask(tasks.get(position));
                    }
                });

            }
        }).attachToRecyclerView(recyclerView);

        getTasks("onCreate");
    }

    private void getTasks(final String s) {
        MainScreenViewModel viewModel = new ViewModelProvider(this).get(MainScreenViewModel.class);
        viewModel.getTaskList().observe(MainActivity.this,new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task>tasks){
                toDoListAdapter.setTasks(tasks);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
