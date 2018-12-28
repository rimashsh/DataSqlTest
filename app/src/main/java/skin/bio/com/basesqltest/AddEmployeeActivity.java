package skin.bio.com.basesqltest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import skin.bio.com.basesqltest.models.EmployeeWrapper;
import skin.bio.com.basesqltest.services.Config;
import skin.bio.com.basesqltest.services.RequestHandler;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextName;
    private EditText editTextDesg;

    private TextView tvId;

    private Button buttonAdd;
    private Button buttonDelete;

    private EmployeeWrapper mEmployee;
    private String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        initView();
        from = getIntent().getStringExtra("from");

        if (from.equals("update")){
            mEmployee = (EmployeeWrapper) getIntent().
                    getSerializableExtra("employee");

            tvId.setVisibility(View.VISIBLE);
            buttonAdd.setText("Update Employee");

            tvId.setText(Integer.toString(mEmployee.getId()));
            editTextName.setText(mEmployee.getName());
            editTextDesg.setText(mEmployee.getDesignation());

            buttonDelete.setVisibility(View.VISIBLE);

        }

    }

    private void initView() {

        tvId = (TextView) findViewById(R.id.emp_id);
        tvId.setVisibility(View.INVISIBLE);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDesg = (EditText) findViewById(R.id.editTextDesignation);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);


        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
    }

    //Adding an employee
    private void addEmployee(){

        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddEmployeeActivity.this,"Adding...","Wait...",false,false);
            }


            @Override
            protected String doInBackground(Void... v) {


                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_DESG,desg);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddEmployeeActivity.this,s,Toast.LENGTH_LONG).show();
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    private void updateEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddEmployeeActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddEmployeeActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID,Integer.toString(mEmployee.getId()));
                hashMap.put(Config.KEY_EMP_NAME,name);
                hashMap.put(Config.KEY_EMP_DESG,desg);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddEmployeeActivity.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(AddEmployeeActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_EMP,
                        Integer.toString(mEmployee.getId()));
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.buttonAdd){

            if (from.equals("add")){

                addEmployee();
            } else if (from.equals("update")){
                updateEmployee();
            }
            finish();
        } else if (id == R.id.buttonDelete){
            deleteEmployee();
            Intent intent = new Intent(this,
                    ProfileEmployeeActivity.class);
            setResult( Config.PICK_ADD_UPDATE_EMPLOYEE_RESULT, intent);
            finish();
        }
    }
}
