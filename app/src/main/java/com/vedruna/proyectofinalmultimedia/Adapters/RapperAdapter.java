package com.vedruna.proyectofinalmultimedia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.proyectofinalmultimedia.Model.Rapper;
import com.vedruna.proyectofinalmultimedia.R;

import java.util.List;


/**
 * Este adaptador se utiliza para mostrar la información de los raperos en una lista.
 */
public class RapperAdapter extends BaseAdapter {

    List<Rapper> rappers;

    Context context;

    /**
     * Constructor del adaptador.
     * @param context El contexto de la aplicación.
     * @param rappers La lista de objetos Rapper a mostrar en la lista.
     */
    public RapperAdapter(Context context,List<Rapper> rappers) {
        this.context = context;
        this.rappers = rappers;
    }

    @Override
    public int getCount() {
        return rappers.size();
    }

    @Override
    public Object getItem(int i) {
        return rappers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return rappers.get(i).getIdRapper();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            //  Inflar el diseño de la vista de la lista si es nulo
            convertView = LayoutInflater.from(context).inflate(R.layout.rapper_list, parent, false);

            // Crear un nuevo ViewHolder y asignar las vistas correspondientes
            viewHolder = new ViewHolder();
            viewHolder.idLabel = convertView.findViewById(R.id.idLabel);
            viewHolder.idText = convertView.findViewById(R.id.idText);
            viewHolder.nameLabel = convertView.findViewById(R.id.nameLabel);
            viewHolder.nameText = convertView.findViewById(R.id.nameText);
            viewHolder.cityLabel = convertView.findViewById(R.id.cityLabel);
            viewHolder.cityText = convertView.findViewById(R.id.cityText);
            viewHolder.albumLabel = convertView.findViewById(R.id.albumLabel);
            viewHolder.albumText = convertView.findViewById(R.id.albumText);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            // Establecer el ViewHolder como una etiqueta para la vista convertida
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Obtén el rapero actual
        Rapper rapper = (Rapper) getItem(position);

        // Asignar los valores del rapero a las vistas
        viewHolder.idLabel.setText("ID: ");
        viewHolder.idText.setText(String.valueOf(rapper.getIdRapper()));
        viewHolder.nameLabel.setText("Nombre: ");
        viewHolder.nameText.setText(rapper.getName());
        viewHolder.cityLabel.setText("Ciudad: ");
        viewHolder.cityText.setText(rapper.getCity());
        viewHolder.albumLabel.setText("Álbum: ");
        viewHolder.albumText.setText(rapper.getAlbum());
        Picasso.get().load(rapper.getImageURL()).into(viewHolder.imageView);


        return convertView;
    }
    /**
     * Clase interna ViewHolder que contiene las vistas de cada elemento de la lista.
     */
    static class ViewHolder {
        TextView idLabel;
        TextView idText;
        TextView nameLabel;
        TextView nameText;
        TextView cityLabel;
        TextView cityText;
        TextView albumLabel;
        TextView albumText;
        ImageView imageView;
    }

}
