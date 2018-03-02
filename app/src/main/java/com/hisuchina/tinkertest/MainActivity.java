package com.hisuchina.tinkertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.hisuchina.tinkertest.manager.TinkerManager;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void loadPatch(View view){
		Toast.makeText(this, "load patch", Toast.LENGTH_SHORT).show();
		TinkerManager.loadPatch("/storage/emulated/0/tinker.apk","");
	}

	public void testBtn(View view){
		Intent intent = new Intent(this, TestActivity.class);
		startActivity(intent);
	}

	public void testBtn2(View view){
		Toast.makeText(this, "show test", Toast.LENGTH_SHORT).show();
	}
}
