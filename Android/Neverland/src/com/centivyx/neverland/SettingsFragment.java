package com.centivyx.neverland;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.larswerkman.colorpicker.ColorPicker;

public class SettingsFragment extends android.support.v4.app.Fragment{
	ColorPicker picker;
	ColorPicker picker2;
	
	public static String ip = "http://10.0.0.58/";
	
	boolean requestPending = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_settings, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button save = (Button) findViewById(R.id.setting_ip_save); 
		final EditText ip = (EditText) findViewById(R.id.setting_ip);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SettingsFragment.ip = "http://" + ip.getText().toString() + "/";
			}
		});
	}


	public View findViewById(int id) {
		return getView().findViewById(id);
	}
}
