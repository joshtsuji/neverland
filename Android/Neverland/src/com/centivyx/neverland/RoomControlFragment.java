package com.centivyx.neverland;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.larswerkman.colorpicker.ColorPicker;
import com.larswerkman.colorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.colorpicker.OpacityBar;
import com.larswerkman.colorpicker.SaturationBar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RoomControlFragment extends android.support.v4.app.Fragment implements OnColorChangedListener {
	ColorPicker picker;
	ColorPicker picker2;
	
	boolean requestPending = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_roomcontrol, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		picker = (ColorPicker) findViewById(R.id.picker1);
		OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar1);
		SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar1);

		picker.addOpacityBar(opacityBar);
		picker.addSaturationBar(saturationBar);
		picker.setOldCenterColor(picker.getColor());
		picker.setOnColorChangedListener(this);
		
		picker2 = (ColorPicker) findViewById(R.id.picker2);
		OpacityBar opacityBar2 = (OpacityBar) findViewById(R.id.opacitybar2);
		SaturationBar saturationBar2 = (SaturationBar) findViewById(R.id.saturationbar2);

		picker2.addOpacityBar(opacityBar2);
		picker2.addSaturationBar(saturationBar2);
		picker2.setOldCenterColor(picker2.getColor());
		picker2.setOnColorChangedListener(this);
		
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onColorChanged(int color) {
		picker.setOldCenterColor(picker.getColor());
		picker2.setOldCenterColor(picker2.getColor());
		refreshBackgroundColors();
		
		if (!requestPending) {
			requestPending = true;
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(SettingsFragment.ip + "color?color&" + colorToString(Color.red(picker.getColor()) * getAlpha(picker.getColor())) + "&" + colorToString(Color.green(picker.getColor()) * getAlpha(picker.getColor())) + 
					"&" + colorToString(Color.blue(picker.getColor()) * getAlpha(picker.getColor())) + "&" + colorToString(Color.red(picker2.getColor()) * getAlpha(picker2.getColor())) + 
					"&" + colorToString(Color.green(picker2.getColor()) * getAlpha(picker2.getColor())) + "&" + colorToString(Color.blue(picker2.getColor()) * getAlpha(picker2.getColor())) + "", new AsyncHttpResponseHandler() {
				@Override
				public void onFinish() {
					requestPending = false;
				}
			});
		}
	}
	
	protected double getAlpha(int color) {
		return Color.alpha(color) / 255.0; 
	}
	
	protected String colorToString(double color) {
		color = color > 255 ? 255 : color < 0 ? 0 : color;
		String c = String.valueOf((int)color);
		if (c.length() == 0) return "000";
		if (c.length() == 1) return "00" + c;
		if (c.length() == 2) return "0" + c;
		else return c;
	}
	
	public View findViewById(int id) {
		return getView().findViewById(id);
	}
	
	protected void refreshBackgroundColors() {
		int leftColor = Color.argb((int)(Color.alpha(picker.getColor()) * 0.5), Color.red(picker.getColor()), Color.green(picker.getColor()), Color.blue(picker.getColor()));
		int[] backColors = { leftColor, 0x00000000};
        ((LinearLayout) getActivity().findViewById(R.id.gradient_left)).setBackgroundDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, backColors));
        
        int rightColor = Color.argb((int)(Color.alpha(picker2.getColor()) * 0.5), Color.red(picker2.getColor()), Color.green(picker2.getColor()), Color.blue(picker2.getColor()));
        int[] backColors2 = { rightColor, 0x00000000};
        ((LinearLayout) getActivity().findViewById(R.id.gradient_right)).setBackgroundDrawable(new GradientDrawable(Orientation.BOTTOM_TOP, backColors2));
	}
}
