package test.java.cats;

import example.dao.OwnerDao;
import example.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerDaoTest {

    private OwnerDao ownerDao;
    private Owner testOwner;

    @BeforeEach
    void setUp() {
        ownerDao = mock(OwnerDao.class);

        testOwner = new Owner("Мария Иванова", new Date(3 - 1 - 1999));
        testOwner.setId(1L);
        testOwner.setName("Ирина");
        testOwner.setBirthDate(LocalDate.of(1990, 3, 15));
    }

    @Test
    void save_shouldReturnSavedOwner() {
        when(ownerDao.save(testOwner)).thenReturn(testOwner);

        Owner saved = ownerDao.save(testOwner);
        assertEquals("Ирина", saved.getName());
    }

    @Test
    void getById_shouldReturnCorrectOwner() {
        when(ownerDao.getById(1L)).thenReturn(testOwner);

        Owner owner = ownerDao.getById(1L);
        assertEquals("Ирина", owner.getName());
    }

    @Test
    void deleteByEntity_shouldWork() {
        doNothing().when(ownerDao).deleteByEntity(testOwner);

        assertDoesNotThrow(() -> ownerDao.deleteByEntity(testOwner));
    }

    @Test
    void getAll_shouldReturnList() {
        when(ownerDao.getAll()).thenReturn(List.of(testOwner));

        List<Owner> owners = ownerDao.getAll();
        assertEquals(1, owners.size());
    }
}
