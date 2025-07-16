package libraryProject.model.dto;

public class BookDto {
    private int bno;
    private static int count = 1;
    private String bname;
    private String bwriter;
    private BookDto () { }

    public BookDto (String bname, String bwriter) {
        this.bno = count++;
        this.bname = bname;
        this.bwriter = bwriter;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bname='" + bname + '\'' +
                ", bwriter='" + bwriter + '\'' +
                '}';
    }
}
