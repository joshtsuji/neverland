package com.centivyx.neverland;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.larswerkman.colorpicker.ColorPicker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class EquipmentFragment extends android.support.v4.app.Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_equipment, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button projector = (Button) findViewById(R.id.equipment_projector); 
		projector.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AsyncHttpClient client = new AsyncHttpClient();
				client.get(SettingsFragment.ip + "event?projector", new AsyncHttpResponseHandler() {
					@Override
					public void onFinish() {
						
					}
				});
			}
		});
	}


	public View findViewById(int id) {
		return getView().findViewById(id);
	}
}
