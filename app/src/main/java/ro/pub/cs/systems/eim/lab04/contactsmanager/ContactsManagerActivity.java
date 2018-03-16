package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    static Button show_button;
    static Button save_button;
    static Button cancel_button;

    public class Toggle implements View.OnClickListener {

        Toggle() {}

        public void onClick(View view) {
            if (view == show_button) {
                LinearLayout container = (LinearLayout)findViewById(R.id.bottom_layout);
                if (show_button.getText() == "SHOW ADDITIONAL FIELDS") {
                    Button button = (Button) show_button;
                    button.setText("HIDE ADDITIONAL FIELDS");
                    container.setVisibility(View.VISIBLE);
                } else {
                    Button button = (Button) show_button;
                    button.setText("SHOW ADDITIONAL FIELDS");
                    container.setVisibility(View.GONE);
                }
            }

            else if (view == save_button) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                EditText name = (EditText)findViewById(R.id.editText11);
                EditText phone = (EditText)findViewById(R.id.editText21);
                EditText email = (EditText)findViewById(R.id.editText31);
                EditText address = (EditText)findViewById(R.id.editText41);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        show_button = (Button)findViewById(R.id.button11);
        save_button = (Button)findViewById(R.id.save_button1);
        cancel_button = (Button)findViewById(R.id.cancel_button1);

        Toggle toggle = new Toggle();
        show_button.setOnClickListener(toggle);
        save_button.setOnClickListener(toggle);


    }
}
