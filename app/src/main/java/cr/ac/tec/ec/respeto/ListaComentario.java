package cr.ac.tec.ec.respeto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


// CLASE utilizada para trasnformar una lista de denuncias a un list view
public class ListaComentario extends ArrayAdapter<Comentario> {

    private Activity context;
    private List<Comentario> listaComenteario;

    public ListaComentario(Activity context, List<Comentario> listaComentario) {
        super(context, R.layout.list_layout, listaComentario);
        this.context = context;
        this.listaComenteario = listaComenteario;


    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);


        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewBody = listViewItem.findViewById(R.id.textViewBody);
        TextView textViewLugar = listViewItem.findViewById(R.id.textViewLugar);
        TextView textViewTime = listViewItem.findViewById(R.id.textViewTime);

        Comentario comentario = listaComenteario.get(position);

        textViewName.setText(comentario.getIdUsuario());
        textViewBody.setText(comentario.getCuerpo());
        textViewTime.setText(comentario.getFechaHoraString());


        return listViewItem;

    }
}
/*
*

* */