package com.example.customerlibrary.util;

import android.net.Uri;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.customerlibrary.AdminBeans;
import com.example.customerlibrary.R;
import com.example.customerlibrary.callback.FireBase_C;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.orange.jzchi.jzframework.JzActivity;


public class Util_Firebase_Upload {

    public static void upload(Uri file, final FireBase_C caller){
        JzActivity.Companion.getControlInstance().showDiaLog(R.layout.progress, false,false,"progress");
        final StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        long time=System.currentTimeMillis();
        final StorageReference riversRef = mStorageRef.child("images/"+ AdminBeans.Companion.getAdmin() +"/"+time+"");
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                caller.UpdateSuccess(uri);
                                JzActivity.Companion.getControlInstance().closeDiaLog();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                caller.UpdateFalse();
                                JzActivity.Companion.getControlInstance().closeDiaLog();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        JzActivity.Companion.getControlInstance().closeDiaLog();
                    }
                });
    }
    public static void AddImage_topo(Uri file, final EditText me){
        JzActivity.Companion.getControlInstance().showDiaLog(R.layout.progress, false,false,"progress");
        final StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        long time=System.currentTimeMillis();
        final StorageReference riversRef = mStorageRef.child("images/"+ AdminBeans.Companion.getAdmin()+"/"+time+"");
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                me.setText(me.getText().toString()+"\n"+uri.toString());
                                JzActivity.Companion.getControlInstance().closeDiaLog();
                                JzActivity.Companion.getControlInstance().toast("上傳成功");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                JzActivity.Companion.getControlInstance().closeDiaLog();
                                JzActivity.Companion.getControlInstance().toast("上傳失敗");
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        JzActivity.Companion.getControlInstance().closeDiaLog();
                        JzActivity.Companion.getControlInstance().toast("上傳失敗");
                    }
                });
    }
}
