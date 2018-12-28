package skin.bio.com.basesqltest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import skin.bio.com.basesqltest.R;
import skin.bio.com.basesqltest.models.EmployeeWrapper;

/**
 * Created by adnen on 4/5/16.
 */
public class EmployeeContentAdapter extends RecyclerView.Adapter<EmployeeContentAdapter.ViewHolder> {


    private ArrayList<EmployeeWrapper> mEmployeesList;
    private Context mContext;

    public EmployeeContentAdapter() {
    }

    public EmployeeContentAdapter(ArrayList<EmployeeWrapper> itemsList) {
        this.mEmployeesList = itemsList;
    }

    public EmployeeContentAdapter(Context context, ArrayList<EmployeeWrapper> itemsList) {
        this.mContext = context;
        this.mEmployeesList = itemsList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_employee, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        EmployeeWrapper employee = mEmployeesList.get(position);

        holder.name.setText(employee.getName());
        holder.designation.setText(employee.getDesignation());

    }

    @Override
    public int getItemCount() {
        return mEmployeesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView designation;


        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.emp_name);
            designation = (TextView) view.findViewById(R.id.emp_designation);
        }

    }
}
