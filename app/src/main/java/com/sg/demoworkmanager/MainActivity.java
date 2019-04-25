package com.sg.demoworkmanager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Constraints constraint=new Constraints.Builder().setRequiresCharging(true).build();
        Data data=new Data.Builder().putInt("int",10750).build();
        final OneTimeWorkRequest oneTimeWorkRequest=new OneTimeWorkRequest.Builder(DemoWorker.class)
                .setInputData(data).setConstraints(constraint).build();
        final TextView textView=findViewById(R.id.textView);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueue(oneTimeWorkRequest);
            }
        });
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if(workInfo!=null)
                {
                    textView.setText(workInfo.getState().name());
                }
                if(workInfo.getState().isFinished())
                {
                    Toast.makeText(MainActivity.this,workInfo.getOutputData().getString("string"),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
