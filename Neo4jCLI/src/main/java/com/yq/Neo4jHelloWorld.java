package com.yq;

import org.neo4j.driver.v1.*;

import java.util.List;
import java.util.Map;

import java.util.Iterator;

import static org.neo4j.driver.v1.Values.parameters;

import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.Session;

public class Neo4jHelloWorld
{
    private final Driver driver;

    public Neo4jHelloWorld(String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    public String createNode( final String message )
    {
        try ( Session session = driver.session())
        {
            Transaction tx = session.beginTransaction();
            String cql = "CREATE (a:Greeting) " +
                    "SET a.message = $message " +
                    "RETURN a.message + ', from node ' + id(a)";
            //cql = "MATCH (n:Movie) WHERE n.title = '长城B' RETURN n.title";
            StatementResult result = tx.run(cql, parameters("message", message));

            //必须调用commit， 否则create数据无法真正存储到数据库
            tx.commitAsync();
            return result.single().get(0).asString();
        }
    }

    public static void main( String... args ) throws Exception
    {
        Neo4jHelloWorld greeter = new Neo4jHelloWorld( "bolt://localhost:7687", "neo4j", "neo4j123" );
        String message = greeter.createNode("nihao");
        System.out.println("CreateNode:" + message);
    }
}