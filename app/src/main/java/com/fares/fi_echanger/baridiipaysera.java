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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class baridiipaysera extends AppCompatActivity {
    TextView textView1,textView2;
    EditText editText,editText1,ccp1,nomm,prenomm,teleph;
    Button button,button1,button2;
    String h;
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baridiipaysera);
        textView1=findViewById(R.id.valueuro);
        textView2=findViewById(R.id.dinarr);
        editText1=findViewById(R.id.email);
        ccp1=findViewById(R.id.ccp);
        nomm=findViewById(R.id.nom);
        prenomm=findViewById(R.id.prenom);
        teleph=findViewById(R.id.telephon);
        editText=findViewById(R.id.euroo);
        button1=findViewById(R.id.valider1);
        button2=findViewById(R.id.annuler);


        //   textView=findViewById(R.id.te);
        // button=findViewById(R.id.butt);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Dinar");
        databaseReference1=FirebaseDatabase.getInstance().getReference().child("achateEuroo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot snapshot) {
                if (snapshot.exists()){
                    final double dinnaarr=Double.parseDouble(snapshot.getValue().toString().replace(",","."));
                    databaseReference2=FirebaseDatabase.getInstance().getReference("achateEuroo");
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull final DataSnapshot snapshot1) {
                            double eurooo=Double.parseDouble(snapshot1.getValue().toString().replace(",","."));
                            double tot=dinnaarr/eurooo;
                            NumberFormat nf=NumberFormat.getInstance();
                            nf.setMaximumFractionDigits(2);
                            textView1.setText(nf.format(tot));
                            button1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    double a=Double.parseDouble(textView1.getText().toString().replace(",","."));
                                    double b=Double.parseDouble(editText.getText().toString().replace(",","."));
                                    if (b<=a) {
                                        String achat=editText.getText().toString();
                                        String emaill=editText1.getText().toString();
                                        String ccp=ccp1.getText().toString();
                                        String nom=nomm.getText().toString();
                                        String prenom=prenomm.getText().toString();
                                        String tele=teleph.getText().toString();
                                        String dinar=textView2.getText().toString();
                                        if (achat.isEmpty()){
                                            editText.setError("S'il Vous Plaît Enter Salair Euro");
                                            editText.requestFocus();
                                        }else if (emaill.isEmpty()){
                                            editText1.setError("S'il Vous Plaît Entre être Email paysera ");
                                            editText1.requestFocus();
                                        }else if(ccp.isEmpty()){
                                            ccp1.setError("S'il Vous Plaît Entre Vous être CCP");
                                            ccp1.requestFocus();
                                        }else if (nom.isEmpty()){
                                            nomm.setError("S'il Vous Plaît Entre Vous être Nom ");
                                            nomm.requestFocus();
                                        }else if (prenom.isEmpty()){
                                            prenomm.setError("S'il Vous Plaît Entre Vous être Prenom");
                                            prenomm.requestFocus();
                                        }
                                        else if (tele.isEmpty()){
                                            teleph.setError("S'il Vous Plait Entre Vous être Telephone");
                                            teleph.requestFocus();
                                        }else {
                                            double x =a - b;
                                            Double x1=x*Double.parseDouble(snapshot1.getValue().toString().replace(",","."));
                                            Intent intent=new Intent(baridiipaysera.this,enrigstrer2.class);
                                            intent.putExtra("x",x1);
                                            intent.putExtra("operation1","VENTE EURO");
                                            intent.putExtra("operation2","Dinar");
                                            intent.putExtra("achat",achat);
                                            intent.putExtra("eurodollar",dinar);
                                            intent.putExtra("ccp",ccp);
                                            intent.putExtra("email",emaill);
                                            intent.putExtra("nom",nom);
                                            intent.putExtra("prenom",prenom);
                                            intent.putExtra("telephone",tele);
                                            intent.putExtra("kimaa","DZ");
                                            intent.putExtra("kimaa1","€");
                                            startActivity(intent);

                                           /* FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            DatabaseReference mf = firebaseDatabase.getReference("Dinar");
                                            NumberFormat nf=NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(2);
                                            mf.setValue(nf.format(x1));
                                            String Date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                            String heur = sdf.format(new Date());
                                            FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                                            DatabaseReference fr = firebaseDatabase1.getReference("VENTE EURO");
                                            fr.push().setValue("EURO Vente: " + achat + "EU   Email : " + emaill + " DINARE " + dinar + "  DA " + Date + " HEUR " + heur + "  CCP  " + ccp + " NOM " + nom + " PRENOM " + prenom + "  Téléphone  " + tele);*/
                                        }
                                    }
                                    if (b>a){
                                        Toast.makeText(baridiipaysera.this, "cette opération impossible", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (error.equals(true)){
                    Toast.makeText(baridiipaysera.this, "problème de connexion", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(baridiipaysera.this, "problème de connexion", Toast.LENGTH_SHORT).show();
            }



        });

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    h=snapshot.getValue().toString();
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if (charSequence.equals("")){
                                Toast.makeText(baridiipaysera.this, "Remplire", Toast.LENGTH_SHORT).show();
                                textView2.setText("");
                            }else{
                                double f=Double.parseDouble(textView1.getText().toString().replace(",","."));
                                double y;
                                try {
                                    y=Double.parseDouble(editText.getText().toString());
                                }catch (NumberFormatException e){
                                    y=0;
                                }
                                double x=Double.parseDouble(h);
                                if (y <= f) {
                                    double s = x * y;
                                    textView2.setText(String.valueOf(s));
                                } else if (y > f) {
                                    Toast.makeText(baridiipaysera.this, "cette opération impossible", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });


                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if(error.equals(true)){
                    Toast.makeText(baridiipaysera.this, "problème de connexion", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(baridiipaysera.this, "problème de connexion", Toast.LENGTH_SHORT).show();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(baridiipaysera.this,change.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
