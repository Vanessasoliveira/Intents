package br.edu.ifsp.scl.sdm.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityMainBinding binding = .setContentView(this, R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.mainTb.appTb.setTitle("Tratando Intents");
        activityMainBinding.mainTb.appTb.setSubtitle("Principais tipos");
        setSupportActionBar(activityMainBinding.mainTb.appTb);


            }

    private void setSupportActionBar(Toolbar appTb) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewMi:
                String url;
                url = activityMainBinding.parameterEt.toString();
                if (url != null) {
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(urlIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "A Url inserida não é válida", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.callMi:
                if (requisitarPermissao()) {
                    discarTelefone();
                } else {
                    Toast.makeText(getApplicationContext(), "Você precisa permitir para realizar ligações", Toast.LENGTH_SHORT).show();
                }
                return (true);
            case R.id.dialMi:
                String telefone = activityMainBinding.parameterEt.getText().toString();
                Intent ligacaoIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("Número de telefone:", telefone, null));
                startActivity(ligacaoIntent);
                return true;
            case R.id.pickMi:
                return true;
            case R.id.chooserMi:
                return true;
            case R.id.exitMi:
                finish();
                return true;
            case R.id.actionMi: Intent actionIntent = new Intent("OPEN_ACTION_ACTIVITY").putExtra(Intent.EXTRA_TEXT, activityMainBinding.parameterEt.getText().toString());
                startActivity(actionIntent);
                return(true);
        } return (false);
    }

    private void discarTelefone() {
        Intent discarIntent = new Intent(Intent.ACTION_CALL, Uri.parse("Número de telefone:" + activityMainBinding.parameterEt.getText().toString()));
        startActivity(discarIntent);
    }

    private boolean requisitarPermissao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return (false);
        } else {
            return (true);
        }
        }else
    {
        return (true);
    }

}

}
