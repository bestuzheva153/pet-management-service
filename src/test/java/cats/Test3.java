package test.java.cats;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.DemoApplication;
import example.controller.CatController;
import example.dto.CatDto;
import example.dto.OwnerDto;
import example.service.CatService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
public class Test3 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCats() throws Exception {
        CatDto cat = new CatDto();
        cat.setId(1L);
        cat.setName("Барсик");
        cat.setColor("рыжий");

        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(catService.findAll(pageable))
                .thenReturn(new PageImpl<>(List.of(cat)));

        mockMvc.perform(get("/api/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Барсик"));
    }

    @Test
    void testGetCatById() throws Exception {
        CatDto cat = new CatDto();
        cat.setId(2L);
        cat.setName("Мурка");
        cat.setColor("черный");

        Mockito.when(catService.findById(2L)).thenReturn(cat);

        mockMvc.perform(get("/api/cats/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Мурка"));
    }

    @Test
    void testGetCatsByColor() throws Exception {
        CatDto cat = new CatDto();
        cat.setId(3L);
        cat.setName("Рыжик");
        cat.setColor("рыжий");

        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(catService.findByColor("рыжий", pageable))
                .thenReturn(new PageImpl<>(List.of(cat)));

        mockMvc.perform(get("/api/cats/by-color")
                        .param("color", "рыжий"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].color").value("рыжий"));
    }

    @Test
    void testCreateCat() throws Exception {
        OwnerDto owner = new OwnerDto(1L, "Алексей", new Date(1990, 1, 1));
        CatDto inputCat = new CatDto();
        inputCat.setName("Снежок");
        inputCat.setColor("белый");
        inputCat.setOwner(owner);

        CatDto savedCat = new CatDto();
        savedCat.setId(5L);
        savedCat.setName("Снежок");
        savedCat.setColor("белый");
        savedCat.setOwner(owner);

        Mockito.when(catService.save(any(CatDto.class))).thenReturn(savedCat);

        mockMvc.perform(post("/api/cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputCat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Снежок"));
    }
}
