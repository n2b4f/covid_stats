package com.example.kursach_covid_stats;

import android.annotation.SuppressLint;
        import android.app.LauncherActivity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import com.example.kursach_covid_stats.R;
        import java.util.List;
//Создание таблицы
public class CustomArrayAdapter extends ArrayAdapter  {

    private LayoutInflater inflater;  //рисует layout на экране
    private List<ListItemClass> listItem;
    private Context context;
    private int layout;


    public CustomArrayAdapter(@NonNull Context context, int resource, List<ListItemClass> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;
        this.context = context;
    }


    /*при прокрутке в ListView, если в списке очень много объектов, то для каждого элемента, когда он попадет в зону видимости,
     будет повторно вызываться метод getView, в котором будет заново создаваться новый объект View.
     Соответственно будет увеличиваться потреление памяти и снижаться производительность. Поэтому оптимизируем код метода getView:
     Параметр convertView указывает на элемент View, который используется для объекта в списке по позиции position.
     Если ранее уже создавался View для этого объекта, то параметр convertView уже содержит некоторое значение,
     которое мы можем использовать.
     */
    @SuppressLint({"ViewHolder", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemClass listItemMain = listItem.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_1, null, false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            //viewHolder.number = convertView.findViewById(R.id.TVdata1);
            viewHolder.name = convertView.findViewById(R.id.TVdata2);
            viewHolder.cases = convertView.findViewById(R.id.TVdata3);
            //viewHolder.cases_on_thousand = convertView.findViewById(R.id.TVdata4);
            viewHolder.died = convertView.findViewById(R.id.TVdata5);
            viewHolder.recovered = convertView.findViewById(R.id.TVdata6);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.number.setText(listItemMain.getData_1());
        viewHolder.name.setText(listItemMain.getData_2());
        viewHolder.cases.setText(listItemMain.getData_3());
        //viewHolder.cases_on_thousand.setText(listItemMain.getData_4());
        viewHolder.died.setText(listItemMain.getData_5());
        viewHolder.recovered.setText(listItemMain.getData_6());
        return convertView;
    }
    //ViewHolder сохраняет ссылки на необходимые в элементе списка шаблоны.
    private class ViewHolder{
        TextView number;
        TextView name;
        TextView cases;
        TextView cases_on_thousand;
        TextView died;
        TextView recovered;
    }
}
