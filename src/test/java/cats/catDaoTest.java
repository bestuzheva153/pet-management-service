package test.java.cats;

import example.dao.CatDao;
import example.model.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatDaoTest {

    private CatDao catDao;
    private Cat testCat;

    @BeforeEach
    void setUp() {
        catDao = mock(CatDao.class);

        testCat = new Cat("Барсик", new Date(14 - 4 - 2024), "Мейн-кун", "Черный");
        testCat.setId(1L);
        testCat.setName("Барсик");
        testCat.setBirthDate(LocalDate.of(2020, 5, 10));
        testCat.setBreed("Сибирская");
        testCat.setColor(String.valueOf(Color.BLACK));
    }

    @Test
    void save_shouldReturnSavedCat() {
        when(catDao.save(testCat)).thenReturn(testCat);

        Cat saved = catDao.save(testCat);
        assertEquals("Барсик", saved.getName());
    }

    @Test
    void getAll_shouldReturnListOfCats() {
        when(catDao.getAll()).thenReturn(List.of(testCat));

        List<Cat> cats = catDao.getAll();
        assertFalse(cats.isEmpty());
    }

    @Test
    void deleteById_shouldNotThrowException() {
        doNothing().when(catDao).deleteById(1L);

        assertDoesNotThrow(() -> catDao.deleteById(1L));
    }
}
