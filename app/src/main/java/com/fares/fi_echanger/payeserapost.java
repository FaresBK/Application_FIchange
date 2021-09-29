package com.fares.fi_echanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class payeserapost extends AppCompatActivity {
  EditText dinar,ccp,email,nom,prenom,tel;
  TextView euro,valeu;
  Button button,button2,button3;
    DatabaseReference databaseReference,databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payeserapost);
        dinar=findViewById(R.id.Dinarre);
        ccp=findViewById(R.id.CCP1);
        email=findViewById(R.id.email);
        nom=findViewById(R.id.nom);
        prenom=findViewById(R.id.prenom);
        tel=findViewById(R.id.telephon);
        valeu=findViewById(R.id.valudinar);
        euro=findViewById(R.id.euroo);
        button2=findViewById(R.id.valider);
        euro.findViewById(R.id.euroo);
        button3=findViewById(R.id.annuler);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Euro");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       valeu.setText(snapshot.getValue().toString());
                       button2.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               double a=Double.parseDouble(snapshot.getValue().toString());
                               double b=Double.parseDouble(dinar.getText().toString());
                               if (b<=a){
                                   String achat=dinar.getText().toString();
                                   String ccp1=ccp.getText().toString();
                                   String euro1= euro.getText().toString();
                                   String email1=email.getText().toString();
                                   String nom1=nom.getText().toString();
                                   String prenom1=prenom.getText().toString();
                                   String tele=tel.getText().toString();
                                   if (achat.isEmpty()){
                                       dinar.setError("S'il Vous Plaît Enter Salair dinar");
                                       dinar.requestFocus();
                                   }else if (ccp1.isEmpty()){
                                       ccp.setError("S'il Vous Plaît Entre être CCP");
                                       ccp.requestFocus();
                                   }else if(email1.isEmpty()){
                                       email.setError("S'il Vous Plaît Entre Vous être Email paysera");
                                       email.requestFocus();
                                   }else if (nom1.isEmpty()){
                                       nom.setError("S'il Vous Plaît Entre Vous être Nom ");
                                       nom.requestFocus();
                                   }else if (prenom1.isEmpty()){
                                       prenom.setError("S'il Vous Plaît Entre Vous être Prenom");
                                       prenom.requestFocus();
                                   }
                                   else if (tele.isEmpty()){
                                       tel.setError("S'il Vous Plait Entre Vous être Telephone");
                                       tel.requestFocus();
                                   }else {
                                        double x=a-b;
                                        Intent intent=new Intent(payeserapost.this,enrigstrer1.class);
                                       intent.putExtra("x",x);
                                       intent.putExtra("operation","Euro");
                                       intent.putExtra("achatt",achat);
                                       intent.putExtra("ccp",ccp1);
                                       intent.putExtra("euro",euro1);
                                       intent.putExtra("email",email1);
                                       intent.putExtra("nom",nom1);
                                       intent.putExtra("prenom",prenom1);
                                       intent.putExtra("telephone",tele);
                                       intent.putExtra("achatt",achat);
                                       intent.putExtra("operation1","ACHETER EURO");
                                       intent.putExtra("kima","€");
                                       intent.putExtra("kima1","DZ");
                                       startActivity(intent);

                                   }
                               }else{
                                   Toast.makeText(payeserapost.this, "Cette Opération impossible", Toast.LENGTH_SHORT).show();
                               }
                               
                           }
                       });
                   }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(payeserapost.this, "problème de connexion", Toast.LENGTH_SHORT).show();

            }
        });
        databaseReference1=FirebaseDatabase.getInstance().getReference().child("valeuEuro");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    final String h=snapshot.getValue().toString();
                     dinar.addTextChangedListener(new TextWatcher() {
                         @Override
                         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                         }

                         @Override
                         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                          if (charSequence.equals("")){
                              Toast.makeText(payeserapost.this, "Remplire", Toast.LENGTH_SHORT).show();
                              euro.setText("");
                          }else{
                              double b;
                              try{
                                  b=Double.parseDouble(dinar.getText().toString());
                              }catch (NumberFormatException e){
                                  b=0;
                              }
                              double val=Double.parseDouble(valeu.getText().toString());
                              double a=Double.parseDouble(h);
                              if(b<=val){
                                  double x=b*a;
                                  euro.setText(String.valueOf(x));
                              }else{
                                  Toast.makeText(payeserapost.this, "cette opération impossible", Toast.LENGTH_SHORT).show();
                              }
                          }
                         }

                         @Override
                         public void afterTextChanged(Editable editable) {

                         }
                     });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(payeserapost.this, "problème de connexion", Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(payeserapost.this,change.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
