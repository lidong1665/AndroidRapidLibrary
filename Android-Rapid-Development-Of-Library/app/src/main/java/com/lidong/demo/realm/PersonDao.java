package com.lidong.demo.realm;

import java.util.List;

/**
 * Created by lidong on 16/6/9.
 */
public interface PersonDao {

    /**
     * 插入Person
     * @param person
     * @throws Exception
     */
   void insert(Person person)throws  Exception;


    /**
     * 获取所有的用户
     * @return
     * @throws Exception
     */
   List<Person> getAllPerson()throws Exception;

    /**
     *  更新用户
      * @throws Exception
     */
   Person  updatePerson(Person person)throws Exception;

    /**
     * 删除用户
     * @param code
     * @throws Exception
     */
    void   deletePerson(String code)throws Exception;

    /**
     * 异步插入Person
     * @param person
     * @throws Exception
     */
    void insertPersonAsync(Person person)throws  Exception;



}
