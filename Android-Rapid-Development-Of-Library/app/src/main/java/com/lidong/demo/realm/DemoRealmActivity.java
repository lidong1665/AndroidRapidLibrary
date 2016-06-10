package com.lidong.demo.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lidong.demo.R;

import java.util.List;

import io.realm.Realm;

public class DemoRealmActivity extends AppCompatActivity {

    static final String TAG = DemoRealmActivity.class.getSimpleName();
    Realm  mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_realm);

//         mRealm= RealmUtil.getIntance(this).getRealm();
//
//
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Person person = realm.createObject(Person.class);
//                person.setName("李东");
//                person.setAge(24);
//                person.setCode(UUID.randomUUID().toString());
//            }
//        });
        Person person = new Person();
                person.setName("李东1");
                person.setAge(28);
                person.setCode("6e56d3aa-7119-429e-8c59-7ad8241e838d");
        PersonDao dao = new PersonDaoImpl(this);
//        try {
//            dao.insert(person);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            dao.deletePerson("6e56d3aa-7119-429e-8c59-7ad8241e838d");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Person> persons = dao.getAllPerson();
            Log.d(TAG, "onCreate: "+persons);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
