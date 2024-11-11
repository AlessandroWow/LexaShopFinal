package com.example.example;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaro las variables que el usuario utilizara
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        spinner = findViewById(R.id.spinnerroles);

        // Arreglo de elementos que apareceran en mi spinner
        String[] roles = {"Administrador", "Usuario"};
        // Creamos un ArrayAdapter para poblar el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asignar el ArrayAdapter al Spinner
        spinner.setAdapter(adapter);
    }

    public void onClickAcceder(View view) {
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();

        if (user.isEmpty()) {
            Toast.makeText(this, "El campo de usuario está vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "El campo de contraseña está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si es un usuario
        if (user.equals("usuario1") && pass.equals("1234") && rol.equals("Usuario")){
            mostrarCargaYRedirigir(AccesoActivity.class);
        }
        // Verificar si es un administrador
        else if (user.equals("administrador") && pass.equals("1234") && rol.equals("Administrador")){
            mostrarCargaYRedirigir(AccesoAdministrador.class);

        }
        else if (user.equals("usuario2") && pass.equals("4567") && rol.equals("Usuario")) {
            mostrarCargaYRedirigir(NuevoUsuario.class);
            
        } else if (user.equals("administrador2") && pass.equals("4567") && rol.equals("Administrador")) {
            mostrarCargaYRedirigir(MainInactivity.class);
        } else {
            Toast.makeText(this, "Datos incorrectos, vuelve a intentarlo", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para mostrar el indicador de carga y redirigir a la actividad correspondiente
    private void mostrarCargaYRedirigir(Class<?> actividadDestino) {
        // Mostrar indicador de carga
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Simular tiempo de carga usando un hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simular 3 segundos de espera
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Cambiar a la nueva actividad después de la espera
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, actividadDestino);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }
}
