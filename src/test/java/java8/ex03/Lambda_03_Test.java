package java8.ex03;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - ForEach
 */
public class Lambda_03_Test {

    // tag::PersonProcessor[]
    interface PersonProcessor {
        void process(Person p);
    }
    // end::PersonProcessor[]

    // tag::forEach[]
    private void forEach(List<Person> source, PersonProcessor processor) {
       // TOD0
    for(Person a:source){
    	processor.process(a);
    }
    }
    // end::forEach[]


    // tag::test_verify_person[]
    @Test
    public void test_verify_person() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO vérifier qu'une personne a un prénom qui commence par first
        // TODO vérifier qu'une personne a un nom qui commence par last
        // TODO vérifier qu'une personne a un age > 0
        
        PersonProcessor verifyPerson = p -> {
        	assertTrue(p.getFirstname().contains("first"));
        	assertTrue(p.getLastname().contains("last"));
        	assertTrue(p.getAge() > 0);
        };

        assertThat(verifyPerson, notNullValue());

        forEach(personList, verifyPerson);
    }
    // end::test_verify_person[]

}
