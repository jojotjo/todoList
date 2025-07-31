package com.jojotjo.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import androidx.compose.ui.tooling.data.ContextCache;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.TaskViewHolder> {
    private static  final String DATE_FORMAT = "dd/MM/yyy";

    private List<Task> mTaskEntries;
    private Context mContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    public ToDoListAdapter(Context context){
        mContext = context;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_row_item,parent,false);
        return new TaskViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder,int position){
        Task taskEntry = mTaskEntries.get(position);
        String description = taskEntry.getDescription();
        int priority = taskEntry.getPriority();
        final int id = taskEntry.getId();
        String updateAt = dateFormat.format(taskEntry.getUpdateAt());
        holder.taskDescriptionView.setText(description);
        holder.updatedAtView.setText(updateAt);
        String priorityString = "" + priority;
        holder.priorityView.setText(priorityString);

        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext,EditorActivity.class);
                intent.putExtra("id",id);
                mContext.startActivity(intent);

            }
        });
    }

    private  int getPriorityColor(int priority){
        int priorityColor = 0;
        switch (priority){
            case 1:
                priorityColor = ContextCompat.getColor(mContext,R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext,R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext,R.color.materialYellow);
                break;
            default:
                break;

        }
        return priorityColor;
    }

    @Override
    public int getItemCount(){
        if(mTaskEntries == null){
            return 0;
        }
        return mTaskEntries.size();
    }

    public void setTasks(List<Task> taskEntries){
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }

    public List<Task> getTasks(){
        return mTaskEntries;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView taskDescriptionView;
        TextView updatedAtView;
        TextView priorityView;

        View view;

        public TaskViewHolder(View itemView){
            super(itemView);

            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            view = itemView;
        }
    }

}
