package cr.ac.tec.ec.respeto;

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

public class ListaDenuncia extends ArrayAdapter<Denuncia>{

    private Activity context;
    private List<Denuncia> listaDenuncia;

    public ListaDenuncia(Activity context, List<Denuncia> listaDenuncia) {
        super(context, R.layout.list_layout, listaDenuncia);
        this.context = context;
        this.listaDenuncia = listaDenuncia;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        EditText textViewBody = listViewItem.findViewById(R.id.textViewBody);

        Denuncia denuncia = listaDenuncia.get(position);

        textViewName.setText("Nombre");
        textViewBody.setText(denuncia.getDescripcion());

        return listViewItem;

    }
}
