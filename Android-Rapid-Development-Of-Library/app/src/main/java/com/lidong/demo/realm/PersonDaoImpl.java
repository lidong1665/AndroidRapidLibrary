package com.lidong.demo.realm;

import android.content.Context;

import java.util.List;

import io.realm.Realm;

/**
*
*@className:PersonDaoImpl
*@desc:
*@author:lidong
*@datetime:16/6/10 下午10:01

*/
public class PersonDaoImpl implements PersonDao {

    private Context context;
    private Realm mRealm;

    public PersonDaoImpl(Context context){
        mRealm = RealmUtil.getIntance(context).getRealm();
    }


    /**
     * @同步插入用户
     * @param person
     * @throws Exception
     */
    @Override
    public void insert(Person person) throws Exception {
        mRealm.beginTransaction();
        Person person1 = mRealm.copyToRealm(person);
        mRealm.commitTransaction();
        mRealm.close();
    }

    /**
     * 获取所有的用户
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Person> getAllPerson() throws Exception {
        List<Person> mlist = null;
        mlist =  mRealm.where(Person.class).findAll();
        mRealm.close();
        return mlist;
    }

    /**
     * @param person
     * @throws Exception
     */
    @Override
    public Person updatePerson(Person person) throws Exception {
        mRealm.beginTransaction();
        Person person1 = mRealm.copyToRealmOrUpdate(person);
        mRealm.commitTransaction();
        mRealm.close();
        return  person1;
    }

    @Override
    public void deletePerson(String code) throws Exception {
        Person person = mRealm.where(Person.class).equalTo("code",code).findFirst();
        mRealm.beginTransaction();
        person.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * 异步插入Person
     *
     * @param person
     * @throws Exception
     */
    @Override
    public void insertPersonAsync(final Person person) throws Exception {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.beginTransaction();
                Person person1 = realm.copyToRealm(person);
                realm.commitTransaction();
                realm.close();
            }
        });
        mRealm.close();
    }


}


