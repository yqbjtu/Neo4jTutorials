
package com.yq.repository;

import com.yq.domain.Movie;
import com.yq.domain.Person;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.ogm.cypher.ComparisonOperator.EQUALS;

/**
 * Simple to Introduction
 * className: CustomizedRepositoryImpl
 *
 * @author YangQian
 * @version 2018/4/19 14:40
 */
public class CustomizedRepositoryImpl <T> implements CustomizedRepository <T>{

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<T> someCustomMethod() {
        return null;
    }

    @Override
    public Iterable<T> findByNodeId(Class<T> objectType, Integer id) {
        Session session = sessionFactory.openSession();
        Iterable<T> result = null;
        try ( Transaction transaction = session.beginTransaction()) {
            //String cql = "MATCH (n:Movie)<-[]-(s) WHERE n.id= $id return n";
            String cql = "MATCH (n) WHERE id(n)=$id return n";
            Map<String, Integer> map = new HashMap<>();
            map.put("id", id);
            result = session.query(objectType, cql, map);
            System.out.println("end result:" + result);

            transaction.commit();
        } catch (Exception e) {
            throw  e;
        }

        return result;
    }

    /*
     * 放在service中，一直报无法找到transaction， 只有在这里才可以works well
     */
    @Override
    public Collection<Person> findByNameFilter(String firstName) {
        Session session = sessionFactory.openSession();
        Collection<Person> persons = null;
        try ( Transaction transaction = session.beginTransaction()) {
            persons =
                    session.loadAll(Person.class, new Filter("firstName", EQUALS, firstName));
            transaction.commit();
        } catch (Exception e) {
            throw  e;
        }

        return persons;
    }
}
