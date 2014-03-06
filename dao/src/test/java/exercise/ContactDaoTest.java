package exercise;

import exercise.dao.contact.ContactDao;
import exercise.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ContactDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Resource(name = "contactDao")
    private ContactDao contactDao;

    @BeforeMethod
    @Transactional
    public void initialLoad() {
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();
        Contact contact4 = new Contact();
        Contact contact5 = new Contact();

        contact1.setForename("Andy");
        contact1.setSurname("Smith");
        contact1.setStreet("Crofton Close");
        contact1.setCity("Bracknell");
        contact1.setCountry("UK");
        contact1.setCounty("Berks");
        contact1.setPostalCode("RG120UT");

        contact2.setForename("Chiara");
        contact2.setSurname("Pierucci");
        contact2.setStreet("Crofton Close");
        contact2.setCity("Bracknell");
        contact2.setCountry("UK");
        contact2.setCounty("Berks");
        contact2.setPostalCode("RG220UT");

        contact3.setForename("Michael");
        contact3.setSurname("Smith");
        contact3.setStreet("Lynacres");
        contact3.setCity("Wing");
        contact3.setCountry("UK");
        contact3.setCounty("Bucks");
        contact3.setPostalCode("LU70pw");

        contact4.setForename("Carmel");
        contact4.setSurname("Smith");
        contact4.setStreet("Lynacres");
        contact4.setCity("Wing");
        contact4.setCountry("UK");
        contact4.setCounty("Bucks");
        contact4.setPostalCode("LU70PW");

        contact5.setForename("Mickey");
        contact5.setSurname("Mouse");
        contact5.setStreet("Disney World");
        contact5.setCity("Florida");
        contact5.setCountry("USA");
        contact5.setCounty("NA");
        contact5.setPostalCode("32830");

        contactDao.save(contact1);
        contactDao.save(contact2);
        contactDao.save(contact3);
        contactDao.save(contact4);
        contactDao.save(contact5);
    }

    @Test
    @Transactional
    public void searchContacts() {

        List<Contact> contacts = contactDao.searchContacts("Mick", null, null, null, null, null, null);
        assert (contacts.size() == 1);
        contacts = contactDao.searchContacts("Mick", "rubbish", null, null, null, null, null);
        assert (contacts.size() == 0);
        contacts = contactDao.searchContacts(null, null, null, null, null, null, null);
        assert (contacts.size() == 5);

    }

}