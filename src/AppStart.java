import libraryProject.view.BookView;
import libraryProject.view.UserView;

public class AppStart {
    public static void main(String[] args) {
        UserView.getInstance().index();
        BookView.getInstance().ii();
    }
}
