package com.fares.fi_echanger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class enrigstrer2 extends AppCompatActivity {
    TextView achatdinare,eurodollar,cart,user,ccp,telephon,kima,kima1;
    String achat1,dinar1,cart1,nom1,prenom1,ccp2,tele2,operation,opertion1;
    Double som;
    Button confermer,Retour;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrigstrer2);
        dialog=new Dialog(enrigstrer2.this);
        dialog.setContentView(R.layout.dialog1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dilogee));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.animation;
        dialog.getWindow().setLayout(800,800);
        Button sortie=dialog.findViewById(R.id.buttonsortier1);

        achatdinare=(TextView)findViewById(R.id.achat);
        eurodollar=(TextView)findViewById(R.id.eurodollar);
        ccp=(TextView)findViewById(R.id.ccp);
        cart=(TextView)findViewById(R.id.cart);
        user=(TextView)findViewById(R.id.user);
        telephon=(TextView)findViewById(R.id.tele);
        confermer=(Button)findViewById(R.id.conf);
        Retour=(Button)findViewById(R.id.Retour);
        kima=(TextView)findViewById(R.id.kima);
        kima1=(TextView)findViewById(R.id.kima1);
        achat1= getIntent().getStringExtra("achat");
        dinar1=getIntent().getStringExtra("eurodollar");
        cart1=getIntent().getStringExtra("email");
        nom1=getIntent().getStringExtra("nom");
        prenom1=getIntent().getStringExtra("prenom");
        ccp2=getIntent().getStringExtra("ccp");
        tele2=getIntent().getStringExtra("telephone");
        operation=getIntent().getStringExtra("operation1");
        opertion1=getIntent().getStringExtra("operation2");
        som=getIntent().getDoubleExtra("x",0);

        achatdinare.setText(dinar1);
        eurodollar.setText(achat1);
        ccp.setText(ccp2);
        cart.setText(cart1);
        user.setText(nom1+" "+prenom1);
        telephon.setText(tele2);
        kima.setText(getIntent().getStringExtra("kimaa"));
        kima1.setText(getIntent().getStringExtra("kimaa1"));

        confermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mf = firebaseDatabase.getReference(opertion1);
                NumberFormat nf=NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                mf.setValue(nf.format(som));
                String Date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String heur = sdf.format(new Date());
                FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                DatabaseReference fr = firebaseDatabase1.getReference(operation);
                fr.push().setValue(operation +" "+ achat1 + " Email : " + cart1 + " DINARE  " + dinar1 + " DA "+" Date "+ Date + " HEUR " + heur + "  CCP  " + ccp2 + " NOM " + nom1 + " PRENOM " + prenom1 + "  Téléphone  " + tele2);
                dialog.show();

            }
        });
        sortie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(enrigstrer2.this,change.class);
                startActivity(intent);
                finish();
            }
        });
          Retour.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  enrigstrer2.super.onBackPressed();
                  finish();
              }
          });

    }
}
