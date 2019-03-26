package java8.ex04;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Exercice 04 - FuncCollection
 */
public class Lambda_04_Test {

	// tag::interfaces[]
	interface GenericPredicate<T> {
		// TODO
		boolean test(T p);
	}

	interface GenericMapper<T, E> {
		// TODO
		E map(T p);
	}

	interface Processor<T> {
		// TODO
		void process(T p);
	}
	// end::interfaces[]

	// tag::FuncCollection[]
	class FuncCollection<T> {

		private Collection<T> list = new ArrayList<>();

		public void add(T t) {
			list.add(t);
		}

		public void addAll(Collection<T> all) {
			for (T el : all) {
				list.add(el);
			}
		}
		// end::FuncCollection[]

		// tag::methods[]
		private FuncCollection<T> filter(GenericPredicate<T> predicate) {
			FuncCollection<T> result = new FuncCollection<>();
			// TODO
			for (T a : this.list) {
				if (predicate.test(a)) {
					result.add(a);
				}
			}
			return result;
		}

		private <E> FuncCollection<E> map(GenericMapper<T, E> mapper) {
			FuncCollection<E> result = new FuncCollection<>();
			// TODO
			for (T a : this.list) {
				result.add(mapper.map(a));
			}
			return result;
		}

		private void forEach(Processor<T> processor) {
			// TODO
			for (T a : this.list) {
				processor.process(a);
			}
		}
		// end::methods[]

	}

	// tag::test_filter_map_forEach[]
	@Test
	public void test_filter_map_forEach() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		personFuncCollection
				// TODO filter, ne garde uniquement que les personnes ayant un
				// age > 50
				.filter(p -> p.getAge() > 50)
				// TODO transformer la liste de personnes en liste de comptes.
				// Un compte a par défaut un solde à 1000.
				.map(p -> {
					Account account = new Account();
					account.setBalance(1000);
					account.setOwner((Person) p);
					return account;
				})
				// TODO vérifier que chaque compte a un solde à 1000.
				// TODO vérifier que chaque titulaire de compte a un age > 50
				.forEach(account -> {
					assertTrue(account.getBalance() == 1000);
					assertTrue(account.getOwner().getAge() > 50);
				});
	}
	// end::test_filter_map_forEach[]

	// tag::test_filter_map_forEach_with_vars[]
	@Test
	public void test_filter_map_forEach_with_vars() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		FuncCollection<Person> personFuncCollection = new FuncCollection<>();
		personFuncCollection.addAll(personList);

		// TODO créer un variable filterByAge de type GenericPredicate
		// TODO filtrer, ne garder uniquement que les personnes ayant un age >
		// 50
		// ??? filterByAge = ???;
		GenericPredicate<Person> filterByAge = p -> p.getAge() > 50;
		

		// TODO créer un variable mapToAccount de type GenericMapper
		// TODO transformer la liste de personnes en liste de comptes. Un compte
		// a par défaut un solde à 1000.
		// ??? mapToAccount = ???;
		Account account = new Account();
		
		GenericMapper<Person, Account> mapToAccount = p -> {
			account.setBalance(1000);
			account.setOwner(p);
			return account;
		};
		

		// TODO créer un variable verifyAccount de type Processor
		// TODO vérifier que chaque compte a un solde à 1000.
		// TODO vérifier que chaque titulaire de compte a un age > 50
		// ??? verifyAccount = ???;
		Processor<Account> verifyAccount = p -> {
			assertTrue(account.getBalance() == 1000);
			assertTrue(account.getOwner().getAge() > 50);
		};
		
		
		/*
		 * TODO Décommenter personFuncCollection .filter(filterByAge)
		 * .map(mapToAccount) .forEach(verifyAccount);
		 */
		personFuncCollection 	.filter(filterByAge)
								.map(mapToAccount)
								.forEach(verifyAccount);
	}
	// end::test_filter_map_forEach_with_vars[]

}
