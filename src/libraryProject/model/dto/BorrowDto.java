package libraryProject.model.dto;

public class BorrowDto {
    private String name;

    public BorrowDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BorrowDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
