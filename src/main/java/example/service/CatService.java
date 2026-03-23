package example.service;

import example.dto.CatDto;
import example.dto.OwnerDto;
import example.model.Cat;
import example.model.Owner;
import example.repository.CatRepository;
import example.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@Service
@Transactional
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    // Получение кота с преобразованием в DTO
    public CatDto getCatById(Long id) {
        return catRepository.findById(id)
                .map(CatDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));
    }

    // Сохранение кота с валидацией
    public CatDto createCat(CatDto catDto) {
        validateCat(catDto);

        Cat cat = new Cat("барсик", new Date(), "breed", "черный", new Owner("Vfif", new Date()));
        cat.setName(catDto.getName());
        cat.setColor(catDto.getColor());

        if (catDto.getOwner() != null) {
            Owner owner = ownerRepository.findById(catDto.getOwner().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
            cat.setOwner(owner);
        }

        return new CatDto(catRepository.save(cat));
    }

    private void validateCat(CatDto catDto) {
        if (catDto.getName() == null || catDto.getName().isBlank()) {
            throw new IllegalArgumentException("Cat name is required");
        }
    }

    public Page<CatDto> findByColor(String color, Pageable pageable) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color parameter must not be empty");
        }

        return catRepository.findByColor(color, pageable)
                .map(CatDto::new);
    }

    public CatDto save(CatDto catDto) {
        validateCat(catDto);

        Cat cat;
        if (catDto.getId() != null) {
            // Обновление существующего кота
            cat = catRepository.findById(catDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + catDto.getId()));
        } else {
            // Создание нового кота
            cat = new Cat();
        }

        // Обновляем поля
        cat.setName(catDto.getName());
        cat.setColor(catDto.getColor());
        cat.setBreed(catDto.getBreed());
        cat.setId(catDto.getId());

        // Обработка владельца
        if (catDto.getOwner() != null && catDto.getOwner().getId() != null) {
            Owner owner = ownerRepository.findById(catDto.getOwner().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Owner not found"));
            cat.setOwner(owner);
        } else {
            cat.setOwner(null);
        }

        Cat savedCat = catRepository.save(cat);

        // Явное преобразование Cat в CatDto
        CatDto savedCatDto = new CatDto();
        savedCatDto.setName(savedCat.getName());
        savedCatDto.setColor(savedCat.getColor());
        savedCatDto.setBreed(savedCat.getBreed());

        if (savedCat.getOwner() != null) {
            OwnerDto ownerDto = new OwnerDto(cat.getOwner());
            savedCatDto.setOwner(ownerDto);
        }

        return savedCatDto;
    }

    public CatDto findById(long id) {
        Optional<Cat> optionalCat = catRepository.findById(id);
        if (!optionalCat.isPresent()) {
            throw new EntityNotFoundException("Cat not found with id: " + id);
        }
        Cat cat = optionalCat.get();

        // Явное создание CatDto
        CatDto catDto = new CatDto();
        catDto.setName(cat.getName());
        catDto.setColor(cat.getColor());
        cat.setOwner(cat.getOwner());
        cat.setBirthDate(cat.getBirthDate());
        cat.setBreed(cat.getBreed());
        return catDto;
        //it is a difference
    }

    public Page<CatDto> findAll(Pageable pageable) {
        return catRepository.findAll(pageable)
                .map(cat -> new CatDto(cat));
    }

    public <T> Object findAll(String s, int i, int i1, T any) {
        return null;
    }
}
