package com.example.wanmac.myapplication;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private TextView TVssn, TVfName, TVlName, TVyBirth, TVcity, TVjoined;
    private static final String TAG = "asdf??";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AmDataBaseHelper helper = AmDataBaseHelper.getINSTANCE(MainActivity.this);

        DaDepartment employee1 = new DaDepartment("1", "companyOne", 233, 3);
        DaDepartment employee2 = new DaDepartment("2", "companyTwo", 322, 4);
        DaDepartment employee3 = new DaDepartment("3", "companyThree", 333, 5);
//
        helper.insertRowDepartment(employee1);
        helper.insertRowDepartment(employee2);
        helper.insertRowDepartment(employee3);

        //String asdf = helper.checkDepartment();
        //Log.d(TAG, asdf);

        TVssn = (TextView) findViewById(R.id.showSSN);
        TVfName = (TextView) findViewById(R.id.showFname);
        TVlName = (TextView) findViewById(R.id.showLname);
        TVyBirth = (TextView) findViewById(R.id.showYbirth);
        TVcity = (TextView) findViewById(R.id.showCity);
        TVjoined = (TextView) findViewById(R.id.showJoiedList) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Chose Employee or Job")
                        .setMessage("Welcome to ADI")
                        .setPositiveButton("Job", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                departmentInputDialog();
                                Toast.makeText(MainActivity.this, "you clicked OK",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Employee", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showInputDialog();
                                Toast.makeText(MainActivity.this, "you clicked Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();


            }//end of onClick

        });//End fo fab

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editSSN, editFName, editLName, editYbityh, editCity;

        editSSN = (EditText) promptView.findViewById(R.id.editSSN);
        editFName = (EditText) promptView.findViewById(R.id.editFName);
        editLName = (EditText) promptView.findViewById(R.id.editLName);
        editYbityh = (EditText) promptView.findViewById(R.id.editYBirth);
        editCity = (EditText) promptView.findViewById(R.id.editCity);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AmDataBaseHelper helper = AmDataBaseHelper.getINSTANCE(MainActivity.this);

                        String ssn  = editSSN.getText().toString();
                        String fname = editFName.getText().toString();
                        String lname = editLName.getText().toString();
                        String yBirth = editYbityh.getText().toString();
                        String  city = editCity.getText().toString();

                        TVssn.setText("SSN: " + editSSN.getText());
                        TVfName.setText("First Name: " + editFName.getText());
                        TVlName.setText("Last Name: " + editLName.getText());
                        TVyBirth.setText("Year of Bityh: " + editYbityh.getText());
                        TVcity.setText("City: " + editCity.getText());

                        DaEmployee employee = new DaEmployee(ssn, fname, lname,yBirth, city);
                        helper.insertRowEmployee(employee);

//                        String joinedName =  helper.getNameCompanyJoins();
//
//                        Log.v(TAG, "before joinName");
//                        Log.v(TAG, joinedName);

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }//End of ShowInputDialog

    protected void departmentInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_department, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editDSSN, editCompany, editSalary, editExperience;

        editDSSN = (EditText) promptView.findViewById(R.id.editDepartSSN);
        editCompany = (EditText) promptView.findViewById(R.id.editComany);
        editSalary = (EditText) promptView.findViewById(R.id.editSalary);
        editExperience = (EditText) promptView.findViewById(R.id.editExperience);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AmDataBaseHelper helper = AmDataBaseHelper.getINSTANCE(MainActivity.this);

                        String dSSN  =editDSSN.getText().toString();
                        String company = editCompany.getText().toString();
                        int salary = Integer.parseInt(editSalary.getText().toString());
                        int experience = Integer.parseInt(editExperience.getText().toString());

//                        TVssn.setText("SSN: " + editSSN.getText());
//                        TVfName.setText("First Name: " + editFName.getText());
//                        TVlName.setText("Last Name: " + editLName.getText());
//                        TVyBirth.setText("Year of Bityh: " + editYbityh.getText());
//                        TVcity.setText("City: " + editCity.getText());
//
                        DaDepartment department = new DaDepartment(dSSN, company,salary, experience);
                        helper.insertRowDepartment(department);

//                        String joinedName =  helper.getNameCompanyJoins();
//
//                        Log.v(TAG, "before joinName");
//                        Log.v(TAG, joinedName);

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }//departmentInputDialog

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.wanmac.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.wanmac.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
