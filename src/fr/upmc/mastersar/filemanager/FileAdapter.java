package fr.upmc.mastersar.filemanager;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends ArrayAdapter<File> {

	public FileAdapter(Context context, int resource, List<File> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;

		if (view == null) {
			LayoutInflater li = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			view = li.inflate(R.layout.cell_list, null);
		}

		Resources res = view.getResources();
		File file = getItem(position);
		if (file != null) {
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(file.getName());

			ImageView imageView = (ImageView) view.findViewById(R.id.icon);
			imageView.setImageDrawable(getImageForExtension(file, res));
		}

		return view;
	}

	public Drawable getImageForExtension(File file, Resources res) {
		Drawable d = null;
		String extension = "";
		
		if (file.isDirectory()) {
			d = res.getDrawable(R.drawable.dossier);
		} else {
			String filename = file.getName();
			int dotPos = filename.lastIndexOf(".");
			if( dotPos != -1 )
				extension = filename.substring(dotPos);
			else
				extension = "";

			if (!extension.equals("")) {
				if (extension.equals(".mp3") || extension.equals(".amr")) {
					d = res.getDrawable(R.drawable.musique);
				} else if (extension.equals(".mp4") || extension.equals(".avi")
						|| extension.equals(".mpg")) {
					d = res.getDrawable(R.drawable.film);
				} else if (extension.equals(".pdf")) {
					d = res.getDrawable(R.drawable.pdf);
				} else if (extension.equals(".jpg")
						|| extension.equals(".jpeg")) {
					d = res.getDrawable(R.drawable.jpg);
				} else if (extension.equals(".xml")) {
					d = res.getDrawable(R.drawable.xml);
				} else if (extension.equals(".apk")) {
					d = res.getDrawable(R.drawable.android);
				} else {
					d = res.getDrawable(R.drawable.fichier);
				}
			} else {
				d = res.getDrawable(R.drawable.fichier);
			}

		}

		return d;
	}

}
