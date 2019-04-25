package com.sg.demoworkmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {
    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data=getInputData();
        int count=data.getInt("int",10750);
        for(int i=0;i<count

                ;i++)
        {
            Log.d("Worker TAG",""+i);
        }
        Data data1=new Data.Builder().putString("string","Hello! Task Completed").build();
        return Result.success(data1);
    }
}
