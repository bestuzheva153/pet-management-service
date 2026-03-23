    package example.dto;

    import example.model.Cat;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @NoArgsConstructor
    public class CatDto {
        private String breed;
        private Long id;
        private String name;
        private String color;
        private OwnerDto owner;

        public CatDto(Cat cat) {
            this.name = cat.getName();
            this.color = cat.getColor();
            this.breed = cat.getBreed();
            this.owner = cat.getOwner() != null ? new OwnerDto(cat.getOwner()) : null;
        }
    }
