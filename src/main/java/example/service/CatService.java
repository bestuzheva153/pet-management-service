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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatDto getCatById(Long id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + id));
        return toDto(cat);
    }

    public CatDto createCat(CatDto catDto) {
        validateCat(catDto);

        Cat cat = new Cat();
        applyDtoToCat(cat, catDto);

        Cat savedCat = catRepository.save(cat);
        return toDto(savedCat);
    }

    public CatDto save(CatDto catDto) {
        validateCat(catDto);

        Cat cat;
        if (catDto.getId() != null) {
            cat = catRepository.findById(catDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + catDto.getId()));
        } else {
            cat = new Cat();
        }

        applyDtoToCat(cat, catDto);

        Cat savedCat = catRepository.save(cat);
        return toDto(savedCat);
    }

    public CatDto findById(long id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found with id: " + id));
        return toDto(cat);
    }

    public Page<CatDto> findAll(Pageable pageable) {
        return catRepository.findAll(pageable)
                .map(this::toDto);
    }

    public Page<CatDto> findByColor(String color, Pageable pageable) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color parameter must not be empty");
        }

        return catRepository.findByColor(color, pageable)
                .map(this::toDto);
    }

    private void validateCat(CatDto catDto) {
        if (catDto.getName() == null || catDto.getName().isBlank()) {
            throw new IllegalArgumentException("Cat name is required");
        }
    }

    private void applyDtoToCat(Cat cat, CatDto catDto) {
        cat.setName(catDto.getName());
        cat.setColor(catDto.getColor());
        cat.setBreed(catDto.getBreed());

        if (catDto.getOwner() != null && catDto.getOwner().getId() != null) {
            Owner owner = ownerRepository.findById(catDto.getOwner().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Owner not found with id: " + catDto.getOwner().getId()
                    ));
            cat.setOwner(owner);
        } else {
            cat.setOwner(null);
        }
    }

    private CatDto toDto(Cat cat) {
        CatDto dto = new CatDto();
        dto.setId(cat.getId());
        dto.setName(cat.getName());
        dto.setColor(cat.getColor());
        dto.setBreed(cat.getBreed());

        if (cat.getOwner() != null) {
            dto.setOwner(new OwnerDto(cat.getOwner()));
        }

        return dto;
    }
}